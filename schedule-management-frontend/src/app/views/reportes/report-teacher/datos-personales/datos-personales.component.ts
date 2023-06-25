import { Component, ElementRef, ViewChild } from '@angular/core';
import { ReportRoom } from 'src/app/models/ReportRoom.model';
import { ReportService } from 'src/app/services/report/report.service';

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

  esquemas: ReportRoom[][] = [];
  listaTitulosReporte:string[]=[]; ///tiene los titulos de los horarios
  
  constructor(
    private reportService :ReportService  ///servicio que se ejecuta al generar el reporte
    ){
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

GenerarReporte(){
  this.esquemas = [];
  this.listaTitulosReporte=[];
  if(this.contenidoTextIdentificacion !== ""){
//alert("busquda id");      
    this.reportService.getReportPerson(this.contenidoTextIdentificacion).subscribe(
      (data: ReportRoom[]) => {
        const esquema = data as ReportRoom[]; // Asignar los datos emitidos a la variable esquema

        // Agregar el esquema al arreglo esquemas
        this.esquemas.push(esquema);
        this.listaTitulosReporte.push("Horario de "+this.contenidoTextIdentificacion);
      },
      (error) => {
        console.log('Error obteniendo los esquemas', error);
      }
    );
  }else if(this.contenidoTextNombre !==""){
  alert("nombre");
  }
}
}
