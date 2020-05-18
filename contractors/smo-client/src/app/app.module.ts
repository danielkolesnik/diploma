import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing.module';
import { LoginComponent } from './pages/login/login.component';
import { LoginModule } from './pages/login/login.module';
import { NotFoundComponent } from './pages/not-found/not-found.component';
import { LayoutModule } from './pages/layout.module';
import { StudentService } from './services/student/student.service';
import { httpInterceptorProviders } from './interceptors';
import { BrowserAnimationsModule } from "@angular/platform-browser/animations";


@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    NotFoundComponent,
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    HttpClientModule,
    LoginModule,
    LayoutModule,
    AppRoutingModule,
  ],
  providers: [StudentService, httpInterceptorProviders],
  bootstrap: [AppComponent]
})
export class AppModule { }
