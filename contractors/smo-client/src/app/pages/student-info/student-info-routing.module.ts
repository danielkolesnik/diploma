import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { StudentInfoComponent } from './student-info.component';
import { PassportComponent } from './passport/passport.component';
import { NostrificationComponent } from './nostrification/nostrification.component';
import { DocumentComponent } from './document/document.component';
import { AuthGuard } from 'src/app/guards/auth.guard';

const routes: Routes = [
  {
    path: '',
    component: StudentInfoComponent,
    data: {student: 'pesos'},
    children: [
      {
        path: 'passport',
        component: PassportComponent,
      }, {
        path: 'nostrification',
        component: NostrificationComponent,
      }, {
        path: 'nostrification/add',
        component: DocumentComponent
      }
    ]
  }

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class StudentInfoRoutingModule { }
