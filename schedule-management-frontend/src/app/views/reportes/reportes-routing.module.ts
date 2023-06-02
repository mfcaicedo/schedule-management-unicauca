import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
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
        },/*
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