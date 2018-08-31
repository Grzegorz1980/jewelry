import {HTTP_INTERCEPTORS, HttpClientModule, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from '@angular/common/http';
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
import {Observable, throwError} from "rxjs/index";
import {catchError} from "rxjs/internal/operators";
import {LoginService} from "./services/login/login.service";
import { FlexLayoutModule } from "@angular/flex-layout";

@Injectable()
export class AuthGuard implements CanActivate {
  constructor(private router : Router){}
  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot):  boolean {
    if (localStorage.getItem('token'))
      return true;
    this.router.navigate(['/login']);
    return false;
  }
}

@Injectable()
export class JwtInterceptor implements HttpInterceptor {
  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    // add authorization header with jwt token if available
    let currentToken = localStorage.getItem('token');
    console.log("JWT. Token=" + currentToken);
    if (currentToken) {
      request = request.clone({
        setHeaders: {
          Authorization: `Bearer ${currentToken}`
        }
      });
    }

    return next.handle(request);
  }
}

@Injectable()
export class ErrorInterceptor implements HttpInterceptor {
  constructor(private loginService: LoginService) {}

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    return next.handle(request).pipe(catchError(err => {
      if (err.status === 401) {
        // auto logout if 401 response returned from api
        this.loginService.logout();
        location.reload(true);
      }

      const error = err.error.message || err.statusText;
      return throwError(error);
    }))
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
    FlexLayoutModule,
    CommonModule, MatButtonModule, MatDialogModule, MatListModule, HttpClientModule, MatProgressBarModule,
  ],
  providers: [
    { provide: HTTP_INTERCEPTORS, useClass: JwtInterceptor, multi: true },
    { provide: HTTP_INTERCEPTORS, useClass: ErrorInterceptor, multi: true },
    JewelryService,
    AuthGuard
  ],
  bootstrap: [AppComponent]
})

export class AppModule {
}
