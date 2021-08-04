import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { RegisterRequest } from 'src/app/models/register-request';
import { AuthService } from 'src/app/services/auth/auth.service';
import { ToastrService } from 'ngx-toastr';
import { Router } from '@angular/router';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {

  signupForm: FormGroup;
  registerRequest: RegisterRequest = new RegisterRequest;
  passwordConfirmationSame: boolean = true;
  signupError: string = "";

  constructor(public authService: AuthService, private toastr: ToastrService, private router: Router) {
    this.signupForm = new FormGroup({
      username: new FormControl('', Validators.required),
      email: new FormControl('', [Validators.required, Validators.email]),
      password: new FormControl('', Validators.required),
      confirmPassword: new FormControl('', Validators.required)
    })
  }

  ngOnInit(): void {

  }

  signup() {
    if (this.signupForm.get('password')?.value === this.signupForm.get('confirmPassword')?.value) {
      this.passwordConfirmationSame = true;
      this.registerRequest.email = this.signupForm.get('email')?.value;
        this.registerRequest.username = this.signupForm.get('username')?.value;
        this.registerRequest.password = this.signupForm.get('password')?.value;

      this.authService.signup(this.registerRequest).subscribe(
        res => {
          this.signupForm.reset();
          this.toastr.success("Login to continue", "Registration successfull");
          this.router.navigateByUrl("/login");
        },
        err => {
          if (err.status == '409') { 
            this.signupError = err.error; 
          }
          else {
              this.signupError = "Internal server error"
          }
        }
      );
    }
    else {
      this.passwordConfirmationSame = false;
    }
  }

}
