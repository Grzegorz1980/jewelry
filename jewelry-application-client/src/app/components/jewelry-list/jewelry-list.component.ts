import {Component, OnInit, ViewChild} from '@angular/core';
import {MatDialog, MatDialogConfig, MatPaginator, MatSort, MatTableDataSource} from "@angular/material";
import {Jewel} from "../../models/jewel.model";
import {JewelryService} from "../../services/jewelry/jewelry.service";
import {DownloadFileService} from "../../services/download-file/download-file.service";
import {ImportFileDialogComponent} from "../import-file-dialog/import-file-dialog.component";
import {EditJewelDialogComponent} from "../edit-jewel-dialog/edit-jewel-dialog.component"
import {animate, state, style, transition, trigger} from '@angular/animations';
import {LoginService} from "../../services/login/login.service";
import {Router} from "@angular/router";
import {ConfirmDialogComponent} from "../confirm-dialog/confirm-dialog.component";
import {UpdatePasswordDialogComponent} from "../update-password/update-password-dialog.component";
import {Settings} from "../../models/settings.model";
import {SettingsService} from "../../services/settings/settings.service";
import {SettingsDialogComponent} from "../settings-dialog/settings-dialog.component";

@Component({
  selector: 'app-jewelry-list',
  templateUrl: './jewelry-list.component.html',
  styleUrls: ['./jewelry-list.component.css'],
  animations: [
    trigger('detailExpand', [
      state('collapsed', style({height: '0px', minHeight: '0', display: 'none'})),
      state('expanded', style({height: '*'})),
      transition('expanded <=> collapsed', animate('225ms cubic-bezier(0.4, 0.0, 0.2, 1)')),
    ])
    ]
})
export class JewelryListComponent implements OnInit {
  dataSource = new MatTableDataSource<Jewel>();
  displayedColumns = ['businessId', 'sku', 'name', 'priceUsd', 'pricePln', 'priceSugested', 'price', 'promoPriceSugested', 'promoPrice', 'edit'];
  expandedElement: Jewel;
  public settings: Settings;

  constructor(private jewelryListService: JewelryService, private settingsService: SettingsService,  public dialog: MatDialog, private downloadFileService: DownloadFileService, private loginService: LoginService, private router: Router) {
  }

  @ViewChild(MatSort) sort: MatSort;
  @ViewChild(MatPaginator) paginator: MatPaginator;

  ngOnInit() {
    this.dataSource.sort = this.sort;
    this.dataSource.paginator = this.paginator;
    this.settingsService.getSettings().subscribe(data => {
      this.settings = data;
    })
    this.jewelryListService.getJewelry().subscribe(data => {
      this.dataSource.data = data;
    })
  }

  applyFilter(filterValue: string) {
    this.dataSource.filter = filterValue.trim().toLowerCase();

    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }

  public openUploadDialog() {
    this.dialog.open(ImportFileDialogComponent, { width: '50%', height: '50%' }).afterClosed().subscribe(result => {
      window.location.reload();
    });
  }

  public generateFile() {
    this.downloadFileService.generate();
  }

  public onEdit(jewel: Jewel) {
    const config = new MatDialogConfig();
    config.data = [jewel, this.settings];
    config.width = '50%';
    config.height = '80%';

    this.dialog.open(EditJewelDialogComponent, config);
  }

  public onDelete(jewel: Jewel) {
    console.log("Deleted. Jewel SKU=" + jewel.sku);

    const config = new MatDialogConfig();
    config.data = jewel;
    config.width = '30%';
    config.height = '25%';

    this.dialog.open(ConfirmDialogComponent, config).afterClosed().subscribe(result => {
      window.location.reload();
    });
  }

  public logout() {
    this.loginService.logout();
    this.router.navigate(['/login']);
  }

  public updatePassword() {
    const config = new MatDialogConfig();
    config.width = '50%';
    config.height = '80%';
    config.data = localStorage.getItem('username');

    this.dialog.open(UpdatePasswordDialogComponent, config);
  }

  public updateSettings() {
    const config = new MatDialogConfig();
    config.data = this.settings;
    config.width = '50%';
    config.height = '80%';
    this.dialog.open(SettingsDialogComponent, config);
  }
}
