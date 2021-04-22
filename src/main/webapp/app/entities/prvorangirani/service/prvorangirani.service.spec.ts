import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as dayjs from 'dayjs';

import { DATE_FORMAT } from 'app/config/input.constants';
import { IPrvorangirani } from '../prvorangirani.model';

import { PrvorangiraniService } from './prvorangirani.service';

describe('Service Tests', () => {
  describe('Prvorangirani Service', () => {
    let service: PrvorangiraniService;
    let httpMock: HttpTestingController;
    let elemDefault: IPrvorangirani;
    let expectedResult: IPrvorangirani | IPrvorangirani[] | boolean | null;
    let currentDate: dayjs.Dayjs;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      service = TestBed.inject(PrvorangiraniService);
      httpMock = TestBed.inject(HttpTestingController);
      currentDate = dayjs();

      elemDefault = {
        id: 0,
        sifraPostupka: 0,
        sifraPonude: 0,
        brojPartije: 0,
        atc: 'AAAAAAA',
        inn: 'AAAAAAA',
        zastceniNaziv: 'AAAAAAA',
        farmaceutskiOblikLijeka: 'AAAAAAA',
        jacinaLijeka: 'AAAAAAA',
        pakovanje: 'AAAAAAA',
        trazenaKolicina: 0,
        trazenaJedinicnaCijena: 0,
        procijenjenaVrijednost: 0,
        ponudjenaJedinicnaCijena: 0,
        ponudjenaVrijednost: 0,
        rokIsporuke: 0,
        nazivPonudjaca: 'AAAAAAA',
        bodIsporuka: 0,
        brojUgovora: 'AAAAAAA',
        datumUgovora: currentDate,
      };
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            datumUgovora: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should return a list of Prvorangirani', () => {
        const returnedFromService = Object.assign(
          {
            id: 1,
            sifraPostupka: 1,
            sifraPonude: 1,
            brojPartije: 1,
            atc: 'BBBBBB',
            inn: 'BBBBBB',
            zastceniNaziv: 'BBBBBB',
            farmaceutskiOblikLijeka: 'BBBBBB',
            jacinaLijeka: 'BBBBBB',
            pakovanje: 'BBBBBB',
            trazenaKolicina: 1,
            trazenaJedinicnaCijena: 1,
            procijenjenaVrijednost: 1,
            ponudjenaJedinicnaCijena: 1,
            ponudjenaVrijednost: 1,
            rokIsporuke: 1,
            nazivPonudjaca: 'BBBBBB',
            bodIsporuka: 1,
            brojUgovora: 'BBBBBB',
            datumUgovora: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            datumUgovora: currentDate,
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      describe('addPrvorangiraniToCollectionIfMissing', () => {
        it('should add a Prvorangirani to an empty array', () => {
          const prvorangirani: IPrvorangirani = { id: 123 };
          expectedResult = service.addPrvorangiraniToCollectionIfMissing([], prvorangirani);
          expect(expectedResult).toHaveLength(1);
          expect(expectedResult).toContain(prvorangirani);
        });

        it('should not add a Prvorangirani to an array that contains it', () => {
          const prvorangirani: IPrvorangirani = { id: 123 };
          const prvorangiraniCollection: IPrvorangirani[] = [
            {
              ...prvorangirani,
            },
            { id: 456 },
          ];
          expectedResult = service.addPrvorangiraniToCollectionIfMissing(prvorangiraniCollection, prvorangirani);
          expect(expectedResult).toHaveLength(2);
        });

        it("should add a Prvorangirani to an array that doesn't contain it", () => {
          const prvorangirani: IPrvorangirani = { id: 123 };
          const prvorangiraniCollection: IPrvorangirani[] = [{ id: 456 }];
          expectedResult = service.addPrvorangiraniToCollectionIfMissing(prvorangiraniCollection, prvorangirani);
          expect(expectedResult).toHaveLength(2);
          expect(expectedResult).toContain(prvorangirani);
        });

        it('should add only unique Prvorangirani to an array', () => {
          const prvorangiraniArray: IPrvorangirani[] = [{ id: 123 }, { id: 456 }, { id: 43654 }];
          const prvorangiraniCollection: IPrvorangirani[] = [{ id: 123 }];
          expectedResult = service.addPrvorangiraniToCollectionIfMissing(prvorangiraniCollection, ...prvorangiraniArray);
          expect(expectedResult).toHaveLength(3);
        });

        it('should accept varargs', () => {
          const prvorangirani: IPrvorangirani = { id: 123 };
          const prvorangirani2: IPrvorangirani = { id: 456 };
          expectedResult = service.addPrvorangiraniToCollectionIfMissing([], prvorangirani, prvorangirani2);
          expect(expectedResult).toHaveLength(2);
          expect(expectedResult).toContain(prvorangirani);
          expect(expectedResult).toContain(prvorangirani2);
        });

        it('should accept null and undefined values', () => {
          const prvorangirani: IPrvorangirani = { id: 123 };
          expectedResult = service.addPrvorangiraniToCollectionIfMissing([], null, prvorangirani, undefined);
          expect(expectedResult).toHaveLength(1);
          expect(expectedResult).toContain(prvorangirani);
        });
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
