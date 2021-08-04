import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthGuard } from './auth/guard/auth.guard';
import { LoginGuardGuard } from './auth/guard/login-guard.guard';
import { LoginComponent } from './auth/login/login.component';
import { SignupComponent } from './auth/signup/signup.component';
import { NotFoundComponent } from './error/not-found/not-found.component';
import { HomeComponent } from './home/home.component';
import { ProfileComponent } from './user/profile/profile.component';


const routes: Routes = [
  {path: "signup", component: SignupComponent, canActivate: [LoginGuardGuard]},
  {path: "login", component: LoginComponent, canActivate: [LoginGuardGuard]},
  {path: "user/profile", component: ProfileComponent, canActivate: [AuthGuard]},
  {path: "", component: HomeComponent},
  {path: "not-found", component: NotFoundComponent},
  {path: '**', redirectTo: '/not-found'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
