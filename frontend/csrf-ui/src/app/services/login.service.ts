import { Injectable } from '@angular/core';
import { User } from '../model/users/users.model';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(private http: HttpClient) {
    
  }

  validateLoginDetails(user: User) {
    window.sessionStorage.setItem("userdetails",JSON.stringify(user));
    return this.http.get("http://localhost:8080/user", { observe: 'response',withCredentials: true });
  }

  callm1get(): Observable<any>
  {
    return this.http.get("http://localhost:8080/m1", { observe: 'response',responseType: 'text'  });
  }

  callm1post(): Observable<any>
  {
    return this.http.post("http://localhost:8080/m1", { observe: 'response',withCredentials: true, responseType: 'text'  });
  }
}
