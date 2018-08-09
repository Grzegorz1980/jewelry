import { Injectable } from '@angular/core';
import { HttpClient }   from '@angular/common/http';
import { Observable }   from 'rxjs';
import { SourceFile } from '../models/source-file.model';
import { SourceFileDetail } from '../models/source-file-detail.model';

@Injectable({
  providedIn: 'root'
})
export class SourceFilesService {

  private serviceUrl = 'http://localhost:8080/source-files';

  constructor(private http: HttpClient) { }

  getFiles(): Observable<SourceFile[]> {
    return this.http.get<SourceFile[]>(this.serviceUrl);
  }

  getDetails(id: number | string): Observable<SourceFileDetail[]> {
    return this.http.get<SourceFileDetail[]>(this.serviceUrl + "/" + id);
  }
}
