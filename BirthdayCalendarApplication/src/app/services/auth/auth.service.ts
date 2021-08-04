import { EventEmitter, Injectable, Output } from '@angular/core';
import { HttpClient } from '@angular/common/http'
import { RegisterRequest } from 'src/app/models/register-request';
import { LoginRequest } from 'src/app/models/login-request';
import { LocalStorageService } from 'ngx-webstorage';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient, private localStorage: LocalStorageService) { }

  @Output() isLoggedIn: EventEmitter<boolean> = new EventEmitter();
  @Output() loggedInUsername: EventEmitter<string> = new EventEmitter();

  readonly baseUrl = "http://localhost:8080/api/"

  signup(registerRequest: RegisterRequest){
    return this.http.post(this.baseUrl+"auth/signup", registerRequest, {responseType: 'text'});
  }

  login(loginRequest: LoginRequest){
    return this.http.post(this.baseUrl+"auth/login", loginRequest, {responseType: 'text'});
  }

  logout(){
    return this.http.post(this.baseUrl+"auth/logout", {responseType: 'text'});
  }

  getLoggedInUsername(){
    return this.localStorage.retrieve('loggedInUsername');
  }

  getIsLoggedIn(){
    return this.localStorage.retrieve('isLoggedIn');
  }

  clearLocalStorage(){
    this.localStorage.clear("isLoggedIn");
    this.localStorage.clear("loggedInUsername");
  }


}
