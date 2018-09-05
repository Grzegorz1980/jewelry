import {Component, OnInit} from '@angular/core';
import {LoginService} from "../../services/login/login.service";
import {Router} from "@angular/router";
import {HttpErrorResponse} from "@angular/common/http";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  public username: string;
  public password: string;
  private isLoginError: boolean;

  constructor(private loginService: LoginService, private router: Router) {
  }

  ngOnInit() {
  }

  login(username, password) {
    console.log("Logowane. Username=" + username);
    this.loginService.login(username, password).subscribe((data: any) => {
      this.isLoginError = false;
      console.log("Got token=" + data.access_token)
        localStorage.setItem("token", data.access_token);
        this.router.navigate(['/jewelry-list']);
      },
      (err : HttpErrorResponse)=>{
        this.isLoginError = true;
      }
    );
  }
}
