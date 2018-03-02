import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router } from '@angular/router';
import { SessionService } from '../session/session.service';

/*
Service to prevent front-end access to unauthorized areas.
This one is specifically for admins.
*/
@Injectable()
export class VisitorAuthGuardService implements CanActivate {

  constructor(private session: SessionService, private router: Router) { }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {
    if(!this.session.checkLoggedIn()){
      return true;
    } else{
      this.router.navigate(["/home"])
    }
    return false;
  }

}
