import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Jewel} from "../../models/jewel.model";
import {Observable} from "rxjs/index";

@Injectable({
  providedIn: 'root'
})
export class JewelryListService {

  private serviceUrl = 'http://localhost:8080/jewelry';

  constructor(private http: HttpClient) { }

  getJewelry(): Observable<Jewel[]> {
    return this.http.get<Jewel[]>(this.serviceUrl+ "/list");
  }

  getJewel(id: number | string): Observable<Jewel> {
    return this.http.get<Jewel>(this.serviceUrl + "/" + id);
  }
}
