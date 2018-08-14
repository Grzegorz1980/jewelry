import {HttpClientModule} from '@angular/common/http';
import {BrowserModule} from '@angular/platform-browser';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';

import {AppComponent} from './app.component';
import {ImportFileComponent} from './components/import-file/import-file.component';
import {PageNotFoundComponent} from "./not-found.component";
import {MatFormFieldModule, MatGridListModule, MatInputModule, MatPaginatorModule, MatSortModule, MatTableModule,} from '@angular/material';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {JewelryListComponent} from './components/jewelry-list/jewelry-list.component';
import {JewelryListService} from './services/jewelry-list/jewelry-list.service';

const appRoutes: Routes = [
  {path: 'import-file', component: ImportFileComponent},
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
    ImportFileComponent,
    PageNotFoundComponent,
    JewelryListComponent
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
  providers: [
              JewelryListService
  ],
  bootstrap: [AppComponent]
})

export class AppModule {
}
