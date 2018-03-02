import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router } from '@angular/router';
import { SessionService } from '../session/session.service';

/*
Service to prevent front-end access to unauthorized areas.
This one is specifically for admins.
*/
@Injectable()
export class AuthGuardService implements CanActivate {

  constructor(private session: SessionService, private router: Router) { }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {
    return this.session.checkAdmin();
  }

}
