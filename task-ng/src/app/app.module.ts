import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { MainWindowComponent } from './components/main-window/main-window.component';
import { AppRoutingModule } from './app-routing.module';
import { RegisterComponent } from './components/register/register.component';
import { LoginComponent } from './components/login/login.component';


import {FormsModule} from "@angular/forms";
import {HttpClientModule} from "@angular/common/http";
import {JwtModule, JwtModuleOptions} from "@auth0/angular-jwt";
import { MyTasksComponent } from './components/my-tasks/my-tasks.component';

const JWT_Module_Options: JwtModuleOptions = {
  config: {
    tokenGetter: ()=>{return localStorage.getItem('token')}
  }
};

@NgModule({
  declarations: [
    AppComponent,
    MainWindowComponent,
    RegisterComponent,
    LoginComponent,
    MyTasksComponent,

  ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        FormsModule,
      HttpClientModule,
      JwtModule.forRoot(JWT_Module_Options)
    ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
