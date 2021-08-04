import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { AuthService } from '../services/auth/auth.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  constructor(public authService: AuthService, private toastr: ToastrService, private router: Router) { 
  }

  isLoggedIn: boolean = false;
  loggedInUsername: string = "";
  
  ngOnInit(): void {
    this.authService.isLoggedIn.subscribe((data: boolean) => this.isLoggedIn = data);
    this.authService.loggedInUsername.subscribe((data: string) => this.loggedInUsername = data);
    this.isLoggedIn = this.authService.getIsLoggedIn();
    this.loggedInUsername = this.authService.getLoggedInUsername();
  }

  logout(){
    this.authService.logout().subscribe(
      res=>{
        this.isLoggedIn = false;
        this.loggedInUsername = "";
        this.authService.clearLocalStorage();
        this.toastr.success("Successfully logged out");
        this.router.navigateByUrl("");
      },
      err =>{
        this.toastr.error("Please try again", "Error during logout")
      }
    );
  }

  goToUserProfile(){
    this.router.navigateByUrl("user/profile")
  }
}
