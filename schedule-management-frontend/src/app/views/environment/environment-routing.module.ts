import { NgModule, importProvidersFrom } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { EnvironmentsComponent } from './environments/environments.component';
import { EnvironmentDetailComponent } from './environment-detail/environment-detail.component';
import { EnvironmentEditComponent } from './environment-edit/environment-edit.component';
import { EnvironmentUploadComponent } from './environment-upload/environment-upload.component';
import { EnvironmentDeleteComponent} from './environment-delete/environment-delete.component';
const routes: Routes = [
  {
    path: '',
    data: {
      title: 'Ambientes',
    },
    children: [
      {
        path: '',
        redirectTo: '/all',
        pathMatch: 'full'
      },
      {
        path: 'all',
        component: EnvironmentsComponent,
        data: {
          title: 'Todos',
        }
      },
      {
        path: 'all/:environmentType',
        component: EnvironmentsComponent,
        data: {
          title: 'Tipo de ambiente'
        }
      },
      {
        //detalle y de un ambiente
        path: 'create',
        component: EnvironmentDetailComponent,
        data: {
          title: 'Crear'
        }
      },
      {
        path: 'edit/:environmentId',
        component: EnvironmentEditComponent,
        data: {
          title: 'Editar'
        }
      },
      {
        path: 'upload-env',
        component: EnvironmentUploadComponent,
        data: {
          title: 'Cargar ambiente'
        }
      },
      {
        path: 'delete/:environmentId',
        component:EnvironmentDeleteComponent,
        data:{
          title: 'Eliminar'
        }
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class EnvironmentRoutingModule { }
