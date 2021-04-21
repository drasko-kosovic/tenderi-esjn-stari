import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { ISpecifikacija, Specifikacija } from '../specifikacija.model';

import { SpecifikacijaService } from './specifikacija.service';

describe('Service Tests', () => {
  describe('Specifikacija Service', () => {
    let service: SpecifikacijaService;
    let httpMock: HttpTestingController;
    let elemDefault: ISpecifikacija;
    let expectedResult: ISpecifikacija | ISpecifikacija[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      service = TestBed.inject(SpecifikacijaService);
      httpMock = TestBed.inject(HttpTestingController);

      elemDefault = {
        id: 0,
        sifraPostupka: 0,
        brojPartije: 0,
        atc: 'AAAAAAA',
        inn: 'AAAAAAA',
        farmaceutskiOblikLijeka: 'AAAAAAA',
        jacinaLijeka: 'AAAAAAA',
        trazenaKolicina: 0,
        pakovanje: 'AAAAAAA',
        procijenjenaVrijednost: 0,
        trazenaJedinicnaCijena: 0,
      };
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a Specifikacija', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new Specifikacija()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a Specifikacija', () => {
        const returnedFromService = Object.assign(
          {
            id: 1,
            sifraPostupka: 1,
            brojPartije: 1,
            atc: 'BBBBBB',
            inn: 'BBBBBB',
            farmaceutskiOblikLijeka: 'BBBBBB',
            jacinaLijeka: 'BBBBBB',
            trazenaKolicina: 1,
            pakovanje: 'BBBBBB',
            procijenjenaVrijednost: 1,
            trazenaJedinicnaCijena: 1,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should partial update a Specifikacija', () => {
        const patchObject = Object.assign(
          {
            sifraPostupka: 1,
            brojPartije: 1,
            atc: 'BBBBBB',
            farmaceutskiOblikLijeka: 'BBBBBB',
            trazenaKolicina: 1,
          },
          new Specifikacija()
        );

        const returnedFromService = Object.assign(patchObject, elemDefault);

        const expected = Object.assign({}, returnedFromService);

        service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PATCH' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of Specifikacija', () => {
        const returnedFromService = Object.assign(
          {
            id: 1,
            sifraPostupka: 1,
            brojPartije: 1,
            atc: 'BBBBBB',
            inn: 'BBBBBB',
            farmaceutskiOblikLijeka: 'BBBBBB',
            jacinaLijeka: 'BBBBBB',
            trazenaKolicina: 1,
            pakovanje: 'BBBBBB',
            procijenjenaVrijednost: 1,
            trazenaJedinicnaCijena: 1,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a Specifikacija', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });

      describe('addSpecifikacijaToCollectionIfMissing', () => {
        it('should add a Specifikacija to an empty array', () => {
          const specifikacija: ISpecifikacija = { id: 123 };
          expectedResult = service.addSpecifikacijaToCollectionIfMissing([], specifikacija);
          expect(expectedResult).toHaveLength(1);
          expect(expectedResult).toContain(specifikacija);
        });

        it('should not add a Specifikacija to an array that contains it', () => {
          const specifikacija: ISpecifikacija = { id: 123 };
          const specifikacijaCollection: ISpecifikacija[] = [
            {
              ...specifikacija,
            },
            { id: 456 },
          ];
          expectedResult = service.addSpecifikacijaToCollectionIfMissing(specifikacijaCollection, specifikacija);
          expect(expectedResult).toHaveLength(2);
        });

        it("should add a Specifikacija to an array that doesn't contain it", () => {
          const specifikacija: ISpecifikacija = { id: 123 };
          const specifikacijaCollection: ISpecifikacija[] = [{ id: 456 }];
          expectedResult = service.addSpecifikacijaToCollectionIfMissing(specifikacijaCollection, specifikacija);
          expect(expectedResult).toHaveLength(2);
          expect(expectedResult).toContain(specifikacija);
        });

        it('should add only unique Specifikacija to an array', () => {
          const specifikacijaArray: ISpecifikacija[] = [{ id: 123 }, { id: 456 }, { id: 3300 }];
          const specifikacijaCollection: ISpecifikacija[] = [{ id: 123 }];
          expectedResult = service.addSpecifikacijaToCollectionIfMissing(specifikacijaCollection, ...specifikacijaArray);
          expect(expectedResult).toHaveLength(3);
        });

        it('should accept varargs', () => {
          const specifikacija: ISpecifikacija = { id: 123 };
          const specifikacija2: ISpecifikacija = { id: 456 };
          expectedResult = service.addSpecifikacijaToCollectionIfMissing([], specifikacija, specifikacija2);
          expect(expectedResult).toHaveLength(2);
          expect(expectedResult).toContain(specifikacija);
          expect(expectedResult).toContain(specifikacija2);
        });

        it('should accept null and undefined values', () => {
          const specifikacija: ISpecifikacija = { id: 123 };
          expectedResult = service.addSpecifikacijaToCollectionIfMissing([], null, specifikacija, undefined);
          expect(expectedResult).toHaveLength(1);
          expect(expectedResult).toContain(specifikacija);
        });
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
