import {Component, Input, OnInit} from '@angular/core';
import {UserRegister} from "../../interfaces/registerUser";
import {RegisterUserService} from "../../services/register-user.service";
import {Router} from "@angular/router";


@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  userRegister: UserRegister = {};
  showErrMsg:boolean=false
  serverMsg:string=''

  constructor(private registerUserService: RegisterUserService,
              private router: Router) {
  }

  ngOnInit(): void {
  }

  registerUser() {
    this.registerUserService.registerUser(this.userRegister).subscribe({next:(response)=>{
      console.log(response.msg)
        alert('Successfully Registered')
        this.router.navigate(['/app-login'])
    },
      error:(err)=>{
      console.log(err.error.msg)
        this.serverMsg = err.error.msg
        this.showErrMsg = true;
      }
    })
  }
}



