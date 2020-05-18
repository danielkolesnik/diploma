import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LayoutComponent } from './layout.component';
import { AuthGuard } from 'src/app/guards/auth.guard';

import {SearchComponent} from './search/search.component';
import {ReportsComponent} from './reports/reports.component';
import {StudentInfoComponent} from './student-info/student-info.component';
import {RegistrationComponent} from './registration/registration.component';

const routes: Routes = [
  {
    path: '',
    component: LayoutComponent,
    canActivate: [AuthGuard],
    children: [
      {
        path: '',
        pathMatch: 'full',
        redirectTo: 'search',
      },
      {
        path: 'search',
        component: SearchComponent
      },
      {
        path: 'student-info',
        component: StudentInfoComponent,
      },
      {
        path: 'registration',
        component: RegistrationComponent
      },
      {
        path: 'reports',
        component: ReportsComponent
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class LayoutRoutingModule { }
