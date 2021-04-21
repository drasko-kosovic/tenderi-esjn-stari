import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { ISpecifikacija, Specifikacija } from '../specifikacija.model';
import { SpecifikacijaService } from '../service/specifikacija.service';

@Injectable({ providedIn: 'root' })
export class SpecifikacijaRoutingResolveService implements Resolve<ISpecifikacija> {
  constructor(protected service: SpecifikacijaService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ISpecifikacija> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((specifikacija: HttpResponse<Specifikacija>) => {
          if (specifikacija.body) {
            return of(specifikacija.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Specifikacija());
  }
}
