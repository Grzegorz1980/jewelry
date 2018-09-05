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
  displayedColumns = ['businessId', 'sku', 'name', 'price', 'promoPrice', 'edit'];
  expandedElement: Jewel;
  selectedId: number;

  constructor(private jewelryListService: JewelryService, public dialog: MatDialog, private downloadFileService: DownloadFileService, private loginService: LoginService, private router: Router) {
  }

  @ViewChild(MatSort) sort: MatSort;
  @ViewChild(MatPaginator) paginator: MatPaginator;

  ngOnInit() {
    this.dataSource.sort = this.sort;
    this.dataSource.paginator = this.paginator;
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
    this.dialog.open(ImportFileDialogComponent, { width: '50%', height: '50%' });
  }

  public generateFile() {
    this.downloadFileService.generate();
  }

  public onEdit(jewel: Jewel) {
    const config = new MatDialogConfig();
    config.data = jewel;
    config.width = '50%';
    config.height = '80%';

    this.dialog.open(EditJewelDialogComponent, config);

  }

  public onDelete(jewel: Jewel) {
    console.log("Deleted. Jewel SKU=" + jewel.sku);
  }

  public logout() {
    this.loginService.logout();
    this.router.navigate(['/login']);
  }
}
