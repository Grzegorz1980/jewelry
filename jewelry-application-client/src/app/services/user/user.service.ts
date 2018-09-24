import { Injectable } from '@angular/core';
import {Jewel} from "../../models/jewel.model";
import {HttpClient} from "@angular/common/http";
import {ServerResponse} from "../../models/serverResponse.model";
import {Observable} from "rxjs/index";
import {AppSettings} from "../app-settings";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private serviceUrl = AppSettings.API_ENDPOINT + 'user/';

  constructor(private http: HttpClient) { }

  updatePassword(userId: string, password: string): Observable<ServerResponse> {
    return this.http.post<ServerResponse>(this.serviceUrl+userId+'/password', password);
  }
}
