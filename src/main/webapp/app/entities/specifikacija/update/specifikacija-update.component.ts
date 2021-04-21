import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import { ISpecifikacija, Specifikacija } from '../specifikacija.model';
import { SpecifikacijaService } from '../service/specifikacija.service';

@Component({
  selector: 'jhi-specifikacija-update',
  templateUrl: './specifikacija-update.component.html',
})
export class SpecifikacijaUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    sifraPostupka: [null, [Validators.required]],
    brojPartije: [null, [Validators.required]],
    atc: [null, [Validators.required]],
    inn: [],
    farmaceutskiOblikLijeka: [],
    jacinaLijeka: [],
    trazenaKolicina: [null, [Validators.required]],
    pakovanje: [],
    procijenjenaVrijednost: [null, [Validators.required]],
    trazenaJedinicnaCijena: [null, [Validators.required]],
  });

  constructor(protected specifikacijaService: SpecifikacijaService, protected activatedRoute: ActivatedRoute, protected fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ specifikacija }) => {
      this.updateForm(specifikacija);
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const specifikacija = this.createFromForm();
    if (specifikacija.id !== undefined) {
      this.subscribeToSaveResponse(this.specifikacijaService.update(specifikacija));
    } else {
      this.subscribeToSaveResponse(this.specifikacijaService.create(specifikacija));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ISpecifikacija>>): void {
    result.pipe(finalize(() => this.onSaveFinalize())).subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.previousState();
  }

  protected onSaveError(): void {
    // Api for inheritance.
  }

  protected onSaveFinalize(): void {
    this.isSaving = false;
  }

  protected updateForm(specifikacija: ISpecifikacija): void {
    this.editForm.patchValue({
      id: specifikacija.id,
      sifraPostupka: specifikacija.sifraPostupka,
      brojPartije: specifikacija.brojPartije,
      atc: specifikacija.atc,
      inn: specifikacija.inn,
      farmaceutskiOblikLijeka: specifikacija.farmaceutskiOblikLijeka,
      jacinaLijeka: specifikacija.jacinaLijeka,
      trazenaKolicina: specifikacija.trazenaKolicina,
      pakovanje: specifikacija.pakovanje,
      procijenjenaVrijednost: specifikacija.procijenjenaVrijednost,
      trazenaJedinicnaCijena: specifikacija.trazenaJedinicnaCijena,
    });
  }

  protected createFromForm(): ISpecifikacija {
    return {
      ...new Specifikacija(),
      id: this.editForm.get(['id'])!.value,
      sifraPostupka: this.editForm.get(['sifraPostupka'])!.value,
      brojPartije: this.editForm.get(['brojPartije'])!.value,
      atc: this.editForm.get(['atc'])!.value,
      inn: this.editForm.get(['inn'])!.value,
      farmaceutskiOblikLijeka: this.editForm.get(['farmaceutskiOblikLijeka'])!.value,
      jacinaLijeka: this.editForm.get(['jacinaLijeka'])!.value,
      trazenaKolicina: this.editForm.get(['trazenaKolicina'])!.value,
      pakovanje: this.editForm.get(['pakovanje'])!.value,
      procijenjenaVrijednost: this.editForm.get(['procijenjenaVrijednost'])!.value,
      trazenaJedinicnaCijena: this.editForm.get(['trazenaJedinicnaCijena'])!.value,
    };
  }
}
