import {Component, OnInit} from '@angular/core';
import {Task} from "../../interfaces/Task";
import {Router} from "@angular/router";
import {User} from "../../interfaces/user";
import {MyTaskPageService} from "../../services/my-task-page.service";
import {Observable, of} from "rxjs";

@Component({
  selector: 'app-add-new-task',
  templateUrl: './add-new-task.component.html',
  styleUrls: ['./add-new-task.component.css']
})
export class AddNewTaskComponent implements OnInit {
  users: Observable<User[]> = of([]);
  tasks !: Task[];
  task: Task = {
    subject: '',
    dueDate: new Date(),
    status: undefined,
    clientUserName: ''
  }

  constructor(private router: Router,
              private myTaskPageService: MyTaskPageService) {
  }

  ngOnInit(): void {
    this.users = this.getAllUsersToAssign();
  }


  addNewTask() {
    console.log(this.task)
    this.myTaskPageService.addTask(this.task).subscribe(
      responce => {
        console.log(responce)
        if (responce) {
          alert('Task added successfully')
          this.router.navigate(['app-my-task-page'])
        }
      })
  }

  getAllUsersToAssign(): Observable<User[]> {
    return this.myTaskPageService.getAllUsers()
  }


}
