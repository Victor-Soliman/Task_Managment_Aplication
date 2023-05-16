import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {Task} from "../../interfaces/Task";
import {User} from "../../interfaces/user";
import {Observable, of, Subscription} from "rxjs";
import {MyTaskPageService} from "../../services/my-task-page.service";
import {FormBuilder, FormControl, FormGroup} from "@angular/forms";

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {

  @Output() searchEvent = new EventEmitter<FormGroup>;
  searchForm!: FormGroup;
  users?: Observable<User[]> = of([]);
  tasks: Task[] = [];


  constructor(private myTaskPageService: MyTaskPageService,
              private formBuilder: FormBuilder) {
    this.searchForm = this.formBuilder.group({
      subject: undefined,
      dueDate: undefined,
      status: undefined,
      clientUserName: undefined
    })
  }

  ngOnInit(): void {
    this.users = this.getAllUsersToAssign();
  }

  onSubmit(event: Event) {
    event.preventDefault();
    this.searchEvent.emit(this.searchForm);
  }

  getAllUsersToAssign(): Observable<User[]> {
    return this.myTaskPageService.getAllUsers()
  }

  onReset() {
    this.searchForm.reset()
     this.myTaskPageService.getAllTasks().subscribe(
      returnedTask => this.tasks = returnedTask
    )
  }
}
