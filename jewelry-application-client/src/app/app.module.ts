import {HttpClientModule} from '@angular/common/http';
import {BrowserModule} from '@angular/platform-browser';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {Injectable, NgModule} from '@angular/core';
import {ActivatedRouteSnapshot, CanActivate, Router, RouterModule, RouterStateSnapshot, Routes} from '@angular/router';
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
import {JewelryService} from './services/jewelry/jewelry.service';
import {ImportFileDialogComponent} from './components/import-file-dialog/import-file-dialog.component';
import { EditJewelDialogComponent } from './components/edit-jewel-dialog/edit-jewel-dialog.component';
import { LoginComponent } from './components/login/login.component';

@Injectable()
export class AuthGuard implements CanActivate {
  constructor(private router : Router){}
  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot):  boolean {
    if (localStorage.getItem('userToken') != null)
      return true;
    this.router.navigate(['/login']);
    return false;
  }
}

const appRoutes: Routes = [
  {
    path: 'jewelry-list',
    component: JewelryListComponent,
    data: {title: 'Jewelry'},
    canActivate: [AuthGuard]
  },
  {
    path: 'login',
    component: LoginComponent,
    data: {title: 'Login'}
  },
  {
    path: '',
    redirectTo: '/login',
    pathMatch: 'full'
  },
  {path: '**', component: PageNotFoundComponent}
];

@NgModule({
  declarations: [
    AppComponent,
    PageNotFoundComponent,
    JewelryListComponent,
    ImportFileDialogComponent,
    EditJewelDialogComponent,
    LoginComponent
  ],
  entryComponents: [ImportFileDialogComponent, EditJewelDialogComponent],
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
    CommonModule, MatButtonModule, MatDialogModule, MatListModule, HttpClientModule, MatProgressBarModule,
  ],
  providers: [
    JewelryService,
    AuthGuard
  ],
  bootstrap: [AppComponent]
})

export class AppModule {
}
