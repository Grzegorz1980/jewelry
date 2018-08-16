import {HttpClientModule} from '@angular/common/http';
import {BrowserModule} from '@angular/platform-browser';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {CommonModule} from '@angular/common';
import {
  MatButtonModule,
  MatDialogModule,
  MatFormFieldModule,
  MatGridListModule,
  MatInputModule,
  MatListModule,
  MatPaginatorModule,
  MatProgressBarModule,
  MatSortModule,
  MatTableModule
} from '@angular/material';

import {AppComponent} from './app.component';
import {PageNotFoundComponent} from "./not-found.component";
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {JewelryListComponent} from './components/jewelry-list/jewelry-list.component';
import {JewelryListService} from './services/jewelry-list/jewelry-list.service';
import {ImportFileDialogComponent} from './components/import-file-dialog/import-file-dialog.component';

const appRoutes: Routes = [
  // {path: 'import-file', component: ImportFileComponent},
  {
    path: 'jewelry-list',
    component: JewelryListComponent,
    data: {title: 'Jewelry'}
  },
  {
    path: '',
    redirectTo: '/jewelry-list',
    pathMatch: 'full'
  },
  {path: '**', component: PageNotFoundComponent}
];

@NgModule({
  declarations: [
    AppComponent,
    PageNotFoundComponent,
    JewelryListComponent,
    ImportFileDialogComponent
  ],
  entryComponents: [ImportFileDialogComponent],
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
    CommonModule, MatButtonModule, MatDialogModule, MatListModule, HttpClientModule, BrowserAnimationsModule, MatProgressBarModule,
  ],
  providers: [
    JewelryListService
  ],
  bootstrap: [AppComponent]
})

export class AppModule {
}
