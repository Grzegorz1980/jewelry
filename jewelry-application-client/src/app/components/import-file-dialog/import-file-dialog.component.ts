import {Component, OnInit, ViewChild} from '@angular/core';
import {MatDialogRef} from '@angular/material';
import {UploadService} from '../../services/upload-file/upload.service';
import {forkJoin} from 'rxjs';

@Component({
  selector: 'app-import-file-dialog',
  templateUrl: './import-file-dialog.component.html',
  styleUrls: ['./import-file-dialog.component.css']
})
export class ImportFileDialogComponent implements OnInit {

  @ViewChild('file') file;
  public fileToUpload: File;
  progress;
  canBeClosed = true;
  primaryButtonText = 'Załaduj';
  showCancelButton = true;
  selected = false;
  uploadSuccessful = false;
  response;

  constructor(public dialogRef: MatDialogRef<ImportFileDialogComponent>, public uploadService: UploadService) {
  }

  ngOnInit() {
  }

  addFile() {
    this.file.nativeElement.click();
  }

  onFilesAdded() {
    const files: { [key: string]: File } = this.file.nativeElement.files;
    for (let key in files) {
      if (!isNaN(parseInt(key))) {
        this.fileToUpload = files[key];
      }
    }
    this.selected = true;
  }

  closeDialog() {
    // if everything was uploaded already, just close the dialog
    if (this.uploadSuccessful) {
      return this.dialogRef.close();
    }

    // start the upload and save the progress map
    this.progress = this.uploadService.upload(this.fileToUpload);

    // Adjust the state variables

    // The OK-button should have the text "Finish" now
    this.primaryButtonText = 'Zamknij';

    // The dialog should not be closed while uploading
    this.canBeClosed = false;
    this.dialogRef.disableClose = true;

    // Hide the cancel-button
    this.showCancelButton = false;

    // When all progress-observables are completed...
    forkJoin(this.progress).subscribe(end => {
      // ... the dialog can be closed again...
      this.canBeClosed = true;
      this.dialogRef.disableClose = false;

      // ... the upload was successful...
      this.uploadSuccessful = true;
      this.response = this.uploadService.response;
    });
  }
}
