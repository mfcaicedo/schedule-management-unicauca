import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http'
import {Auth} from 'src/app/models/auth.model'
import { AuthService } from '../auth/auth.service';
import { tap } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})

export class LoginService {

  // endpPoint='api/auth'
  endpPoint = environment.urlAuth
  constructor(
    private http: HttpClient,
    private authService: AuthService
  ) { }

  singin(credentials:{email:string,password:string}){
    const username = credentials.email
    const password = credentials.password

    return this.http.post<Auth>(`${this.endpPoint}/login`,{username,password})
    .pipe(
      tap(response => {this.authService.saveToken(response.token)})

    )
  }
}
