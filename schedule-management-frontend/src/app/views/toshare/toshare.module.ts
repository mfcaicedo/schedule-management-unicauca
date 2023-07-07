import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ToshareRoutingModule } from './toshare-routing.module';
import { PaginadorComponent } from './paginador/paginador.component';
import {
  PaginationModule,
  DropdownModule
}from '@coreui/angular'

@NgModule({
  declarations: [
    PaginadorComponent
  ],
  imports: [
    CommonModule,
    ToshareRoutingModule,
    PaginationModule,
    DropdownModule
  ],
  exports:[
    PaginadorComponent
  ]
})
export class ToshareModule { }
