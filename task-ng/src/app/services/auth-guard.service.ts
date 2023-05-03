import { Injectable } from '@angular/core';
import {Router, CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, UrlTree} from '@angular/router';
import {AuthService} from "./auth.service";
import {Observable} from "rxjs";
@Injectable({
  providedIn: 'root'
})
export class AuthGuardService implements CanActivate {

  constructor(public auth: AuthService, public router: Router) {
  }

  canActivate(): boolean {
    if (!this.auth.isAuthenticated()) {
      this.router.navigate(['app-login']);
      return false;
    }
    return true;


  // canActivate(
  //   route: ActivatedRouteSnapshot,
  //   state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
  //
  //   if (this.auth.isloggedin()) {
  //     if (route.url.length > 0) {
  //       let menu = route.url[0].path;
  //       if (menu == 'user') {
  //         if (this.auth.getrole() == 'admin') {
  //           return true;
  //         } else {
  //           this.router.navigate(['']);
  //           alert('You dont have access.')
  //           return false;
  //         }
  //       } else {
  //         return true;
  //       }
  //     } else {
  //       return true;
  //     }
  //   } else {
  //     this.router.navigate(['login']);
  //     return false;
  //   }
  }
}
