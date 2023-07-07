import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {ReportFacultyComponent} from './report-faculty/report-faculty.component'
import { ReportEnvironmentComponent } from './report-environment/report-environment.component';
import {AuthGuard} from 'src/app/guards/auth.guard'

const routes: Routes = [
  {
    path: '',
    data: {
      title: 'reporte',
    },
    children:[
      {
        path: '',
        redirectTo: '/facultad',
        pathMatch: 'full'
      },
      {
        path: 'facultad',
        //component: ReportFacultyComponent,
        component:ReportEnvironmentComponent,
        data: {
          title: 'facultad',
        }
      },
      {

        path: 'programa',
        component: ReportEnvironmentComponent,
        data: {
          title: 'programa'
        }
      },
      {

        path: 'semestre',
        component: ReportEnvironmentComponent,
        data: {
          title: 'semestre'
        }
      },
      {

        path: 'profesor',
        component: ReportEnvironmentComponent,
        data: {
          title: 'profesor'
        }
      },
      {

        path: 'ambiente',
        component: ReportEnvironmentComponent,
        data: {
          title: 'ambiente'
        }
      },
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ReportRoutingModule {


 }
