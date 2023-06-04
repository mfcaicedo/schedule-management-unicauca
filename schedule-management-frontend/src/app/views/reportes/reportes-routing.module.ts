import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CalendarioComponent } from './calendario/calendario.component';
import { ReportFacultyComponent } from './report-faculty/report-faculty.component';
import { ReportProgramComponent } from './report-program/report-program.component';
import { ReportSemestreComponent } from './report-semestre/report-semestre.component';
import { ReportTeacherComponent } from './report-teacher/report-teacher.component';
import { RoomComponent } from './room/room.component';

const routes: Routes = [
    {
      path: '',
      data: {
        title: 'Recurso',
      },
      children: [
        {
          path: '',
          redirectTo: '/all',
          pathMatch: 'full'
        },
        {
          path: 'all',
          component: RoomComponent,
          data: {
            title: 'Todos',
          }
        },
        {
          path: 'all/:resourceType',
          component: RoomComponent,
          data: {
            title: 'environmentType'
          }
        },
        {
          //detalle y de un ambiente
          path: 'salon',
          component: RoomComponent,
          data: {
            title: 'Salon'
          }
        },
        {
          //detalle y de un ambiente
          path: 'Report_faculty',
          component: ReportFacultyComponent,
          data: {
            title: 'Report_faculty'
          }
        },
        {
          //detalle y de un ambiente
          path: 'Report_program',
          component: ReportProgramComponent,
          data: {
            title: 'Report_program'
          }
        },
        {
          //detalle y de un ambiente
          path: 'Report_semestre',
          component: ReportSemestreComponent,
          data: {
            title: 'Report_semestre'
          }
        },
        {
          //detalle y de un ambiente
          path: 'Report_teacher',
          component: ReportTeacherComponent,
          data: {
            title: 'Report_teacher'
          }
        },
        {
          //detalle y de un ambiente
          path: 'calendario',
          component: CalendarioComponent,
          data: {
            title: 'PRUEBAS'
          }
        }/*
        {
          path: 'edit/:resourceId',
          component: ResourcesEditComponent,
          data: {
            title: 'Editar'
          }
        }*/
      ]
    }
  ];

  @NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
  })
  export class ReportesRoutingModule { }