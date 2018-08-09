import {HttpClientModule} from '@angular/common/http';
import {BrowserModule} from '@angular/platform-browser';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';

import {AppComponent} from './app.component';
import {SourceFilesTableComponent} from './components/source-files-table/source-files-table.component';
import {SourceFilesService} from './services/source-files.service';
import {ImportFilesComponent} from './components/import-files/import-files.component';
import {PageNotFoundComponent} from "./not-found.component";
import {MatFormFieldModule, MatInputModule, MatPaginatorModule, MatSortModule, MatTableModule, MatGridListModule,} from '@angular/material';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import { SourceFileDetailsListComponent } from './components/source-file-details-list/source-file-details-list.component';


const appRoutes: Routes = [
  {path: 'import-files', component: ImportFilesComponent},
  {
    path: 'source-files',
    component: SourceFilesTableComponent,
    data: {title: 'Source Files'}
  },
  {
    path: '',
    redirectTo: '/source-files',
    pathMatch: 'full'
  },
  {path: '**', component: PageNotFoundComponent}
];

@NgModule({
  declarations: [
    AppComponent,
    SourceFilesTableComponent,
    SourceFileDetailsListComponent,
    ImportFilesComponent,
    PageNotFoundComponent,
    SourceFileDetailsListComponent
  ],
  imports: [
    RouterModule.forRoot(
      appRoutes,
      {enableTracing: true} // <-- debugging purposes only
    ),
    BrowserModule,
    HttpClientModule,
    MatTableModule,
    MatSortModule,
    MatPaginatorModule,
    MatInputModule,
    MatFormFieldModule,
    MatGridListModule,
    FormsModule,
    ReactiveFormsModule,
    BrowserAnimationsModule,
  ],
  providers: [SourceFilesService],
  bootstrap: [AppComponent]
})

export class AppModule {
}
