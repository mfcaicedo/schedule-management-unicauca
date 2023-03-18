import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor
} from '@angular/common/http';
import { Observable } from 'rxjs';

import {AuthService} from 'src/app/services/auth/auth.service'

@Injectable()
export class TokenInterceptor implements HttpInterceptor {

  constructor(
    private authService :AuthService
  ) {}

  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
    request=this.addToken(request)
    return next.handle(request);
  }

  private addToken(request: HttpRequest<unknown>){
    const token = this.authService.getToken()
    if(token){
      const authReq = request.clone({
        headers: request.headers.set('Authorization', `Bearer ${token}`)
      })
      return authReq
    }
    return request
  }
}
