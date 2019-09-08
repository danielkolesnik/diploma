import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import {CollapseModule} from "ngx-bootstrap";


import { LayoutComponent } from './layout.component';
import {SearchComponent} from "./search/search.component";

import { LayoutRoutingModule } from './layout-routing.module';
import {StudentInfoModule} from "./student-info/student-info.module";
import {RegistrationModule} from "./registration/registration.module";
import {HeaderComponent} from "../components/header/header.component";

@NgModule({
  declarations: [
    LayoutComponent,
    HeaderComponent,
    SearchComponent
  ],
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    CollapseModule,
    LayoutRoutingModule,
    StudentInfoModule,
    RegistrationModule,
  ]
})
export class LayoutModule { }
