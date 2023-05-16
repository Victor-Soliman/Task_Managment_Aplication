import {Component, OnInit} from '@angular/core';
import {Task} from "../../interfaces/Task";
import {MyTaskPageService} from "../../services/my-task-page.service";
import {FormGroup} from "@angular/forms";


@Component({
  selector: 'app-my-task-page',
  templateUrl: './my-task-page.component.html',
  styleUrls: ['./my-task-page.component.css']
})

export class MyTaskPageComponent implements OnInit {
  tasks: Task[] = [];
  userName ?: string;
  task!: Task;
  // for search
  showSearchComponent?: boolean;
  searchButtonLabel?: string;


  constructor(private myTaskPageService: MyTaskPageService) {
    this.showSearchComponent = false;
    this.searchButtonLabel = "Search";
  }

  ngOnInit(): void {
    this.getAllTasks();
  }

  getAllTasks(): void {
    this.myTaskPageService.getAllTasks().subscribe(
      returnedTask => this.tasks = returnedTask
    )
  }

  getUserNameById(userId: number) {
    this.myTaskPageService.getUserNameById(userId).subscribe(
      returnedUser => this.userName = returnedUser
    )
  }

  searchTask(searchParameters: FormGroup) {

    const searchCriteria: Task = {
      subject: searchParameters.get('subject')!.value,
      dueDate: searchParameters.get('dueDate')!.value,
      status: searchParameters.get('status')!.value,
      clientUserName: searchParameters.get('clientUserName')!.value
    }
    this.myTaskPageService.searchForTasks(searchCriteria).subscribe(
      response => {
        this.tasks = response
        if (searchCriteria.subject == null &&
          searchCriteria.dueDate == null &&
          searchCriteria.status == null &&
          searchCriteria.clientUserName == null){
          alert("No results for this searching criteria!")
        }
      });
  }


showSearchBox()
{
  this.showSearchComponent = !this.showSearchComponent;
  this.searchButtonLabel = this.showSearchComponent ? 'Hide' : 'Search';
}

}
