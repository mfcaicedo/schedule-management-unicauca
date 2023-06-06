import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';

import { ReportesRoutingModule } from './reportes-routing.module';

import { ReactiveFormsModule } from '@angular/forms';


import {
  AccordionModule,
  BadgeModule,
  BreadcrumbModule,
  ButtonModule,
  CardModule,
  CarouselModule,
  CollapseModule,
  DropdownModule,
  FormModule,
  GridModule,
  ListGroupModule,
  ModalModule,
  NavModule,
  PaginationModule,
  PlaceholderModule,
  PopoverModule,
  ProgressModule,
  SharedModule,
  SpinnerModule,
  TableModule,
  TabsModule,
  TooltipModule,
  UtilitiesModule
} from '@coreui/angular';

import { FormsModule } from '@angular/forms';
import { FullCalendarModule } from '@fullcalendar/angular';
import { CalendarioComponent } from './calendario/calendario.component';
import { ReportFacultyComponent } from './report-faculty/report-faculty.component';
import { ReportProgramComponent } from './report-program/report-program.component';
import { ReportSemestreComponent } from './report-semestre/report-semestre.component';
import { ReportTeacherComponent } from './report-teacher/report-teacher.component';
import { RoomComponent } from './room/room.component';
import { DepartamentoComponent } from './report-teacher/departamento/departamento.component';
import { DatosPersonalesComponent } from './report-teacher/datos-personales/datos-personales.component';


@NgModule({
    declarations: [
        RoomComponent,
        ReportFacultyComponent,
        ReportProgramComponent,
        ReportSemestreComponent,
        ReportTeacherComponent,
        CalendarioComponent,
        DepartamentoComponent,
        DatosPersonalesComponent
    ],
    imports: [
        CommonModule,
        ReportesRoutingModule,
    
        ReactiveFormsModule,
        FormsModule,
        ModalModule,
    
        AccordionModule,
        BadgeModule,
        BreadcrumbModule,
        ButtonModule,
        CardModule,
        CarouselModule,
        CollapseModule,
        FullCalendarModule,
        DropdownModule,
        FormModule,
        GridModule,
        ListGroupModule,
        NavModule,
        PaginationModule,
        PlaceholderModule,
        PopoverModule,
        ProgressModule,
        SharedModule,
        SpinnerModule,
        TableModule,
        TabsModule,
        TooltipModule,
        UtilitiesModule,
        FullCalendarModule
      ],
      exports: [
        RoomComponent,
        ReportFacultyComponent,
        ReportProgramComponent,
        ReportSemestreComponent,
        ReportTeacherComponent
      ]

})
export class ReportesModule { }