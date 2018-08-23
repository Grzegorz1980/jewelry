import {Component, OnInit} from '@angular/core';
import {LoginService} from "../../services/login/login.service";
import {Router} from "@angular/router";

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
      if (data.result === 'OK') {
        localStorage.setItem('userToken', data.message);
        this.router.navigate(['/jewelry-list']);
      } else {
        this.isLoginError = true;
      }
    });
  }
}
