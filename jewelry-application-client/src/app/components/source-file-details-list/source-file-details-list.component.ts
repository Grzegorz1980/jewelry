import {Component, OnInit, ViewChild} from '@angular/core';
import {MatPaginator, MatSort, MatTableDataSource} from '@angular/material';
import { SourceFilesService } from '../../services/source-files.service';
import { SourceFileDetail } from '../../models/source-file-detail.model';
import {SourceFile} from "../../models/source-file.model";
import {ActivatedRoute, ParamMap} from "@angular/router";
import {switchMap} from "rxjs/internal/operators";
import {Observable} from "rxjs/index";

@Component({
  selector: 'app-source-file-details-list',
  templateUrl: './source-file-details-list.component.html',
  styleUrls: ['./source-file-details-list.component.css']
})
export class SourceFileDetailsListComponent implements OnInit {
  dataSource = new MatTableDataSource<SourceFileDetail>();
  displayedColumns = ['id', 'itemNumber', 'price'];
  private selectedId: number;

  details$: Observable<SourceFileDetail[]>;

  constructor(private sourceFilesService: SourceFilesService, private route: ActivatedRoute) {
  }

  @ViewChild(MatSort) sort: MatSort;
  @ViewChild(MatPaginator) paginator: MatPaginator;

  ngOnInit() {
    this.dataSource.sort = this.sort;
    this.dataSource.paginator = this.paginator;
    this.details$ = this.route.paramMap.pipe(
      switchMap((params: ParamMap) =>
        this.sourceFilesService.getDetails(params.get('id'))
    ));
    this.details$.subscribe(data =>  {
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
