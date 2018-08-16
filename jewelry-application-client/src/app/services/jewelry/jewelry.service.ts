import {Injectable} from '@angular/core';
import {HttpClient, HttpRequest, HttpResponse} from "@angular/common/http";
import {Jewel} from "../../models/jewel.model";
import {Observable} from "rxjs/index";
import {ServerResponse} from "../../models/serverResponse.model";

@Injectable({
  providedIn: 'root'
})
export class JewelryService {

  private serviceUrl = 'http://localhost:8080/jewelry';

  constructor(private http: HttpClient) { }

  getJewelry(): Observable<Jewel[]> {
    return this.http.get<Jewel[]>(this.serviceUrl+ "/list");
  }

  getJewel(id: number | string): Observable<Jewel> {
    return this.http.get<Jewel>(this.serviceUrl + "/" + id);
  }

  saveJewel(jewel: Jewel): Observable<ServerResponse> {
    console.log("Saving jewel. SKU=" + jewel.sku);
    return this.http.post<ServerResponse>(this.serviceUrl, jewel).pipe();
  }
}
