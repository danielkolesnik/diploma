import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { StudentInfoRoutingModule } from './student-info-routing.module';
import { StudentInfoComponent } from './student-info.component';
import { PassportComponent } from './passport/passport.component';
import { NostrificationComponent } from './nostrification/nostrification.component';
import { DocumentComponent } from './document/document.component';
import { PersonalDataComponent } from './personal-data/personal-data.component';
import { FormsModule } from '@angular/forms';

@NgModule({
  declarations: [
    StudentInfoComponent,
    PassportComponent,
    NostrificationComponent,
    DocumentComponent,
    PersonalDataComponent
  ],
  imports: [
    CommonModule,
    FormsModule,
    StudentInfoRoutingModule
  ]
})
export class StudentInfoModule { }
