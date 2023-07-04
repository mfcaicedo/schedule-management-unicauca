import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { DefaultLayoutComponent } from './containers';
import { Page404Component } from './views/pages/page404/page404.component';
import { Page500Component } from './views/pages/page500/page500.component';
import { LoginComponent } from './views/pages/login/login.component';
import { RegisterComponent } from './views/pages/register/register.component';
import { ScheduleModule } from './views/schedule/schedule.module';
import { ToshareModule } from './views/toshare/toshare.module';
import { ReportModule } from './views/report/report.module';
import { OpenSesionGuard } from './guards/open-sesion.guard'
import { AuthGuard } from './guards/auth.guard';
import {AdminGuard} from './guards/admin.guard'
import {ScheduleManagerGuard} from './guards/schedule-manager.guard'
import { CloseSessionGuard } from './guards/close-session.guard'
import { ChangePasswordComponent } from './views/pages/change-password/change-password.component';

const routes: Routes = [
  {
    path: '',
    redirectTo: 'login',
    pathMatch: 'full'
  },
  {
    path: 'login',
    component: LoginComponent,
    //canActivate: [CloseSessionGuard],
    data: {
      title: 'Login Page'
    }
  },
  {
    path: 'change-password/:id',
    component: ChangePasswordComponent,

    data: {
      title: 'change password'
    }
  },
  {
    path: '',
    component: DefaultLayoutComponent,
    //canActivate: [OpenSesionGuard],
    canActivate:[AuthGuard],
    data: {
      title: 'Inicio'
    },
    children: [
      {
        path: 'teacher',
        loadChildren: () =>
          import('./views/teacher/teacher.module').then(m => m.TeacherModule)
      },
      {
        path: 'subject',

        loadChildren: () =>
          import('./views/subject/subject.module').then(m => m.SubjectModule)
      },
      {
        path: 'environment',

        loadChildren: () =>
          import('./views/environment/environment.module').then(m => m.EnvironmentModule)
      },
      {
        path: 'resource',
        loadChildren: () =>
          import('./views/resource/resource.module').then(m => m.ResourceModule)
      },
      {
        path:'reportes',
        loadChildren: ()=>
        import('./views/reportes/reportes.module').then(m=>m.ReportesModule)
      },
      {
        path: 'schedule',

        loadChildren: () =>
          import('./views/schedule/schedule.module').then(m => m.ScheduleModule)
      },
      {
        path: 'toshare',
        loadChildren: () =>
          import('./views/toshare/toshare.module').then(m => m.ToshareModule)
      },
      {
        path: 'reporte',
        loadChildren: () =>
          import('./views/report/report.module').then(m => m.ReportModule),

      },
      {
        path: 'dashboard',
        loadChildren: () =>
          import('./views/dashboard/dashboard.module').then((m) => m.DashboardModule)
      },
      {
        path: 'theme',
        loadChildren: () =>
          import('./views/theme/theme.module').then((m) => m.ThemeModule)
      },
      {
        path: 'base',
        loadChildren: () =>
          import('./views/base/base.module').then((m) => m.BaseModule)
      },
      {
        path: 'buttons',
        loadChildren: () =>
          import('./views/buttons/buttons.module').then((m) => m.ButtonsModule)
      },
      {
        path: 'forms',
        loadChildren: () =>
          import('./views/forms/forms.module').then((m) => m.CoreUIFormsModule)
      },
      {
        path: 'charts',
        loadChildren: () =>
          import('./views/charts/charts.module').then((m) => m.ChartsModule)
      },
      {
        path: 'icons',
        loadChildren: () =>
          import('./views/icons/icons.module').then((m) => m.IconsModule)
      },
      {
        path: 'notifications',
        loadChildren: () =>
          import('./views/notifications/notifications.module').then((m) => m.NotificationsModule)
      },
      {
        path: 'widgets',
        loadChildren: () =>
          import('./views/widgets/widgets.module').then((m) => m.WidgetsModule)
      },
      {
        path: 'pages',
        loadChildren: () =>
          import('./views/pages/pages.module').then((m) => m.PagesModule)
      },
    ]
  },
  {
    path: '404',
    component: Page404Component,
    data: {
      title: 'Page 404'
    }
  },
  {
    path: '500',
    component: Page500Component,
    data: {
      title: 'Page 500'
    }
  },
  // {
  //   path: 'login',
  //   component: LoginComponent,
  //   canActivate: [CloseSessionGuard],
  //   data: {
  //     title: 'Login Page'
  //   }
  // },
  {
    path: 'register',
    component: RegisterComponent,
    data: {
      title: 'Register Page'
    }
  },
  { path: '**', redirectTo: 'login' }
];

@NgModule({
  imports: [
    RouterModule.forRoot(routes, {
      scrollPositionRestoration: 'top',
      anchorScrolling: 'enabled',
      initialNavigation: 'enabledBlocking'
      // relativeLinkResolution: 'legacy'
    })
  ],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
