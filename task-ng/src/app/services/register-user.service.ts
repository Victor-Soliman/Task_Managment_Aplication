import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {UserRegister} from "../interfaces/registerUser";

@Injectable({
  providedIn: 'root'
})
export class RegisterUserService {

  constructor(private httpClient: HttpClient) {
  }

  registerUser(userRegister: UserRegister): Observable<any> {
    return this.httpClient.post<UserRegister>("http://localhost:8080/user/register", userRegister);
  }

}
