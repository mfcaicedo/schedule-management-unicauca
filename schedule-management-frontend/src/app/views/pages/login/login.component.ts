import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';

import { navItems } from 'src/app/containers/default-layout/_nav';
import{LoginService} from 'src/app/services/login/login.service'
import {AuthService} from 'src/app/services/auth/auth.service'
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  public navItems = navItems;
  public formLogin!: FormGroup;
  buttontouched!:boolean;

  public perfectScrollbarConfig = {
    suppressScrollX: true,
  };
  constructor(
    private formBuilder: FormBuilder,
    private loginService:LoginService,
    private authService: AuthService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.buttontouched=false
    // this.buildForm();
    this.formLogin=this.formBuilder.group({
      email:['',[Validators.required]],
      password:['',[Validators.required]]
    });
  }

  buildForm(){

  }

  enviarDatos(){
    console.log("entra a enviar datos")
    this.buttontouched=true
    if(this.formLogin.valid){
      this.loginService.singin(this.formLogin.value).subscribe(response =>{
        console.log("Login exitoso!", response)
        this.authService.saveToken(response.token)
        this.router.navigate(['dashboard'])
      })
    }
    return
  }
  getError(controlname:string){
    let control = this.formLogin.get(controlname)
    if(control?.hasError("required")) return "campo obligatorio."
    if(control?.hasError("email")) return "ingrese un correo valido."
    return ""
  }



}
