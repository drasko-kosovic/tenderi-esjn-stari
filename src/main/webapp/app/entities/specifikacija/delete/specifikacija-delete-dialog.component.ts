import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { ISpecifikacija } from '../specifikacija.model';
import { SpecifikacijaService } from '../service/specifikacija.service';

@Component({
  templateUrl: './specifikacija-delete-dialog.component.html',
})
export class SpecifikacijaDeleteDialogComponent {
  specifikacija?: ISpecifikacija;

  constructor(protected specifikacijaService: SpecifikacijaService, public activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.specifikacijaService.delete(id).subscribe(() => {
      this.activeModal.close('deleted');
    });
  }
}
