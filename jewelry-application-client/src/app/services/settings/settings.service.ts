import { Injectable } from '@angular/core';
import {Observable} from "rxjs/index";
import {HttpClient} from "@angular/common/http";
import {ServerResponse} from "../../models/serverResponse.model";
import {AppSettings} from "../app-settings";
import {Settings} from "../../models/settings.model";
import {Jewel} from "../../models/jewel.model";

@Injectable({
  providedIn: 'root'
})
export class SettingsService {

  private serviceUrl = AppSettings.API_ENDPOINT + 'settings';

  constructor(private http: HttpClient) { }

  getSettings(): Observable<Settings> {
    return this.http.get<Settings>(this.serviceUrl);
  }

  saveSettings(settings: Settings): Observable<ServerResponse> {
    console.log("Saving settings. usdRate=" + settings.usdRate + ", promoRate=" + settings.promoRate + ", regularRate=" + settings.regularRate);
    return this.http.post<ServerResponse>(this.serviceUrl, settings);
  }
}
