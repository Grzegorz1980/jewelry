import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {saveAs} from 'file-saver/FileSaver';

const url = 'http://localhost:8080/generateFile';

@Injectable({
  providedIn: 'root'
})
export class DownloadFileService {

  constructor(private http: HttpClient) {
  }

  public generate() {
    this.http.get(url, {responseType: 'blob', observe: 'response'})
      .subscribe(res => {
        const contentDispositionHeader: string = res.headers.get('Content-Disposition');
        const parts: string[] = contentDispositionHeader.split(';');
        const filename = parts[1].split('=')[1];
        console.log("Downloaded file. FileName = " + filename);
        saveAs(res.body, filename);
      });
  }
}
