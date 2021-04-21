import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { IPonude, Ponude } from '../ponude.model';

import { PonudeService } from './ponude.service';

describe('Service Tests', () => {
  describe('Ponude Service', () => {
    let service: PonudeService;
    let httpMock: HttpTestingController;
    let elemDefault: IPonude;
    let expectedResult: IPonude | IPonude[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      service = TestBed.inject(PonudeService);
      httpMock = TestBed.inject(HttpTestingController);

      elemDefault = {
        id: 0,
        sifraPostupka: 0,
        sifraPonude: 0,
        brojPartije: 0,
        nazivPonudjaca: 'AAAAAAA',
        naziProizvodjaca: 'AAAAAAA',
        zastceniNaziv: 'AAAAAAA',
        ponudjenaVrijednost: 0,
        ponudjenaJedinicnaCijena: 0,
        rokIsporuke: 0,
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

      it('should create a Ponude', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new Ponude()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a Ponude', () => {
        const returnedFromService = Object.assign(
          {
            id: 1,
            sifraPostupka: 1,
            sifraPonude: 1,
            brojPartije: 1,
            nazivPonudjaca: 'BBBBBB',
            naziProizvodjaca: 'BBBBBB',
            zastceniNaziv: 'BBBBBB',
            ponudjenaVrijednost: 1,
            ponudjenaJedinicnaCijena: 1,
            rokIsporuke: 1,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should partial update a Ponude', () => {
        const patchObject = Object.assign(
          {
            sifraPostupka: 1,
            sifraPonude: 1,
            brojPartije: 1,
            naziProizvodjaca: 'BBBBBB',
            ponudjenaVrijednost: 1,
          },
          new Ponude()
        );

        const returnedFromService = Object.assign(patchObject, elemDefault);

        const expected = Object.assign({}, returnedFromService);

        service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PATCH' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of Ponude', () => {
        const returnedFromService = Object.assign(
          {
            id: 1,
            sifraPostupka: 1,
            sifraPonude: 1,
            brojPartije: 1,
            nazivPonudjaca: 'BBBBBB',
            naziProizvodjaca: 'BBBBBB',
            zastceniNaziv: 'BBBBBB',
            ponudjenaVrijednost: 1,
            ponudjenaJedinicnaCijena: 1,
            rokIsporuke: 1,
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

      it('should delete a Ponude', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });

      describe('addPonudeToCollectionIfMissing', () => {
        it('should add a Ponude to an empty array', () => {
          const ponude: IPonude = { id: 123 };
          expectedResult = service.addPonudeToCollectionIfMissing([], ponude);
          expect(expectedResult).toHaveLength(1);
          expect(expectedResult).toContain(ponude);
        });

        it('should not add a Ponude to an array that contains it', () => {
          const ponude: IPonude = { id: 123 };
          const ponudeCollection: IPonude[] = [
            {
              ...ponude,
            },
            { id: 456 },
          ];
          expectedResult = service.addPonudeToCollectionIfMissing(ponudeCollection, ponude);
          expect(expectedResult).toHaveLength(2);
        });

        it("should add a Ponude to an array that doesn't contain it", () => {
          const ponude: IPonude = { id: 123 };
          const ponudeCollection: IPonude[] = [{ id: 456 }];
          expectedResult = service.addPonudeToCollectionIfMissing(ponudeCollection, ponude);
          expect(expectedResult).toHaveLength(2);
          expect(expectedResult).toContain(ponude);
        });

        it('should add only unique Ponude to an array', () => {
          const ponudeArray: IPonude[] = [{ id: 123 }, { id: 456 }, { id: 49051 }];
          const ponudeCollection: IPonude[] = [{ id: 123 }];
          expectedResult = service.addPonudeToCollectionIfMissing(ponudeCollection, ...ponudeArray);
          expect(expectedResult).toHaveLength(3);
        });

        it('should accept varargs', () => {
          const ponude: IPonude = { id: 123 };
          const ponude2: IPonude = { id: 456 };
          expectedResult = service.addPonudeToCollectionIfMissing([], ponude, ponude2);
          expect(expectedResult).toHaveLength(2);
          expect(expectedResult).toContain(ponude);
          expect(expectedResult).toContain(ponude2);
        });

        it('should accept null and undefined values', () => {
          const ponude: IPonude = { id: 123 };
          expectedResult = service.addPonudeToCollectionIfMissing([], null, ponude, undefined);
          expect(expectedResult).toHaveLength(1);
          expect(expectedResult).toContain(ponude);
        });
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
