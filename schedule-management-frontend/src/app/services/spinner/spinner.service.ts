import { Injectable } from '@angular/core';
import { NgxSpinnerService } from 'ngx-spinner';

@Injectable({
  providedIn: 'root'
})
export class SpinnerService {

  constructor(private spinnerService: NgxSpinnerService) { }

  startSpinner() {
    this.spinnerService.show();
  }

  stopSpinner() {
    this.spinnerService.hide();
  }
}
