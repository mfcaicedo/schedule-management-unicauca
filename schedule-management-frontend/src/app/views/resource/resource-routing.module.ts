import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ResourcesAllComponent } from './resources-all/resources-all.component';
import { ResourcesCreateComponent } from './resources-create/resources-create.component';
import { ResourcesEditComponent } from './resources-edit/resources-edit.component';
import { ScheduleManagerGuard } from 'src/app/guards/schedule-manager.guard';

const routes: Routes = [
  {
    path: '',
    data: {
      title: 'Recurso',
    },
    children: [
      {
        path: '',
        redirectTo: 'all',
        pathMatch: 'full'
      },
      {
        path: 'all',
        component: ResourcesAllComponent,
        data: {
          title: 'Todos',
        }
      },
      {
        path: 'all/:resourceType',
        component: ResourcesAllComponent,
        data: {
          title: 'environmentType'
        }
      },
      {
        //detalle y de un ambiente
        path: 'create',
        canActivate:[ScheduleManagerGuard],
        component: ResourcesCreateComponent,
        data: {
          title: 'Crear'
        }
      },
      {
        path: 'edit/:resourceId',
        canActivate:[ScheduleManagerGuard],
        component: ResourcesEditComponent,
        data: {
          title: 'Editar'
        }
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ResourceRoutingModule { }
