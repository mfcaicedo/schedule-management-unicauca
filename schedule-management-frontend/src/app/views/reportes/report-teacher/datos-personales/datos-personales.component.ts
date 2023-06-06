import { Component, ElementRef, ViewChild } from '@angular/core';

@Component({
  selector: 'app-datos-personales',
  templateUrl: './datos-personales.component.html',
  styleUrls: ['./datos-personales.component.scss','../../HojaEstilosReportesSCSS/reportes.component.scss']
})
export class DatosPersonalesComponent {
  @ViewChild('textIdentificacion') TextIdentificacion!: ElementRef<HTMLInputElement>; 
  @ViewChild('textNombre') TextNombre!: ElementRef<HTMLInputElement>; 

  contenidoTextIdentificacion:string="";
  contenidoTextNombre:string="";
  constructor(){
    this.desactivarEntrada();
  }
  /**
 * esta funcion nos permite cambiar el nombre del boton 
 * cada vez que se cree o eliminen las vistas previas
 */
 desactivarEntrada():void {
  setInterval(() => {
    if (this.contenidoTextIdentificacion!=="") {
      this.TextNombre.nativeElement.disabled=true;
    }else{
      this.TextNombre.nativeElement.disabled=false;
    }
   
    if (this.contenidoTextNombre!=="") {
      this.TextIdentificacion.nativeElement.disabled=true;
    }else{
      this.TextIdentificacion.nativeElement.disabled=false;
    }
  }, 1000);
}
BuscarProfesor(){

}
}
