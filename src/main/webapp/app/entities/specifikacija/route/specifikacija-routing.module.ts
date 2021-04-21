import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { SpecifikacijaComponent } from '../list/specifikacija.component';
import { SpecifikacijaDetailComponent } from '../detail/specifikacija-detail.component';
import { SpecifikacijaUpdateComponent } from '../update/specifikacija-update.component';
import { SpecifikacijaRoutingResolveService } from './specifikacija-routing-resolve.service';

const specifikacijaRoute: Routes = [
  {
    path: '',
    component: SpecifikacijaComponent,
    data: {
      defaultSort: 'id,asc',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: SpecifikacijaDetailComponent,
    resolve: {
      specifikacija: SpecifikacijaRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: SpecifikacijaUpdateComponent,
    resolve: {
      specifikacija: SpecifikacijaRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: SpecifikacijaUpdateComponent,
    resolve: {
      specifikacija: SpecifikacijaRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(specifikacijaRoute)],
  exports: [RouterModule],
})
export class SpecifikacijaRoutingModule {}
