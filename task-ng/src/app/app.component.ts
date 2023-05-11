import {Component} from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  bodyText = 'This text can be updated in modal 1';

  constructor() { }
  ngOnInit() {

  }
}
