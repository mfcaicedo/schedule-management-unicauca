import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable, map } from 'rxjs';
import { AuthService } from '../services/auth/auth.service';
import Swal from 'sweetalert2'

@Injectable({
  providedIn: 'root'
})
export class AdminGuard implements CanActivate {

  constructor(

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
          const esAdministrador = user?.authorities?.some(authority => authority.authority === 'ROLE_ACADEMIC_MANAGER');

          if(esAdministrador)  {
            //Swal.fire("Welcome", "holaa","success")
            return true;
          }
          this.router.createUrlTree(['/'])
          Swal.fire("Acceso denegado", "No tienes permisos para ingresar a esta vista","error")
          return false;
        })
      )

  }

}
