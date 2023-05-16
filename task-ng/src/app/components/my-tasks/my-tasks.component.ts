import {Component, OnInit} from '@angular/core';
import {MyTasksService} from "../../services/my-tasks.service";
import {Task} from "../../interfaces/Task";

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
  }

  getAllTasksForUser(): void {
    this.myTasksService.getTasksForUser().subscribe(
      returnedTasks => this.tasks = returnedTasks
    )
  }
}
