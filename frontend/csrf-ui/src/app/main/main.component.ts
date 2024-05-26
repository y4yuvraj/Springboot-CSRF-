import { Component } from '@angular/core';
import { LoginService } from '../services/login.service';
import { Router } from '@angular/router';
import { getCookie, removeCookie } from 'typescript-cookie';

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css']
})
export class MainComponent {


  constructor(private loginService: LoginService, private router: Router) {
  }

  customMethod() {
    this.loginService.callm1get().subscribe(
      response => {
        console.log("GET /m1 response:", response.body);
        alert("GET /m1 response: " + response.body);  // To show it on screen as well
      },
      error => {
        console.error("Error in GET /m1:", error);
      }
    );
  }

  customMethod2() {
    // removeCookie('XSRF-TOKEN')!;
    this.loginService.callm1post().subscribe(
      response => {
        console.log("POST /m1 response:", response.body);
        alert("POST /m1 response: " + response.body);  // To show it on screen as well
      },
      error => {
        console.error("Error in POST /m1:", error);
      }
    );
  }

}
