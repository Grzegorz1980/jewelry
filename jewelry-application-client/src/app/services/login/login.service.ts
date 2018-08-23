import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  private serviceUrl = 'http://localhost:8080/login';

  constructor(private http: HttpClient) { }

  login(userName, password) {
    var data = {username: userName, password: password};
    return this.http.post(this.serviceUrl, data);
  }

  logout() {
    localStorage.removeItem('userToken');
  }
}
