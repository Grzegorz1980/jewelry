import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {AppSettings} from "../app-settings";

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  private serviceUrl = AppSettings.API_ENDPOINT + 'oauth/token';

  constructor(private http: HttpClient) {
  }

  login(username, password) {
      var data = "username=" + username + "&password=" + password + "&grant_type=password";
      var reqHeader = new HttpHeaders({'Authorization':'Basic YnJvd3Nlcjo=',
          'Content-Type':'application/x-www-form-urlencoded'});
      return this.http.post(this.serviceUrl , data, {headers: reqHeader});  }

  logout() {
    localStorage.removeItem('token');
    localStorage.removeItem('username');
  }
}
