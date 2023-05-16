import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {Task} from "../interfaces/Task";
import {User} from "../interfaces/user";

@Injectable({
  providedIn: 'root'
})
export class MyTaskPageService {

  private MY_PAGE_TASK_URL: string = "http://localhost:8080/task";
  private GET_TASK_BY_ID: string = "http://localhost:8080/task/task-details";
  private GET_ALL_USERS: string = "http://localhost:8080/user/all";
  private SEARCH_TASKS: string = "http://localhost:8080/task/search"

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
    })
  }

  constructor(private httpClient: HttpClient) {
  }


  getAllTasks(): Observable<Task[]> {
    return this.httpClient.get<Task[]>(
      this.MY_PAGE_TASK_URL + "/all")
  }

  getUserNameById(id: number) {
    return this.httpClient.get<string>(
      this.MY_PAGE_TASK_URL + "/all/" + id
    )
  }

  getTaskById(id: number): Observable<Task> {
    // console.log(`${id}`)
    return this.httpClient.get<Task>(
      `${this.GET_TASK_BY_ID}/${id}`, this.httpOptions
    )

  }

  addTask(task: Task): Observable<Task> {
    return this.httpClient.post<Task>(
      this.MY_PAGE_TASK_URL + "/add", task
    )
  }

  editTask(task: Task): Observable<Task> {
    return this.httpClient.patch<Task>(
      this.MY_PAGE_TASK_URL + "/edit", task
    )
  }

  searchForTasks(task: Task): Observable<Task[]> {
    return this.httpClient.post<Task[]>(
      this.SEARCH_TASKS, task
    )
  }

  deleteTaskById(id: number): Observable<Task> {
    return this.httpClient.delete(`${this.MY_PAGE_TASK_URL}/${id}`)
  }

  getAllUsers(): Observable<User[]> {
    return this.httpClient.get<User[]>(`${this.GET_ALL_USERS}`, this.httpOptions)
  }
}
