import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { AuthService } from 'src/app/services/auth/auth.service';
import { ToastrService } from 'ngx-toastr';
import { LoginRequest } from 'src/app/models/login-request';
import { Router } from '@angular/router';
import { LocalStorageService } from 'ngx-webstorage';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginForm: FormGroup;
  loginRequest: LoginRequest = new LoginRequest;
  isError: boolean = false;
  loginError = "";

  constructor(public authService: AuthService, private toastr: ToastrService, 
    private router: Router, private localStorage: LocalStorageService) {
    this.loginForm = new FormGroup({
      username: new FormControl('', Validators.required),
      password: new FormControl('', Validators.required),
    })
   }

  ngOnInit(): void {
  }

  login(){
    this.loginRequest.username = this.loginForm.get('username')?.value;
    this.loginRequest.password = this.loginForm.get('password')?.value;

    this.authService.login(this.loginRequest).subscribe(
      res =>{
        this.localStorage.store("loggedInUsername", res);
        this.localStorage.store("isLoggedIn", true);
        this.authService.isLoggedIn.emit(true);
        this.authService.loggedInUsername.emit(res);
        this.toastr.success("Successfully logged in", "Success")
        this.loginForm.reset();
        this.router.navigateByUrl("");
      },
      err => {
        if(err.status == "403"){
          this.loginError = "Error during login, check your credentials and try again";
        }
        else{
          this.loginError = "Internal server error";
        }
      }
    );
  }

}
