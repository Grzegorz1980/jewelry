import {HttpClientModule} from '@angular/common/http';
import {BrowserModule} from '@angular/platform-browser';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';

import {AppComponent} from './app.component';
import {SourceFilesTableComponent} from './components/sourcefilestable/source-files-table.component';
import {SourceFilesService} from './services/source-files.service';
import {ImportFilesComponent} from './components/import-files/import-files.component';
import {PageNotFoundComponent} from "./not-found.component";
import {MatFormFieldModule, MatInputModule, MatPaginatorModule, MatSortModule, MatTableModule} from '@angular/material';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';


const appRoutes: Routes = [
  {path: 'source-files', component: SourceFilesTableComponent},
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
    ImportFilesComponent,
    PageNotFoundComponent
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
    FormsModule,
    ReactiveFormsModule,
    BrowserAnimationsModule,
  ],
  providers: [SourceFilesService],
  bootstrap: [AppComponent]
})

export class AppModule {
}
