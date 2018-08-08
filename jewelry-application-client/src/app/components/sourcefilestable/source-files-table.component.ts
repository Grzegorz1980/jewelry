import {Component, OnInit, ViewChild} from '@angular/core';
import {MatPaginator, MatSort, MatTableDataSource} from '@angular/material';
import { SourceFilesService } from '../../services/source-files.service';
import { SourceFile } from '../../models/source-file.model';

@Component({
  selector: 'source-files-table',
  templateUrl: './source-files-table.component.html',
  styleUrls: ['./source-files-table.component.css']
})
export class SourceFilesTableComponent implements OnInit {
  dataSource = new MatTableDataSource<SourceFile>();
  displayedColumns = ['id', 'name', 'importDate'];
  constructor(private sourceFilesService: SourceFilesService) { }

  @ViewChild(MatSort) sort: MatSort;
  @ViewChild(MatPaginator) paginator: MatPaginator;

  ngOnInit() {
    this.dataSource.sort = this.sort;
    this.dataSource.paginator = this.paginator;
    this.sourceFilesService.getFiles().subscribe(data =>  {
      this.dataSource.data = data;
    })
  }

  applyFilter(filterValue: string) {
    this.dataSource.filter = filterValue.trim().toLowerCase();

    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }
}

