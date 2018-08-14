import { Component, OnInit, ViewChild } from '@angular/core';
import {MatDialog, MatDialogRef} from '@angular/material';
import { UploadService } from '../../services/upload-file/upload.service';
import { ImportFileDialogComponent } from '../import-file-dialog/import-file-dialog.component';
import { forkJoin } from 'rxjs';

@Component({
  selector: 'app-import-file',
  templateUrl: './import-file.component.html',
  styleUrls: ['./import-file.component.css']
})
export class ImportFileComponent implements OnInit {

  constructor(public dialog: MatDialog, public uploadService: UploadService) {}

  public openUploadDialog() {
    let dialogRef = this.dialog.open(ImportFileDialogComponent, { width: '50%', height: '50%' });
  }

  ngOnInit(): void {
  }
}
