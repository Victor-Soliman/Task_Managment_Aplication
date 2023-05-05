import {Component, OnInit} from '@angular/core';
import {MyTasksService} from "../../services/my-tasks.service";
import {MainWindowComponent} from "../main-window/main-window.component";
import {Task} from "../../Task";

@Component({
  selector: 'app-my-tasks',
  templateUrl: './my-tasks.component.html',
  styleUrls: ['./my-tasks.component.css']
})
export class MyTasksComponent implements OnInit {
  tasks: Task[] = [];

  constructor(private myTasksService: MyTasksService) {
  }

  ngOnInit() {
    this.getAllTasksForUser();
    console.log(this.tasks);
  }

  getAllTasksForUser(): void {
    this.myTasksService.getTasksForUser().subscribe(
      returnedTasks => this.tasks = returnedTasks
    )
  }


}
