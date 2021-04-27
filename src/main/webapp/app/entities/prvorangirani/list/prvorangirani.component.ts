import { Component, Inject, OnInit } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { combineLatest } from 'rxjs';

import { IPrvorangirani } from '../prvorangirani.model';

import { ITEMS_PER_PAGE } from 'app/config/pagination.constants';
import { PrvorangiraniService } from '../service/prvorangirani.service';
import { SERVER_API_URL } from 'app/app.constants';
import { DOCUMENT } from '@angular/common';

@Component({
  selector: 'jhi-prvorangirani',
  templateUrl: './prvorangirani.component.html',
})
export class PrvorangiraniComponent implements OnInit {
  prvorangiranis?: IPrvorangirani[];
  isLoading = false;
  totalItems = 0;
  itemsPerPage = ITEMS_PER_PAGE;
  page?: number;
  predicate!: string;
  ascending!: boolean;
  ngbPaginationPage = 1;
  brojPostupka: any;
  brojPonude: any;
  public resourceUrlExel = SERVER_API_URL + '/api/excel-prvorangirani/download';
  constructor(
    protected prvorangiraniService: PrvorangiraniService,
    protected activatedRoute: ActivatedRoute,
    protected router: Router,
    @Inject(DOCUMENT) private document: Document
  ) {}

  public exel(): void {
    {
      this.document.location.href = this.resourceUrlExel;
    }
  }

  loadPageByPostupak(page?: number, dontNavigate?: boolean): void {
    this.isLoading = true;
    const pageToLoad: number = page ?? this.page ?? 1;

    this.prvorangiraniService
      .query({
        'sifraPostupka.in': this.brojPostupka,
        page: pageToLoad - 1,
        size: this.itemsPerPage,
        sort: this.sort(),
      })
      .subscribe(
        (res: HttpResponse<IPrvorangirani[]>) => {
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

    this.prvorangiraniService
      .query({
        'sifraPonude.in': this.brojPonude,
        page: pageToLoad - 1,
        size: this.itemsPerPage,
        sort: this.sort(),
      })
      .subscribe(
        (res: HttpResponse<IPrvorangirani[]>) => {
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

  loadPage(page?: number, dontNavigate?: boolean): void {
    this.isLoading = true;
    const pageToLoad: number = page ?? this.page ?? 1;

    this.prvorangiraniService
      .query({
        page: pageToLoad - 1,
        size: this.itemsPerPage,
        sort: this.sort(),
      })
      .subscribe(
        (res: HttpResponse<IPrvorangirani[]>) => {
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

  trackId(index: number, item: IPrvorangirani): number {
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

  protected onSuccess(data: IPrvorangirani[] | null, headers: HttpHeaders, page: number, navigate: boolean): void {
    this.totalItems = Number(headers.get('X-Total-Count'));
    this.page = page;
    if (navigate) {
      this.router.navigate(['/prvorangirani'], {
        queryParams: {
          page: this.page,
          size: this.itemsPerPage,
          sort: this.predicate + ',' + (this.ascending ? 'asc' : 'desc'),
        },
      });
    }
    this.prvorangiranis = data ?? [];
    this.ngbPaginationPage = this.page;
  }

  protected onError(): void {
    this.ngbPaginationPage = this.page ?? 1;
  }
}
