import {NgModule} from '@angular/core';
import {MainWindowComponent} from "./components/main-window/main-window.component";
import {RouterModule, Routes} from "@angular/router";
import {RegisterComponent} from "./components/register/register.component";
import {LoginComponent} from "./components/login/login.component";
import {HttpClientModule} from "@angular/common/http";
import {
  AuthGuardService as AuthGuard
} from './services/auth-guard.service';
import {LoginRegisterGuardService as LoginRegisterGuard} from "./services/login-register-guard.service";


const routes: Routes = [
  {path: 'main-window', component: MainWindowComponent, canActivate: [AuthGuard]},
  {path: 'app-register', component: RegisterComponent, canActivate: [LoginRegisterGuard]},
  {path: 'app-login', component: LoginComponent, canActivate: [LoginRegisterGuard]},
  {path: '', redirectTo: '/app-login', pathMatch: 'full'}, // this will make the app open on app-login component

  {path: '**', redirectTo: ''}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule, HttpClientModule]
})
export class AppRoutingModule {
}
