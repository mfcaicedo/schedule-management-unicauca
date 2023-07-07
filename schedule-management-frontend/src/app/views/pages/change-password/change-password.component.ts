import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AbstractControl,FormBuilder, FormGroup, Validators ,ValidatorFn ,ValidationErrors  } from '@angular/forms';
import { AuthService } from 'src/app/services/auth/auth.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-change-password',
  templateUrl: './change-password.component.html',
  styleUrls: ['./change-password.component.scss']
})
export class ChangePasswordComponent implements OnInit{

  id:string='';
  username:string='';
  public formChangePassword!: FormGroup;
  constructor(
    private route: ActivatedRoute,
    private formBuilder: FormBuilder,
    private authService: AuthService,
    private router: Router
  ) { }


  ngOnInit(): void {


      this.formChangePassword=this.formBuilder.group({
        // username:['',[Validators.required]],
        password:['',[Validators.required]],
        confirmPassword:['',[Validators.required]],
        tokenPassword:['',[Validators.required]]
      },
      {validator: passwordMatchValidator } // Aplica el validador personalizado}
      );




    this.route.paramMap.subscribe(
      params => {
        this.id = String(params.get('id'));

        if (this.id != null) {
          console.log("id del back ",this.id)
          this.setToken(this.id)

        }

      })
  }

  enviarCambioPassword(){


    if(this.formChangePassword.valid){
      this.authService.changePassword(this.formChangePassword.value).subscribe(respuesta =>{
        console.log("Cambio de contraseña exitoso", respuesta)
        if(respuesta.status!=200){
          Swal.fire("Fallo",` ${respuesta.userMessage}`,"error");
          this.cleanForm()
        }else{
          Swal.fire("Exito!",` ${respuesta.data}`,"success");
          this.router.navigate(['login'])
        }

      })
    }
    return
  }
  getError(controlname:string){
    let control = this.formChangePassword.get(controlname)
    if(control?.hasError("required")) return "campo obligatorio."

    return ""
  }

  setToken(id:string){
    this.formChangePassword.get('tokenPassword')?.setValue(id)
  }

  cleanForm(){
    this.formChangePassword.get('password')?.setValue('')
    this.formChangePassword.get('confirmPassword')?.setValue('')
  }



}
// Función de validación personalizada para verificar si la contraseña y la confirmación son iguales
export const passwordMatchValidator: ValidatorFn = (formGroup: AbstractControl): ValidationErrors | null => {

  const password = formGroup.get('password')?.value;
  const confirmPassword = formGroup.get('confirmPassword')?.value;
  // Verificar si las contraseñas no coinciden
  if (password !== confirmPassword) {
    return { passwordMismatch: true };
  }

  return null;
};
