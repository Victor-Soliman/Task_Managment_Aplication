import {Component, OnInit} from '@angular/core';
import {Task} from "../../Task";
import {Router} from "@angular/router";
import {User} from "../../user";
import {MyTaskPageService} from "../../services/my-task-page.service";
import {FormControl, FormGroup} from "@angular/forms";
import {Observable, of} from "rxjs";

@Component({
  selector: 'app-add-new-task',
  templateUrl: './add-new-task.component.html',
  styleUrls: ['./add-new-task.component.css']
})
export class AddNewTaskComponent implements OnInit {
  users: Observable<User[]> = of([]);
  tasks !: Task[];
  myForm !: FormGroup;
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
    this.myForm = new FormGroup({
      subject: new FormControl(''),
      dueDate: new FormControl(''),
      status: new FormControl(''),
      assignedToUser: new FormControl('')
    });
    this.users = this.getAllUsersToAssign();
  }


  addNewTask() {
    // this.task.subject = this.subject;
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

  // getAllUsersToAssign() {
  //   this.myTaskPageService.getAllUsers().subscribe(
  //     returnedUsers => this.users = returnedUsers
  //   )
  // }
  getAllUsersToAssign(): Observable<User[]> {
    return this.myTaskPageService.getAllUsers()
  }

  // helperMethod
  // setValueForForm(task: Task) {
  //   this.myForm.setValue({
  //     subject: task.subject,
  //     dueDate: task.dueDate,
  //     status: task.status,
  //     assignedToUser: task.clientUserName
  //   })
  // }

}
