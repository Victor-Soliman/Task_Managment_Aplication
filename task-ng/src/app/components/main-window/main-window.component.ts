import {Component} from '@angular/core';
import {MainWindowService} from "../../services/main-window.service";
import {Router} from "@angular/router";
import {UserRegister} from "../../registerUser";
import {AuthService} from "../../services/auth.service";

@Component({
  selector: 'app-main-window',
  templateUrl: './main-window.component.html',
  styleUrls: ['./main-window.component.css']
})
export class MainWindowComponent {
  loggedInUser: UserRegister = {};

  constructor(private authService: AuthService,
              private mainWindowService: MainWindowService,
              private router: Router) {
  }

  ngOnInit(): void {

  }

  goToMainWindow() {

  }

  logoutUser(): void {
    // return this.httpClient.get(`${this.url}`);
    this.authService.logOut();
  }

  showAllTasks() {
    console.log(1111);
  }
}
