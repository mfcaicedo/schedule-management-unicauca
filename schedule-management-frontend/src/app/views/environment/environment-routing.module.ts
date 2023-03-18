import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { EnvironmentsComponent } from './environments/environments.component';
import { EnvironmentDetailComponent } from './environment-detail/environment-detail.component';
import { EnvironmentEditComponent } from './environment-edit/environment-edit.component';
const routes: Routes = [
  {
    path: '',
    data: {
      title: 'Environment',
    },
    children: [
      {
        path:'',
        redirectTo:'/all',
        pathMatch:'full'
      },
      {
        path: 'all',
        component: EnvironmentsComponent,
        data: {
          title: 'all',
        }
      },
      {
        path:'all/:environmentType',
        component:EnvironmentsComponent,
        data:{
          title:'environmentType'
        }
      },
      {
        //detalle y de un ambiente
        path:'create',
        component:EnvironmentDetailComponent,
        data:{
          title:'create'
        }
      },
      {
        path:'edit/:environmentId',
        component:EnvironmentEditComponent,
        data:{
          title:'edit'
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
