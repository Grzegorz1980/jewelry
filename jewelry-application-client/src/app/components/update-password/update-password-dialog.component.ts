import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material";
import {ImportFileDialogComponent} from "../import-file-dialog/import-file-dialog.component";
import {JewelryService} from "../../services/jewelry/jewelry.service";
import {UserService} from "../../services/user/user.service";
import {Jewel} from "../../models/jewel.model";

@Component({
  selector: 'app-update-password',
  templateUrl: './update-password-dialog.component.html',
  styleUrls: ['./update-password-dialog.component.css']
})
export class UpdatePasswordDialogComponent implements OnInit {

  public username: string;
  public password: string;
  public repeatPassword: string;
  public error: boolean;

  constructor(public dialogRef: MatDialogRef<UpdatePasswordDialogComponent>, public userService: UserService, @Inject(MAT_DIALOG_DATA) data) {
    dialogRef.disableClose = true;
    this.username = data;
  }

  ngOnInit() {
  }

  updatePassword() {
    if (this.password != this.repeatPassword) {
      this.error = true;
    } else {
      this.error = false;
      this.userService.updatePassword(this.username, this.password).subscribe(response => {
        this.dialogRef.close();
      });
    }
  }

  closeDialog() {
    this.dialogRef.close();
  }
}
