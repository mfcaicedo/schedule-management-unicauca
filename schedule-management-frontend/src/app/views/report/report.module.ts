import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ReportRoutingModule } from './report-routing.module';
import { ReportFacultyComponent } from './report-faculty/report-faculty.component';
import { ReportEnvironmentComponent } from './report-environment/report-environment.component';
import { EnvironmentModule } from '../environment/environment.module';
import { ToshareModule } from 'src/app/views/toshare/toshare.module';
import {
  AlertModule,
  CardModule,
  FormModule,
  GridModule,
  TableModule
} from '@coreui/angular';
@NgModule({
  declarations: [
    ReportFacultyComponent,
    ReportEnvironmentComponent
  ],
  imports: [
    CommonModule,
    ReportRoutingModule,
    EnvironmentModule,
    ToshareModule,

    AlertModule,
    CardModule,
    FormModule,
    GridModule,
    TableModule
  ]
})
export class ReportModule { }
