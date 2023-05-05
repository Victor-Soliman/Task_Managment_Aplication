import {Injectable} from '@angular/core';
import {Observable} from "rxjs";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Task} from "../Task"
import {User} from "../user";

@Injectable({
  providedIn: 'root'
})
export class MyTasksService {

  private tasks_FROM_USER_URL: string = "http://localhost:8080/task/all";
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
      // 'Authorization': `Bearer ${localStorage.getItem('token')}`
    }),
  };
  constructor(private httpClient: HttpClient) {
  }

  getTasksForUser(): Observable<Task[]> {
    return this.httpClient.get<Task[]>(
      this.tasks_FROM_USER_URL + "/" + localStorage.getItem("username"),
      this.httpOptions);
  }
}
