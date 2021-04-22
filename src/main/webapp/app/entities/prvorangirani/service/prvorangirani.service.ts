import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as dayjs from 'dayjs';

import { isPresent } from 'app/core/util/operators';
import { DATE_FORMAT } from 'app/config/input.constants';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IPrvorangirani, getPrvorangiraniIdentifier } from '../prvorangirani.model';

export type EntityResponseType = HttpResponse<IPrvorangirani>;
export type EntityArrayResponseType = HttpResponse<IPrvorangirani[]>;

@Injectable({ providedIn: 'root' })
export class PrvorangiraniService {
  public resourceUrl = this.applicationConfigService.getEndpointFor('api/prvorangiranis');

  constructor(protected http: HttpClient, private applicationConfigService: ApplicationConfigService) {}

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IPrvorangirani>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IPrvorangirani[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  addPrvorangiraniToCollectionIfMissing(
    prvorangiraniCollection: IPrvorangirani[],
    ...prvorangiranisToCheck: (IPrvorangirani | null | undefined)[]
  ): IPrvorangirani[] {
    const prvorangiranis: IPrvorangirani[] = prvorangiranisToCheck.filter(isPresent);
    if (prvorangiranis.length > 0) {
      const prvorangiraniCollectionIdentifiers = prvorangiraniCollection.map(
        prvorangiraniItem => getPrvorangiraniIdentifier(prvorangiraniItem)!
      );
      const prvorangiranisToAdd = prvorangiranis.filter(prvorangiraniItem => {
        const prvorangiraniIdentifier = getPrvorangiraniIdentifier(prvorangiraniItem);
        if (prvorangiraniIdentifier == null || prvorangiraniCollectionIdentifiers.includes(prvorangiraniIdentifier)) {
          return false;
        }
        prvorangiraniCollectionIdentifiers.push(prvorangiraniIdentifier);
        return true;
      });
      return [...prvorangiranisToAdd, ...prvorangiraniCollection];
    }
    return prvorangiraniCollection;
  }

  protected convertDateFromClient(prvorangirani: IPrvorangirani): IPrvorangirani {
    return Object.assign({}, prvorangirani, {
      datumUgovora: prvorangirani.datumUgovora?.isValid() ? prvorangirani.datumUgovora.format(DATE_FORMAT) : undefined,
    });
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.datumUgovora = res.body.datumUgovora ? dayjs(res.body.datumUgovora) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((prvorangirani: IPrvorangirani) => {
        prvorangirani.datumUgovora = prvorangirani.datumUgovora ? dayjs(prvorangirani.datumUgovora) : undefined;
      });
    }
    return res;
  }
}
