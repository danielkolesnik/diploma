import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { CollapseModule, ModalModule } from "ngx-bootstrap";
import { NgMultiSelectDropDownModule } from 'ng-multiselect-dropdown';
import { FontAwesomeModule } from "@fortawesome/angular-fontawesome";

import { LayoutComponent } from './layout.component';
import { SearchComponent } from "./search/search.component";
import { ReportsComponent } from "./reports/reports.component";

import { LayoutRoutingModule } from './layout-routing.module';
import { StudentInfoModule } from "./student-info/student-info.module";
import { RegistrationModule } from "./registration/registration.module";
import { HeaderComponent } from "../components/header/header.component";
import { ReportComponent } from "../components/report/report.component";
import {ReportConfirmationComponent} from "../components/modals/report-confirmation/report-confirmation.component";

@NgModule({
  declarations: [
    LayoutComponent,
    HeaderComponent,
    SearchComponent,
    ReportConfirmationComponent,
    ReportComponent,
    ReportsComponent
  ],
  imports: [
    NgMultiSelectDropDownModule,
    FontAwesomeModule,
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    CollapseModule,
    ModalModule.forRoot(),
    LayoutRoutingModule,
    StudentInfoModule,
    RegistrationModule,
  ],
  entryComponents: [
    ReportConfirmationComponent,
  ]
})
export class LayoutModule { }
