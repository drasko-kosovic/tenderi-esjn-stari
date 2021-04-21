import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ISpecifikacija } from '../specifikacija.model';

@Component({
  selector: 'jhi-specifikacija-detail',
  templateUrl: './specifikacija-detail.component.html',
})
export class SpecifikacijaDetailComponent implements OnInit {
  specifikacija: ISpecifikacija | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ specifikacija }) => {
      this.specifikacija = specifikacija;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
