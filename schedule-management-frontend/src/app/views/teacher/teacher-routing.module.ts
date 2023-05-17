import { NgModule, importProvidersFrom } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { TeachersComponent } from './teachers/teacher-detail.component';
import { TeacherUploadComponent } from './teacher-upload/teacher-upload.component';
const routes: Routes = [
  {
    path: '',
    data: {
      title: 'Profesores',
    },
    children: [
      // {
      //   path: '',
      //   redirectTo: '/all',
      //   pathMatch: 'full'
      // },
      {
        path: 'all',
        component: TeachersComponent,
        data: {
          title: 'Todos',
        }
      },
      {
        path: 'upload-teacher',
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
