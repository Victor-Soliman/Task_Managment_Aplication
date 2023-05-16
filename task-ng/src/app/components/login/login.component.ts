import {Component, Input, OnInit} from '@angular/core';
import {User} from "../../interfaces/user";
import {LoginUserService} from "../../services/login-user.service";
import {FormBuilder} from "@angular/forms";
import {Router} from "@angular/router";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  @Input() user: User = {email: '', password: ''};

  constructor(private userService: LoginUserService,
              private formBuilder: FormBuilder,
              private router: Router) {
  }

  ngOnInit() {
  }


  userLogin() {
    console.log(this.user);

    return this.userService.loginUser(this.user).subscribe(
      inputData => {
        alert("Login Successfully")

        const token = inputData.accessToken;
        console.log(token);

        localStorage.setItem('token', token);
        // localStorage.setItem('username', this.user.username); // cand schimbi catre email
        localStorage.setItem('email', this.user.email);
        this.router.navigate(['app-my-tasks']);
      }
      , error => alert("Please Enter correct credentials"));
  }
}
