import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {User} from "../interfaces/user";

@Injectable({
  providedIn: 'root'
})
export class LoginUserService {
  private url = "http://localhost:8080/user/login";

  constructor(private httpClient: HttpClient) {
  }


  loginUser(user: User): Observable<any> {
    return this.httpClient.post(`${this.url}`, user);
  }
}
