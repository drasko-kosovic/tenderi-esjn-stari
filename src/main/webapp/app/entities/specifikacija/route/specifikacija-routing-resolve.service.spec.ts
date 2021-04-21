jest.mock('@angular/router');

import { TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { of } from 'rxjs';

import { ISpecifikacija, Specifikacija } from '../specifikacija.model';
import { SpecifikacijaService } from '../service/specifikacija.service';

import { SpecifikacijaRoutingResolveService } from './specifikacija-routing-resolve.service';

describe('Service Tests', () => {
  describe('Specifikacija routing resolve service', () => {
    let mockRouter: Router;
    let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
    let routingResolveService: SpecifikacijaRoutingResolveService;
    let service: SpecifikacijaService;
    let resultSpecifikacija: ISpecifikacija | undefined;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        providers: [Router, ActivatedRouteSnapshot],
      });
      mockRouter = TestBed.inject(Router);
      mockActivatedRouteSnapshot = TestBed.inject(ActivatedRouteSnapshot);
      routingResolveService = TestBed.inject(SpecifikacijaRoutingResolveService);
      service = TestBed.inject(SpecifikacijaService);
      resultSpecifikacija = undefined;
    });

    describe('resolve', () => {
      it('should return ISpecifikacija returned by find', () => {
        // GIVEN
        service.find = jest.fn(id => of(new HttpResponse({ body: { id } })));
        mockActivatedRouteSnapshot.params = { id: 123 };

        // WHEN
        routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
          resultSpecifikacija = result;
        });

        // THEN
        expect(service.find).toBeCalledWith(123);
        expect(resultSpecifikacija).toEqual({ id: 123 });
      });

      it('should return new ISpecifikacija if id is not provided', () => {
        // GIVEN
        service.find = jest.fn();
        mockActivatedRouteSnapshot.params = {};

        // WHEN
        routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
          resultSpecifikacija = result;
        });

        // THEN
        expect(service.find).not.toBeCalled();
        expect(resultSpecifikacija).toEqual(new Specifikacija());
      });

      it('should route to 404 page if data not found in server', () => {
        // GIVEN
        spyOn(service, 'find').and.returnValue(of(new HttpResponse({ body: null })));
        mockActivatedRouteSnapshot.params = { id: 123 };

        // WHEN
        routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
          resultSpecifikacija = result;
        });

        // THEN
        expect(service.find).toBeCalledWith(123);
        expect(resultSpecifikacija).toEqual(undefined);
        expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
      });
    });
  });
});
