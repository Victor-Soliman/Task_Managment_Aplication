import {Injectable} from '@angular/core';
import {User} from "../user";
import {Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";
import {AuthService} from "./auth.service";

@Injectable({
  providedIn: 'root'
})
export class MainWindowService {
  private url = "http://localhost:8080/user/logout";

  constructor(private httpClient: HttpClient) {
  }

  ngOnInit(): void {

  }


}
