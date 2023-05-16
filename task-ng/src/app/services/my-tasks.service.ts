import {Injectable} from '@angular/core';
import {Observable} from "rxjs";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Task} from "../interfaces/Task"

@Injectable({
  providedIn: 'root'
})
export class MyTasksService {

  private TASKS_FROM_USER_URL: string = "http://localhost:8080/task/all";
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
      this.TASKS_FROM_USER_URL + "/" + localStorage.getItem("email"),
      this.httpOptions);
  }
}
