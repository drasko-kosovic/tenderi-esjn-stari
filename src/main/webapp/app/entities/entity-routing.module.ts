import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'ponude',
        data: { pageTitle: 'tenderiApp.ponude.home.title' },
        loadChildren: () => import('./ponude/ponude.module').then(m => m.PonudeModule),
      },
      {
        path: 'specifikacija',
        data: { pageTitle: 'tenderiApp.specifikacija.home.title' },
        loadChildren: () => import('./specifikacija/specifikacija.module').then(m => m.SpecifikacijaModule),
      },
      {
        path: 'prvorangirani',
        data: { pageTitle: 'tenderiApp.prvorangirani.home.title' },
        loadChildren: () => import('./prvorangirani/prvorangirani.module').then(m => m.PrvorangiraniModule),
      },
      {
        path: 'hvale-ponude',
        data: { pageTitle: 'tenderiApp.hvaleponude.home.title' },
        loadChildren: () => import('./hvale-ponude/hvale-ponude.module').then(m => m.HvalePonudeModule),
      },
      {
        path: 'view-vrednovanje',
        data: { pageTitle: 'tenderiApp.viewVrednovanje.home.title' },
        loadChildren: () => import('./view-vrednovanje/view-vrednovanje.module').then(m => m.ViewVrednovanjeModule),
      },
      {
        path: 'ponudjaci',
        data: { pageTitle: 'tenderiApp.ponudjaci.home.title' },
        loadChildren: () => import('./ponudjaci/ponudjaci.module').then(m => m.PonudjaciModule),
      },
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class EntityRoutingModule {}
