import { NgModule } from '@angular/core';

import { SharedModule } from 'app/shared/shared.module';
import { SpecifikacijaComponent } from './list/specifikacija.component';
import { SpecifikacijaDetailComponent } from './detail/specifikacija-detail.component';
import { SpecifikacijaUpdateComponent } from './update/specifikacija-update.component';
import { SpecifikacijaDeleteDialogComponent } from './delete/specifikacija-delete-dialog.component';
import { SpecifikacijaRoutingModule } from './route/specifikacija-routing.module';

@NgModule({
  imports: [SharedModule, SpecifikacijaRoutingModule],
  declarations: [SpecifikacijaComponent, SpecifikacijaDetailComponent, SpecifikacijaUpdateComponent, SpecifikacijaDeleteDialogComponent],
  entryComponents: [SpecifikacijaDeleteDialogComponent],
})
export class SpecifikacijaModule {}
