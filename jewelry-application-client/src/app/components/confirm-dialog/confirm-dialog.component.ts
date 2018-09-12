import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material";
import {ImportFileDialogComponent} from "../import-file-dialog/import-file-dialog.component";
import {JewelryService} from "../../services/jewelry/jewelry.service";
import {ServerResponse} from "../../models/serverResponse.model";
import {Jewel} from "../../models/jewel.model";

@Component({
  selector: 'app-confirm-dialog',
  templateUrl: './confirm-dialog.component.html',
  styleUrls: ['./confirm-dialog.component.css']
})
export class ConfirmDialogComponent implements OnInit {

  constructor(public dialogRef: MatDialogRef<ImportFileDialogComponent>, public jewelryService: JewelryService, @Inject(MAT_DIALOG_DATA) data) {
    dialogRef.disableClose = true;
    this.jewel = data;
  }

  public jewel: Jewel;
  public responded = false;

  ngOnInit() {
  }

  closeDialog() {
    this.dialogRef.close();
  }

  delete(id: number) {
    this.jewelryService.deleteJewel(id).subscribe(response => {
      this.responded = true;
      this.dialogRef.close();
    });
  }

}
