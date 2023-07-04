import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable, map } from 'rxjs';
import { TokenService } from '../services/token/token.service';
import { AuthService } from '../services/auth/auth.service';
import Swal from 'sweetalert2';


@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {

  constructor(
    private tokenService:TokenService,
    private authService: AuthService,
    private router: Router
  ){

  }
  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {

      return this.authService.user$
      .pipe(
        map(user => {
          console.log(user)
          if(!user) {
             this.router.navigate(['login']);
            //this.router.createUrlTree(['login'])
            Swal.fire("Acceso Denegado ", "No has iniciado sesión","error")
            return false;
          }
          return true;
        })
      )
  }

}
