import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { OaUploadComponent } from './oa-upload/oa-upload.component';
import { OaViewFilesComponent } from './oa-view-files/oa-view-files.component';
import { ScheduleCreateComponent } from './schedule-create/schedule-create.component';
import { ScheduleDetailComponent } from './schedule-detail/schedule-detail.component';
import { ScheduleProfessorDetailComponent } from './schedule-professor-detail/schedule-professor-detail.component';
import { ScheduleUpdateComponent } from './schedule-update/schedule-update.component';
import { ScheduleReserveCreateComponent } from './schedule-reserve-create/schedule-reserve-create.component';
import { AdminGuard } from 'src/app/guards/admin.guard';
import { ScheduleManagerGuard } from 'src/app/guards/schedule-manager.guard';

import { ScheduleReserveConsultComponent } from './schedule-reserve-consult/schedule-reserve-consult.component';
const routes: Routes = [
  {
    path: '',
    data: {
      title: 'Oferta académica',
    },
    children: [
      {
        path: '',
        redirectTo: 'all',
        pathMatch: 'full'
      },
      {
        path: 'all',
        canActivate:[ScheduleManagerGuard],
        component: ScheduleCreateComponent,
        data: {
          title: 'all',
        }
      },
      {

        path: 'create',
        canActivate:[ScheduleManagerGuard],
        component: ScheduleCreateComponent,
        data: {
          title: 'create'
        }
      },
      {
        path: 'reserve',
        canActivate:[ScheduleManagerGuard],
        component: ScheduleReserveCreateComponent,
        data: {
          title: 'reserve'
        }
      },
      {
        path: 'consultreserve',
        component: ScheduleReserveConsultComponent,
        data: {
          title: 'consultreserve'
        }
      },
      {
        path: 'detail',
        component: ScheduleDetailComponent,
        data: {
          title: 'detail'
        }
      },
      {
        path: 'detailprofessor',
        component: ScheduleProfessorDetailComponent,
        data: {
          title: 'detailprofessor'
        }
      },
      {
        path: 'update/:ambienteId/:scheduleData',
        canActivate:[ScheduleManagerGuard],
        component: ScheduleUpdateComponent,
        data: {
          title: 'updateSchedule'
        }
      },
      {
        path: 'oa/upload-oa',
        canActivate: [ AdminGuard ],
        component: OaUploadComponent,
        data: {
          title: 'Subir oferta'
        }
      },
      {
        path: 'oa/view-files-oa',
        component: OaViewFilesComponent,
        data: {
          title: 'Ver archivos '
        }
      }
    ]
  }

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ScheduleRoutingModule {

}
