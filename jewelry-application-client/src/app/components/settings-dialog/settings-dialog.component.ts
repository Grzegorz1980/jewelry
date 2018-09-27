import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material";
import {UpdatePasswordDialogComponent} from "../update-password/update-password-dialog.component";
import {SettingsService} from "../../services/settings/settings.service";
import {Settings} from "../../models/settings.model";

@Component({
  selector: 'app-settings-dialog',
  templateUrl: './settings-dialog.component.html',
  styleUrls: ['./settings-dialog.component.css']
})
export class SettingsDialogComponent implements OnInit {
  public settings: Settings;

  constructor(public dialogRef: MatDialogRef<SettingsDialogComponent>, public settingsService: SettingsService, @Inject(MAT_DIALOG_DATA) data) {
    dialogRef.disableClose = true;
    this.settings = data;
  }

  ngOnInit() {
  }

  saveSettings() {
    this.settingsService.saveSettings(this.settings).subscribe(response => {
        this.dialogRef.close();
      });
    }

  closeDialog() {
    this.dialogRef.close();
  }

}
