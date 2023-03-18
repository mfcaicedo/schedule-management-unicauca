import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { OaUploadComponent } from './oa-upload/oa-upload.component';
import { ScheduleCreateComponent } from './schedule-create/schedule-create.component';
import { ScheduleDetailComponent } from './schedule-detail/schedule-detail.component';
import {ScheduleProfessorDetailComponent} from './schedule-professor-detail/schedule-professor-detail.component';
import { ScheduleUpdateComponent } from './schedule-update/schedule-update.component';

const routes: Routes = [
  {
    path: '',
    data: {
      title: 'schedule',
    },
    children: [
      {
        path:'',
        redirectTo:'/all',
        pathMatch:'full'
      },
      {
        path: 'all',
        component: ScheduleCreateComponent,
        data: {
          title: 'all',
        }
      },
      {

        path:'create',
        component:ScheduleCreateComponent,
        data:{
          title:'create'
        }
      },
      {
        path:'detail',
        component:ScheduleDetailComponent,
        data:{
          title:'detail'
        }
      },
      {
        path:'detailprofessor',
        component:ScheduleProfessorDetailComponent,
        data:{
          title:'detailprofessor'
        }
      },
      {
        path:'update/:ambienteId/:scheduleData',
        component: ScheduleUpdateComponent,
        data:{
          title :'updateSchedule'
        }
      },
      {
        path:'upload-oa',
        component: OaUploadComponent,
        data : {
          title:'upload-oa'
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
