import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { ISpecifikacija, getSpecifikacijaIdentifier } from '../specifikacija.model';

export type EntityResponseType = HttpResponse<ISpecifikacija>;
export type EntityArrayResponseType = HttpResponse<ISpecifikacija[]>;

@Injectable({ providedIn: 'root' })
export class SpecifikacijaService {
  public resourceUrl = this.applicationConfigService.getEndpointFor('api/specifikacijas');

  constructor(protected http: HttpClient, private applicationConfigService: ApplicationConfigService) {}

  create(specifikacija: ISpecifikacija): Observable<EntityResponseType> {
    return this.http.post<ISpecifikacija>(this.resourceUrl, specifikacija, { observe: 'response' });
  }

  update(specifikacija: ISpecifikacija): Observable<EntityResponseType> {
    return this.http.put<ISpecifikacija>(`${this.resourceUrl}/${getSpecifikacijaIdentifier(specifikacija) as number}`, specifikacija, {
      observe: 'response',
    });
  }

  partialUpdate(specifikacija: ISpecifikacija): Observable<EntityResponseType> {
    return this.http.patch<ISpecifikacija>(`${this.resourceUrl}/${getSpecifikacijaIdentifier(specifikacija) as number}`, specifikacija, {
      observe: 'response',
    });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ISpecifikacija>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ISpecifikacija[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  addSpecifikacijaToCollectionIfMissing(
    specifikacijaCollection: ISpecifikacija[],
    ...specifikacijasToCheck: (ISpecifikacija | null | undefined)[]
  ): ISpecifikacija[] {
    const specifikacijas: ISpecifikacija[] = specifikacijasToCheck.filter(isPresent);
    if (specifikacijas.length > 0) {
      const specifikacijaCollectionIdentifiers = specifikacijaCollection.map(
        specifikacijaItem => getSpecifikacijaIdentifier(specifikacijaItem)!
      );
      const specifikacijasToAdd = specifikacijas.filter(specifikacijaItem => {
        const specifikacijaIdentifier = getSpecifikacijaIdentifier(specifikacijaItem);
        if (specifikacijaIdentifier == null || specifikacijaCollectionIdentifiers.includes(specifikacijaIdentifier)) {
          return false;
        }
        specifikacijaCollectionIdentifiers.push(specifikacijaIdentifier);
        return true;
      });
      return [...specifikacijasToAdd, ...specifikacijaCollection];
    }
    return specifikacijaCollection;
  }
}
