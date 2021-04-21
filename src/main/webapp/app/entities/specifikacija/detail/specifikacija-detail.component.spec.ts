import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { SpecifikacijaDetailComponent } from './specifikacija-detail.component';

describe('Component Tests', () => {
  describe('Specifikacija Management Detail Component', () => {
    let comp: SpecifikacijaDetailComponent;
    let fixture: ComponentFixture<SpecifikacijaDetailComponent>;

    beforeEach(() => {
      TestBed.configureTestingModule({
        declarations: [SpecifikacijaDetailComponent],
        providers: [
          {
            provide: ActivatedRoute,
            useValue: { data: of({ specifikacija: { id: 123 } }) },
          },
        ],
      })
        .overrideTemplate(SpecifikacijaDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(SpecifikacijaDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load specifikacija on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.specifikacija).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
