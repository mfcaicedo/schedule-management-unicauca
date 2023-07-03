import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable, map } from 'rxjs';
import { AuthService } from '../services/auth/auth.service';
import Swal from 'sweetalert2'

@Injectable({
  providedIn: 'root'
})
export class ScheduleManagerGuard implements CanActivate {

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
          const esScheduleManager = user?.authorities?.some(authority => authority.authority === 'ROLE_SCHEDULE_MANAGER');

          if(esScheduleManager)  {
            //Swal.fire("Welcome", "holaa","success")
            console.log("Tiene acceso")
            return true;
          }
          this.router.createUrlTree(['/'])
          Swal.fire("Acceso denegado", "No tienes permisos para ingresar a esta vista","error")
          return false;
        })
      )
  }

}
