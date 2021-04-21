jest.mock('@angular/router');

import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { of, Subject } from 'rxjs';

import { SpecifikacijaService } from '../service/specifikacija.service';
import { ISpecifikacija, Specifikacija } from '../specifikacija.model';

import { SpecifikacijaUpdateComponent } from './specifikacija-update.component';

describe('Component Tests', () => {
  describe('Specifikacija Management Update Component', () => {
    let comp: SpecifikacijaUpdateComponent;
    let fixture: ComponentFixture<SpecifikacijaUpdateComponent>;
    let activatedRoute: ActivatedRoute;
    let specifikacijaService: SpecifikacijaService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        declarations: [SpecifikacijaUpdateComponent],
        providers: [FormBuilder, ActivatedRoute],
      })
        .overrideTemplate(SpecifikacijaUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(SpecifikacijaUpdateComponent);
      activatedRoute = TestBed.inject(ActivatedRoute);
      specifikacijaService = TestBed.inject(SpecifikacijaService);

      comp = fixture.componentInstance;
    });

    describe('ngOnInit', () => {
      it('Should update editForm', () => {
        const specifikacija: ISpecifikacija = { id: 456 };

        activatedRoute.data = of({ specifikacija });
        comp.ngOnInit();

        expect(comp.editForm.value).toEqual(expect.objectContaining(specifikacija));
      });
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', () => {
        // GIVEN
        const saveSubject = new Subject();
        const specifikacija = { id: 123 };
        spyOn(specifikacijaService, 'update').and.returnValue(saveSubject);
        spyOn(comp, 'previousState');
        activatedRoute.data = of({ specifikacija });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.next(new HttpResponse({ body: specifikacija }));
        saveSubject.complete();

        // THEN
        expect(comp.previousState).toHaveBeenCalled();
        expect(specifikacijaService.update).toHaveBeenCalledWith(specifikacija);
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', () => {
        // GIVEN
        const saveSubject = new Subject();
        const specifikacija = new Specifikacija();
        spyOn(specifikacijaService, 'create').and.returnValue(saveSubject);
        spyOn(comp, 'previousState');
        activatedRoute.data = of({ specifikacija });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.next(new HttpResponse({ body: specifikacija }));
        saveSubject.complete();

        // THEN
        expect(specifikacijaService.create).toHaveBeenCalledWith(specifikacija);
        expect(comp.isSaving).toEqual(false);
        expect(comp.previousState).toHaveBeenCalled();
      });

      it('Should set isSaving to false on error', () => {
        // GIVEN
        const saveSubject = new Subject();
        const specifikacija = { id: 123 };
        spyOn(specifikacijaService, 'update').and.returnValue(saveSubject);
        spyOn(comp, 'previousState');
        activatedRoute.data = of({ specifikacija });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.error('This is an error!');

        // THEN
        expect(specifikacijaService.update).toHaveBeenCalledWith(specifikacija);
        expect(comp.isSaving).toEqual(false);
        expect(comp.previousState).not.toHaveBeenCalled();
      });
    });
  });
});
