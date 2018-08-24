import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {headersToString} from "selenium-webdriver/http";

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  private serviceUrl = 'http://localhost:8080/oauth/token';

  constructor(private http: HttpClient) {
  }

  login(username, password) {
      var data = "username=" + username + "&password=" + password + "&grant_type=password";
      var reqHeader = new HttpHeaders({'Authorization':'Basic YnJvd3Nlcjo=',
          'Content-Type':'application/x-www-form-urlencoded'});
      return this.http.post(this.serviceUrl , data, {headers: reqHeader});  }

  logout() {
    localStorage.removeItem('token');
  }
}
