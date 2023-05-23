import { NgModule, importProvidersFrom } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SubjectUploadComponent } from './subject-upload/subject-upload.component';
import { SubjectAllComponent } from './subject-all/subject-all.component';

const routes: Routes = [
  {
    path: '',
    data: {
      title: 'Asignaturas',
    },
    children: [
      {
        path: '',
        redirectTo: '/all',
        pathMatch: 'full'
      },
      {
        path: 'all',
        component: SubjectAllComponent,
        data: {
          title: 'Todos',
        }
      },
      {
        path: 'upload-sub',
        component: SubjectUploadComponent,
        data: {
          title: 'Cargar asignaturas',
        }
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class SubjectRoutingModule { }