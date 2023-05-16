import {Component} from '@angular/core';
import {AuthService} from "../../services/auth.service";

@Component({
  selector: 'app-main-window',
  templateUrl: './main-window.component.html',
  styleUrls: ['./main-window.component.css']
})
export class MainWindowComponent {
  constructor(private authService: AuthService) {
  }

  ngOnInit(): void {

  }

  logoutUser(): void {
    this.authService.logOut();
  }

}
