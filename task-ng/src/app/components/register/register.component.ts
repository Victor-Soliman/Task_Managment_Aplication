import {Component, Input, OnInit} from '@angular/core';
import {UserRegister} from "../../registerUser";
import {User} from "../../user";
import {mockUserRegister} from "../../mock-userRegister";
import {RegisterUserService} from "../../services/register-user.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  // user: User | undefined;

  // @Input() userRegister: UserRegister = {username: '', email: '', password: ''}
  userRegister: UserRegister = {}; // interfata o sa o pasez in functia de mai jos ,
  // pe care o chem din service

  constructor(private registerUserService: RegisterUserService, private router: Router) {
    // this.userRegister = mockUserRegister;
  }

  ngOnInit(): void {

  }

  registerUser() {
    console.log(this.userRegister.username,
      this.userRegister.email,
      this.userRegister.password);
    // check if this object (userRegister) has anything in it - > do this

    this.registerUserService.registerUser(this.userRegister).subscribe(
      responce => {
        console.log(responce)
        if (responce) {
          this.router.navigate(['login']);
          alert("Register Successfully")
        }
      }
      // inputData => {
      //             alert("Register Successfully")
      // }
    );

    // in order to empty the form after login
    // const newUser = {
    //   userName: this.userRegister.username,
    //   email: this.userRegister.email,
    //   password: this.userRegister.password
    // }
  }

}
