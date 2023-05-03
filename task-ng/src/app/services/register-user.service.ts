import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {UserRegister} from "../registerUser";

@Injectable({
  providedIn: 'root'
})
export class RegisterUserService {

  private registerUrl = "http://localhost:8080/user/register";
  // httpOptions: { headers: HttpHeaders }  = {
  //   headers: new HttpHeaders({
  //     'Content-Type': 'application/json'
  //   })
  // }

  constructor(private httpClient: HttpClient) {
  }

  ngOnInit(): void {
  }

  registerUser(userRegister: UserRegister): Observable<UserRegister> {
    return this.httpClient.post<UserRegister>("http://localhost:8080/user/register", userRegister);
  }
}
