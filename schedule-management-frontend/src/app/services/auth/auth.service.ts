import { Injectable } from '@angular/core';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private router:Router) { }

  saveToken(token:string){
    // console.log("Asignando token en local storage ",token)
    localStorage.setItem('token',token)
  }

  getToken(){
    const token = localStorage.getItem('token')
    // console.log("Obteniendo token en auth service ",token)
    return token
  }
  
  logout(): void {

    localStorage.clear();
    //localStorage.removeItem('token');
    this.router.navigate(['login']);
  }
}
