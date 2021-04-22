import { Component, Inject, OnInit } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { combineLatest } from 'rxjs';

import { IViewVrednovanje } from '../view-vrednovanje.model';

import { ITEMS_PER_PAGE } from 'app/config/pagination.constants';
import { ViewVrednovanjeService } from '../service/view-vrednovanje.service';
import { SERVER_API_URL } from 'app/app.constants';
import { DOCUMENT } from '@angular/common';

@Component({
  selector: 'jhi-view-vrednovanje',
  templateUrl: './view-vrednovanje.component.html',
})
export class ViewVrednovanjeComponent implements OnInit {
  viewVrednovanjes?: IViewVrednovanje[];
  isLoading = false;
  totalItems = 0;
  itemsPerPage = ITEMS_PER_PAGE;
  page?: number;
  predicate!: string;
  ascending!: boolean;
  ngbPaginationPage = 1;
  brojPostupka: any;
  brojPonude: any;

  public resourceUrlExel = SERVER_API_URL + '/api/excel/download';

  constructor(
    protected viewVrednovanjeService: ViewVrednovanjeService,
    protected activatedRoute: ActivatedRoute,
    protected router: Router,
    @Inject(DOCUMENT) private document: Document
  ) {}
  public exel(): void {
    if (this.brojPonude === undefined && this.brojPostupka !== undefined) {
      // (`${this.resourceUrl}/${id}`, { observe: 'response' });
      this.document.location.href = this.resourceUrlExel + '/sifra-postupka/' + String(this.brojPostupka);
    }
    if (this.brojPostupka === undefined && this.brojPonude !== undefined) {
      // eslint-disable-next-line no-console
      console.log('polje cijena je puno a polje artikal je prazno zato dajem samo potrgu za cijenu exel');
      this.document.location.href = this.resourceUrlExel + '/sifra-ponude/' + String(this.brojPonude);
    }
    if (this.brojPonude !== undefined && this.brojPostupka !== undefined) {
      // eslint-disable-next-line no-console
      console.log('oba polja su puna');
      this.document.location.href =
        this.resourceUrlExel + '/postupakponuda?sifraPostupka=' + String(this.brojPostupka) + '&sifraPonude=' + String(this.brojPonude);
    }
    if (this.brojPostupka === undefined && this.brojPonude === undefined) {
      // eslint-disable-next-line no-console
      console.log('oba polja su puna');
      this.document.location.href = this.resourceUrlExel;
    }
  }

  loadPage(page?: number, dontNavigate?: boolean): void {
    this.isLoading = true;
    const pageToLoad: number = page ?? this.page ?? 1;

    this.viewVrednovanjeService
      .query({
        page: pageToLoad - 1,
        size: this.itemsPerPage,
        sort: this.sort(),
      })
      .subscribe(
        (res: HttpResponse<IViewVrednovanje[]>) => {
          this.isLoading = false;
          this.onSuccess(res.body, res.headers, pageToLoad, !dontNavigate);
        },
        () => {
          this.isLoading = false;
          this.onError();
        }
      );
  }

  ngOnInit(): void {
    this.handleNavigation();
  }

  loadPageByPostupak(page?: number, dontNavigate?: boolean): void {
    this.isLoading = true;
    const pageToLoad: number = page ?? this.page ?? 1;

    this.viewVrednovanjeService
      .query({
        'sifraPostupka.in': this.brojPostupka,
        page: pageToLoad - 1,
        size: this.itemsPerPage,
        sort: this.sort(),
      })
      .subscribe(
        (res: HttpResponse<IViewVrednovanje[]>) => {
          this.isLoading = false;
          this.onSuccess(res.body, res.headers, pageToLoad, !dontNavigate);
        },
        () => {
          this.isLoading = false;
          this.onError();
        }
      );
  }

  loadPageByPonude(page?: number, dontNavigate?: boolean): void {
    this.isLoading = true;
    const pageToLoad: number = page ?? this.page ?? 1;

    this.viewVrednovanjeService
      .query({
        'sifraPonude.in': this.brojPonude,
        page: pageToLoad - 1,
        size: this.itemsPerPage,
        sort: this.sort(),
      })
      .subscribe(
        (res: HttpResponse<IViewVrednovanje[]>) => {
          this.isLoading = false;
          this.onSuccess(res.body, res.headers, pageToLoad, !dontNavigate);
        },
        () => {
          this.isLoading = false;
          this.onError();
        }
      );
  }
  prazanRefresh(): void {
    this.prazanPostupak();
    this.prazanPonude();
  }
  prazanPostupak(): void {
    this.brojPostupka = '';
    this.loadPage();
  }
  prazanPonude(): void {
    this.brojPonude = '';
    this.loadPage();
  }

  trackId(index: number, item: IViewVrednovanje): number {
    return item.id!;
  }

  protected sort(): string[] {
    const result = [this.predicate + ',' + (this.ascending ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected handleNavigation(): void {
    combineLatest([this.activatedRoute.data, this.activatedRoute.queryParamMap]).subscribe(([data, params]) => {
      const page = params.get('page');
      const pageNumber = page !== null ? +page : 1;
      const sort = (params.get('sort') ?? data['defaultSort']).split(',');
      const predicate = sort[0];
      const ascending = sort[1] === 'asc';
      if (pageNumber !== this.page || predicate !== this.predicate || ascending !== this.ascending) {
        this.predicate = predicate;
        this.ascending = ascending;
        this.loadPage(pageNumber, true);
      }
    });
  }

  protected onSuccess(data: IViewVrednovanje[] | null, headers: HttpHeaders, page: number, navigate: boolean): void {
    this.totalItems = Number(headers.get('X-Total-Count'));
    this.page = page;
    if (navigate) {
      this.router.navigate(['/view-vrednovanje'], {
        queryParams: {
          page: this.page,
          size: this.itemsPerPage,
          sort: this.predicate + ',' + (this.ascending ? 'asc' : 'desc'),
        },
      });
    }
    this.viewVrednovanjes = data ?? [];
    this.ngbPaginationPage = this.page;
  }

  protected onError(): void {
    this.ngbPaginationPage = this.page ?? 1;
  }
}
