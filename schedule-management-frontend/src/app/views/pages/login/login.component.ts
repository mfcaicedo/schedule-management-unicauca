import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Observable, map } from 'rxjs';
import { navItems } from 'src/app/containers/default-layout/_nav';
import{LoginService} from 'src/app/services/login/login.service'
import {AuthService} from 'src/app/services/auth/auth.service'

import{emailValues} from'src/app/models/emailValues'
import Swal from 'sweetalert2';
import { title } from 'process';
import { timer } from 'rxjs';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  public navItems = navItems;
  public formLogin!: FormGroup;

  formPassword !: FormGroup;
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
      username:['',[Validators.required]],
      password:['',[Validators.required]]
    });

    this.formPassword=this.formBuilder.group({
      usuario:['',[Validators.required]],
      correo:['',[Validators.required]]
    });

  }

  buildForm(){

  }

  enviarDatos(){
    // console.log("entra a enviar datos")
    this.buttontouched=true
    console.log("object login ",this.formLogin.value);
    if(this.formLogin.valid){

      this.loginService.singin(this.formLogin.value).subscribe(response =>{
        console.log("Login exitoso!", response)
        //this.authService.saveToken(response.token)
        this.router.navigate(['dashboard'])
      }
      ,error=>{
        console.log("Error en login", error)
        Swal.fire({
          title: 'Error!',
          text: 'Usuario o contraseña incorrectos',
          icon: 'error',
          showConfirmButton:false,
          timer: 2000
        }

        )
      }
      )

    }else{
      console.log("Formulario invalido")
    }
    return
  }
  getError(controlname:string){
    let control = this.formLogin.get(controlname)
    if(control?.hasError("required")) return "Campo obligatorio."
    if(control?.hasError("username")) return "Ingrese un correo valido."
    return ""
  }

  public visible = false;

  showDialog() {
    this.visible = !this.visible;
  }
  cleanForm(){
    this.formPassword.get('usuario')?.setValue('')
    this.formPassword.get('correo')?.setValue('')
  }

  handleLiveDemoChange(event: any) {
    this.visible = event;
  }

  enviarCorreo(){


    const emailValues:  emailValues ={mailFrom:"", mailTo: this.getEmail() ,subject:"" ,token:"",username:this.getUsuario()}
    console.log(emailValues)
    this.authService.sendEmailChangePassword(emailValues).subscribe(
      (respuesta => {
        console.log("Respuesta send email ",respuesta)

        if(respuesta.status!=200){
          Swal.fire("Fallo",` ${respuesta.userMessage}`,"error");
        }else{
          Swal.fire("Exito!",`${respuesta.data}`,"success");
        }

        this.showDialog()
      })


    )




  }

  getEmail(){
    return this.formPassword.get('correo')?.value;
  }
  getUsuario(){
    return this.formPassword.get('usuario')?.value;
  }

}
