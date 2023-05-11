import {Component, OnInit} from '@angular/core';
import {Task} from "../../Task";
import {MyTaskPageService} from "../../services/my-task-page.service";


@Component({
  selector: 'app-my-task-page',
  templateUrl: './my-task-page.component.html',
  styleUrls: ['./my-task-page.component.css']
})

export class MyTaskPageComponent implements OnInit {

  tasks: Task[] = [];
  userName ?: string;

  constructor(private myTaskPageService: MyTaskPageService) {

  }

  ngOnInit(): void {
    this.getAllTasks();
    // getUserNameById();
  }

  getAllTasks(): void {
    this.myTaskPageService.getAllTasks().subscribe(
      returnedTask => this.tasks = returnedTask
    )
    // this.tasks.flatMap(task => task.userId = this.getUserNameById(task.userId))
  }

  getUserNameById(userId: number) {
    this.myTaskPageService.getUserNameById(userId).subscribe(
      returnedUser => this.userName = returnedUser
    )
  }

  // openViewPopUp() {
  //   console.log('view opened')
  //   this.referenceDialog.open(ViewComponent,
  //     {
  //       data :{
  //         name : "Nasr",
  //         tasks : this.getAllTasks()
  //       }
  //     })
  //
  // }

  // openEditPopUp() {
  //   console.log('edit-popup opened')
  //   this.referenceDialog.open(EditComponent);
  // }
}
