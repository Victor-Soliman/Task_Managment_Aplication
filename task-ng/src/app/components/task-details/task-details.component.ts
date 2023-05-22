import {Component, Input, OnInit} from '@angular/core';
import {MyTaskPageService} from "../../services/my-task-page.service";
import {Task} from "../../interfaces/Task";
import {ActivatedRoute, Router} from "@angular/router";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {DatePipe} from "@angular/common";
import {Observable, of} from "rxjs";
import {User} from "../../interfaces/user";
import {Status} from "../../interfaces/Status";

@Component({
  selector: 'app-task-details',
  templateUrl: './task-details.component.html',
  styleUrls: ['./task-details.component.css']
})
export class TaskDetailsComponent implements OnInit {
  myForm !: FormGroup;
  task !: Task;
  users: Observable<User[]> = of([]);
  statuses = Object.values(Status);
  // for edit
  editMode: boolean = false;
  // for delete
  private status?: string;
  private errorMessage?: string;


  constructor(private myTaskPageService: MyTaskPageService,
              private route: ActivatedRoute,
              private router: Router,
              private datePipe: DatePipe) {
  }

  ngOnInit(): void {
    this.myForm = new FormGroup({
      id: new FormControl(''),
      status: new FormControl(''),
      dueDate: new FormControl(''),
      subject: new FormControl('', [Validators.required, Validators.maxLength(100)]),
      assignedUserName: new FormControl('')
    });
    this.myForm.disable()
    this.getTaskById()
    this.users = this.getAllUsersToAssign();
  }

  getTaskById() {
    const id: number = Number(this.route.snapshot.paramMap.get('id')) // imi ia din taskul vinit de pe back end (id-ul)
    console.log(id)
    this.myTaskPageService.getTaskById(id).subscribe(
      returnedTask => this.setValuesForForm(returnedTask)
    )
  }

  getAllUsersToAssign(): Observable<User[]> {
    return this.myTaskPageService.getAllUsers()
  }

  deleteTask() {
    const id: number = Number(this.route.snapshot.paramMap.get('id'))
    if(confirm("Are you sure you want to delete this task?"))

      this.myTaskPageService.deleteTaskById(id).subscribe({
      next: data => {
        this.status = 'Delete successful';
        alert("Task deleted successfully")
        this.router.navigate(['app-my-task-page'])
      },
      error: error => {
        this.errorMessage = error.message;
        console.error('There was an error!', error);
      }
    });
    console.log('deleted')
  }

  // helper methods
  setValuesForForm(task: Task) {
    this.myForm.setValue({
      id: task.id,
      status: task.status,
      dueDate: this.datePipe.transform(task?.dueDate, "yyyy-MM-dd"),
      subject: task.subject,
      assignedUserName: task.clientUserName
    });
  }

  onEdit() {
    this.editMode = true;
    this.myForm.enable();
  }

  onSubmit() {
    const updatedTask: Task = {
      id: this.myForm.get('id')?.value,
      subject: this.myForm.get('subject')?.value,
      status: this.myForm.get('status')?.value,
      dueDate: this.myForm.get('dueDate')?.value,
      clientUserName: this.myForm.get('assignedUserName')?.value
    }
    console.log(updatedTask)
    this.myTaskPageService.editTask(updatedTask).subscribe(response => {
      console.log('success')
      this.editMode = false;
      this.myForm.disable();
      alert("Task Edited Successfully")
    })

  }
}


