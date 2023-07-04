import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import {TokenService} from '../token/token.service'
import { Observable, map, switchMap, tap } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Auth } from 'src/app/models/auth.model';
import {Authority, User} from 'src/app/models/profile'
import { environment } from 'src/environments/environment';
import { BehaviorSubject } from 'rxjs';

import{emailValues} from'src/app/models/emailValues'
@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private user = new BehaviorSubject<User | null>(null);
  user$ = this.user.asObservable();

  constructor(
    private http: HttpClient,
    private router:Router,
    private tokenService : TokenService
    ) {

     }

  apiUrl = environment.urlAuth
  apiPasswordUrl = environment.urlPassword;

  login(credentials:{username:string,password:string}):Observable<Auth> {
    const username = credentials.username
    const password = credentials.password
    return this.http.post<Auth>(`${this.apiUrl}/login`, {username, password})
    .pipe(
      tap(response => {
        this.tokenService.saveToken(response.token)
        this.tokenService.save(response)
      }),

    );
  }

  getProfile(auth:Auth) {

    const  user: User = {username: auth.username, authorities : auth.authorities}
    return  this.user.next(user)

  }

  loginAndGet(credentials:{username:string,password:string}) {
    const username = credentials.username
    const password = credentials.password

    return this.login({username, password})
    .pipe(
      switchMap(async (response) => {
        this.getProfile(response)
        // console.log(this.user$)
      }),

    )
  }

  logout(){
    this.tokenService.logout()
    this.user.next(null);
  }
  obtenerDatosToken(accessToken: string): any {
    if (accessToken != null) {
      return JSON.parse(atob(accessToken.split(".")[1]));
    }
    return null;
  }
  guardarUsuario(accessToken: string):Auth {

    // console.log("save User",programa)
    let payload = this.obtenerDatosToken(accessToken);
    const username = payload.sub;
    let authority = localStorage.getItem('aut');
    if (authority == null){
      authority="ROLE_SCHEDULE_MANAGER"
    }
    const auth : Auth = {username:username , authorities:[{authority:authority}] ,token:accessToken, bearer:"Bearer"}
    return auth
  }

  getUserAuthority():Observable<string[]>{

    return this.user$.pipe(
      map(user => {
        let autoridades:string[] = []
        console.log(user)

        user?.authorities?.forEach(authority => autoridades.push(authority.authority)  );
        return autoridades;
      })
    )
  }

  sendEmailChangePassword(emailValues:emailValues):Observable<string>{
    console.log("email send ", emailValues)


    return this.http.post<string>(`${this.apiPasswordUrl}/email/sendHtml`,emailValues)
  }

  changePassword(credentials:{username:string,password:string }):Observable<any>{
    const username = credentials.username
    const password = credentials.password

    return this.http.post<string>(`${this.apiPasswordUrl}/email/sendHtml`,{username,password})
  }


}
