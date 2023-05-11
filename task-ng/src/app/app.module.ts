import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppComponent} from './app.component';
import {MainWindowComponent} from './components/main-window/main-window.component';
import {RegisterComponent} from './components/register/register.component';
import {LoginComponent} from './components/login/login.component';
import {MyTasksComponent} from './components/my-tasks/my-tasks.component';
import {MyTaskPageComponent} from './components/task-page/my-task-page.component';
import { TaskDetailsComponent } from './components/task-details/task-details.component';
// import { EditComponent } from './components/edit/edit.component';
import { AddNewTaskComponent } from './components/add-new-task/add-new-task.component';

import {AppRoutingModule} from './app-routing.module';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {HttpClientModule} from "@angular/common/http";
import {JwtModule, JwtModuleOptions} from "@auth0/angular-jwt";
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';


import {DatePipe} from "@angular/common";

const JWT_Module_Options: JwtModuleOptions = {
  config: {
    tokenGetter: () => {
      return localStorage.getItem('token')
    }
  }
};

@NgModule({
  declarations: [
    AppComponent,
    MainWindowComponent,
    RegisterComponent,
    LoginComponent,
    MyTasksComponent,
    MyTaskPageComponent,
    TaskDetailsComponent,

    AddNewTaskComponent,

  ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        FormsModule,
        HttpClientModule,
        JwtModule.forRoot(JWT_Module_Options),
        BrowserAnimationsModule,
        ReactiveFormsModule,


    ],
  providers: [
    DatePipe
  ],
  bootstrap: [AppComponent],

})
export class AppModule {
}
