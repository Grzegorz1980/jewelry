import { Injectable } from '@angular/core';
import {Jewel} from "../../models/jewel.model";
import {HttpClient} from "@angular/common/http";
import {ServerResponse} from "../../models/serverResponse.model";
import {Observable} from "rxjs/index";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private serviceUrl = 'http://localhost:8080/user/';

  constructor(private http: HttpClient) { }

  updatePassword(userId: string, password: string): Observable<ServerResponse> {
    return this.http.post<ServerResponse>(this.serviceUrl+userId+'/password', password);
  }
}
