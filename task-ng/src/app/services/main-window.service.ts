import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class MainWindowService {
  private url = "http://localhost:8080/user/logout";

  constructor(private httpClient: HttpClient) {
  }

  ngOnInit(): void {

  }

}
