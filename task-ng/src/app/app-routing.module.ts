import {MainWindowComponent} from "./components/main-window/main-window.component";
import {RegisterComponent} from "./components/register/register.component";
import {LoginComponent} from "./components/login/login.component";
import {MyTasksComponent} from "./components/my-tasks/my-tasks.component";
import {MyTaskPageComponent} from "./components/task-page/my-task-page.component";
import {TaskDetailsComponent} from "./components/task-details/task-details.component";
import {AddNewTaskComponent} from "./components/add-new-task/add-new-task.component";
import {SearchComponent} from "./components/search/search.component";

import {NgModule} from '@angular/core';
import {HttpClientModule} from "@angular/common/http";
import {RouterModule, Routes} from "@angular/router";

import { AuthGuardService as AuthGuard} from './guards/auth-guard.service';
import {LoginRegisterGuardService as LoginRegisterGuard} from "./guards/login-register-guard.service";
import {AddTaskGuard} from "./guards/add-task.guard";
import {MyTasksGuard} from "./guards/my-tasks.guard";
import {TasksPageGuard} from "./guards/tasks-page.guard";
import {TaskDetailsGuard} from "./guards/task-details.guard";

const routes: Routes = [
  {path: 'main-window', component: MainWindowComponent, canActivate: [AuthGuard]},
  {path: 'app-register', component: RegisterComponent, canActivate: [LoginRegisterGuard]},
  {path: 'app-login', component: LoginComponent, canActivate: [LoginRegisterGuard]},
  {path: 'app-my-tasks', component: MyTasksComponent,canActivate:[MyTasksGuard]},
  {path: 'app-my-task-page', component: MyTaskPageComponent,canActivate:[TasksPageGuard]},
  {path: 'task-details', component: TaskDetailsComponent,canActivate:[TaskDetailsGuard]},
  {path: 'task-details/:id', component: TaskDetailsComponent,canActivate:[TaskDetailsGuard]},
  {path: 'app-task-details', component: TaskDetailsComponent},
  {path: 'app-add-new-task', component: AddNewTaskComponent, canActivate:[AddTaskGuard]},
  {path: 'app-search', component: SearchComponent},

  {path: '', redirectTo: '/app-login', pathMatch: 'full'}, // this will make the app open on app-login component
  {path: '**', redirectTo: ''} // you can redirect it to '/app-login' if you want

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule, HttpClientModule]
})
export class AppRoutingModule {
}
