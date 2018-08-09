import {ChangeDetectorRef, Component, Input, OnInit, ViewChild} from '@angular/core';
import {MatPaginator, MatSort, MatTableDataSource} from '@angular/material';
import {SourceFilesService} from '../../services/source-files.service';
import {SourceFileDetail} from '../../models/source-file-detail.model';
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-source-file-details-list',
  templateUrl: './source-file-details-list.component.html',
  styleUrls: ['./source-file-details-list.component.css']
})
export class SourceFileDetailsListComponent implements OnInit {
  dataSource = new MatTableDataSource<SourceFileDetail>();
  displayedColumns = ['id', 'itemNumber', 'price'];
  @Input() selectedId: number;

  constructor(private sourceFilesService: SourceFilesService, private route: ActivatedRoute, private changeDetectorRefs: ChangeDetectorRef) {
  }

  @ViewChild(MatSort) sort: MatSort;
  @ViewChild(MatPaginator) paginator: MatPaginator;

  ngOnInit() {
  }

  ngOnChanges(changes) {
    this.refresh()
  }

  applyFilter(filterValue: string) {
    this.dataSource.filter = filterValue.trim().toLowerCase();

    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }

  refresh() {
    if (typeof this.selectedId !== 'undefined') {
      this.dataSource.sort = this.sort;
      this.dataSource.paginator = this.paginator;
      this.sourceFilesService.getDetails(this.selectedId).subscribe(data => {
        this.dataSource.data = data;
      })
    }
  }
}
