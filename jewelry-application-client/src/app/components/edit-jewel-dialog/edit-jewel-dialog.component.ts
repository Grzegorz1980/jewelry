import {Component, Inject, OnInit} from '@angular/core';
import {ImportFileDialogComponent} from "../import-file-dialog/import-file-dialog.component";
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material";
import {Jewel} from "../../models/jewel.model";
import {JewelryService} from "../../services/jewelry/jewelry.service";
import {ServerResponse} from "../../models/serverResponse.model";

@Component({
  selector: 'app-edit-jewel-dialog',
  templateUrl: './edit-jewel-dialog.component.html',
  styleUrls: ['./edit-jewel-dialog.component.css']
})
export class EditJewelDialogComponent implements OnInit {

  constructor(public dialogRef: MatDialogRef<ImportFileDialogComponent>, public jewelryService: JewelryService, @Inject(MAT_DIALOG_DATA) data) {
    dialogRef.disableClose = true;
    this.jewel = data;
  }

  public jewel: Jewel;
  public response: ServerResponse;
  public responded = false;

  ngOnInit() {
  }

  closeDialog() {
    this.dialogRef.close();
  }

  save(jewel: Jewel) {
    this.jewelryService.saveJewel(jewel).subscribe(response => {
      this.response = response;
      this.responded = true;
      if (response.result === "OK") {
        this.dialogRef.close();
      }
    });
  }
}
