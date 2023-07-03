import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CalendarioComponent } from './calendario/calendario.component';
import { ReportFacultyComponent } from './report-faculty/report-faculty.component';
import { ReportProgramComponent } from './report-program/report-program.component';
import { ReportSemestreComponent } from './report-semestre/report-semestre.component';
import { ReportTeacherComponent } from './report-teacher/report-teacher.component';
import { RoomComponent } from './room/room.component';
import { ScheduleManagerGuard } from 'src/app/guards/schedule-manager.guard';
import { AdminGuard } from 'src/app/guards/admin.guard';

const routes: Routes = [
    {
      path: '',
      data: {
        title: 'Reporte',
      },
      children: [
        {
          path: '',
          redirectTo: 'all',
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
          path: 'Ambiente',
          component: RoomComponent,
          data: {
            title: 'Ambiente'
          }
        },
        {
          //detalle y de un ambiente
          path: 'Report_faculty',
          canActivate:[ScheduleManagerGuard],
          component: ReportFacultyComponent,
          data: {
            title: 'Report_faculty'
          }
        },
        {
          //detalle y de un ambiente
          path: 'Programa',
          canActivate:[ScheduleManagerGuard],
          component: ReportProgramComponent,
          data: {
            title: 'Programa'
          }
        },
        {
          //detalle y de un ambiente
          path: 'Semestre',
          canActivate: [ AdminGuard ],
          component: ReportSemestreComponent,
          data: {
            title: 'Semestre'
          }
        },
        {
          //detalle y de un ambiente
          path: 'Docente',
          canActivate: [ AdminGuard ],
          component: ReportTeacherComponent,
          data: {
            title: 'Docente'
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
