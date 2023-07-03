import { NgModule, importProvidersFrom } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { TeachersComponent } from './teachers/teacher-detail.component';
import { TeacherUploadComponent } from './teacher-upload/teacher-upload.component';
import { AdminGuard } from 'src/app/guards/admin.guard';
import { ScheduleManagerGuard } from 'src/app/guards/schedule-manager.guard';
const routes: Routes = [
  {
    path: '',
    data: {
      title: 'Profesores',
    },
    children: [
      {
        path: '',
        redirectTo: 'all',
        pathMatch: 'full'
      },
      {
        path: 'all',
        component: TeachersComponent,

        data: {
          title: 'Todos',
        }
      },
      {
        path: 'upload-teacher',
        canActivate: [ AdminGuard ],
        component: TeacherUploadComponent,
        data: {
          title: 'Cargar docentes'
        }
      }

    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class TeacherRoutingModule { }
