import {Component, Input, OnInit} from '@angular/core';
import {User} from "../../user";
import {LoginUserService} from "../../services/login-user.service";
import {UserRegister} from "../../registerUser";
// import {mockUser} from "../../mock-user";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Router} from "@angular/router";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
// we create an object of type User
//   user: User = new User();
  @Input() user: User = {username: '', password: ''};
  public formValidate !: FormGroup;
  public isInvalid !: boolean;

  constructor(private userService: LoginUserService,
              private formBuilder: FormBuilder,
              private router: Router) {
    // localStorage.clear();
  }

  ngOnInit() {
    // this.userLogin();
    // this.formValidate = this.formBuilder.group({
    //   username: [this.user.username, Validators.required],
    //   password: [this.user.password, Validators.required]
    // })
  }


  userLogin() {
    console.log(this.user);

    return this.userService.loginUser(this.user).subscribe(
      inputData => {
        alert("Login Successfully")


        const token = inputData.accessToken;
        console.log(token);

        localStorage.setItem('token', token);
        this.router.navigate(['main-window']);

      }
      , error => alert("Please Enter correct credentials"));

    // in order to empty the form after login
    const newUser = {
      userName: this.user.username,
      password: this.user.password
    }

  }

  userRegister(): void {
    console.log(this.user);
  }


}
