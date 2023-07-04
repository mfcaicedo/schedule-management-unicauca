import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Auth } from 'src/app/models/auth.model';

@Injectable({
  providedIn: 'root'
})
export class TokenService {

  constructor(
    private router :Router
  ) { }
  saveToken(token:string){
    // console.log("Asignando token en local storage ",token)
    localStorage.setItem('token',token)
  }

  getToken(){
    const token = localStorage.getItem('token')
    return token
  }

  logout(): void {

    localStorage.clear();
    this.router.navigate(['login']);
  }
  
}
