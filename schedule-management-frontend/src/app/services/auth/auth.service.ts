import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import {TokenService} from '../token/token.service'
import { Observable, map, switchMap, tap } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Auth } from 'src/app/models/auth.model';
import {Authority, User} from 'src/app/models/profile'
import { environment } from 'src/environments/environment';
import { BehaviorSubject } from 'rxjs';
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

  login(credentials:{username:string,password:string}):Observable<Auth> {
    const username = credentials.username
    const password = credentials.password
    return this.http.post<Auth>(`${this.apiUrl}/login`, {username, password})
    .pipe(
      tap(response => this.tokenService.saveToken(response.token)),
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




}
