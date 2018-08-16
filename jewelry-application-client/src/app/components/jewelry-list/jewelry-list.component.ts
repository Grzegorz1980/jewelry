import {Component, OnInit, ViewChild} from '@angular/core';
import {MatDialog, MatPaginator, MatSort, MatTableDataSource} from "@angular/material";
import {Jewel} from "../../models/jewel.model";
import {JewelryListService} from "../../services/jewelry-list/jewelry-list.service";
import {ImportFileDialogComponent} from "../import-file-dialog/import-file-dialog.component";

@Component({
  selector: 'app-jewelry-list',
  templateUrl: './jewelry-list.component.html',
  styleUrls: ['./jewelry-list.component.css']
})
export class JewelryListComponent implements OnInit {
  dataSource = new MatTableDataSource<Jewel>();
  displayedColumns = ['businessId', 'sku', 'name', 'type', 'edit'];
  selectedId: number;

  constructor(private jewelryListService: JewelryListService, public dialog: MatDialog) {
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

  onSelect(selectedId: number): void {
    this.selectedId = selectedId;
  }

  public openUploadDialog() {
    let dialogRef = this.dialog.open(ImportFileDialogComponent, { width: '50%', height: '50%' });
  }

  public onEdit(jewel: Jewel) {
    console.log("Clicked. Jewel SKU=" + jewel.sku);
  }

  public onDelete(jewel: Jewel) {
    console.log("Deleted. Jewel SKU=" + jewel.sku);
  }
}
