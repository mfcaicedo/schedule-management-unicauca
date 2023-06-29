import { Component, ElementRef, ViewChild } from '@angular/core';
import { ReportRoom } from 'src/app/models/ReportRoom.model';
import { Person } from 'src/app/models/person.model';
import { PersonService } from 'src/app/services/person/person.service';
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
  contenidoSelectedBusquedaDocenteNombre:string="";

  esquemas: ReportRoom[][] = [];
  listaTitulosReporte:string[]=[]; ///tiene los titulos de los horarios
  listaDocenesPorNombre:Person[]=[];
  nombrePersonaReporte:string="";
  idPersonaReporte:string="";

  constructor(
    private reportService :ReportService,  ///servicio que se ejecuta al generar el reporte
    private personService :PersonService ///servicio que se ejecuta al generar el nombre de profesor
    ){
    this.desactivarEntrada();
  }
  /**
 * esta funcion nos permite cambiar el nombre del boton 
 * cada vez que se cree o eliminen las vistas previas
 */
 desactivarEntrada():void {
  /**setInterval(() => {
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
  }, 1000);**/
}
getListaProfesoresPorNombre(){
  this.reportService.getProfesoresPorNombre(this.contenidoTextNombre).subscribe(
    (data: Person[]) => {
      this.listaDocenesPorNombre = data as Person[]; // Asignar los datos emitidos a la variable listafacultades 
      //alert(nombre);     
    },
    (error) => {
      console.log('Error obteniendo name', error);
    }
  );
}
getNombreProfesor(){
  //let nombre:String ="";
  this.personService.getnameByCode(this.contenidoTextIdentificacion).subscribe(
    (data: string) => {
      this.nombrePersonaReporte = data; // Asignar los datos emitidos a la variable listafacultades 
      //alert(nombre);     
    },
    (error) => {
      console.log('Error obteniendo name', error);
    }
  );
}
GenerarReporte(){
  //let nombre=this.getNombreProfesor(this.contenidoTextIdentificacion);
 // alert("su nom "+nombre);
  this.esquemas = [];
  this.listaTitulosReporte=[];
  if(this.contenidoTextIdentificacion !== ""){
    this.idPersonaReporte=this.contenidoTextIdentificacion;
//alert("busquda id");  
  }else if(this.contenidoTextNombre !==""){
    this.nombrePersonaReporte=this.contenidoTextNombre;
    this.idPersonaReporte=this.contenidoSelectedBusquedaDocenteNombre;
    //alert("nombre"+this.idPersonaReporte+this.nombrePersonaReporte);
  }    
  this.reportService.getReportPerson(this.idPersonaReporte).subscribe(
    (data: ReportRoom[]) => {
      const esquema = data as ReportRoom[]; // Asignar los datos emitidos a la variable esquema

      // Agregar el esquema al arreglo esquemas
      this.esquemas.push(esquema);
      let cadenaMsj="";
      if (this.nombrePersonaReporte === null) {
        cadenaMsj="NO EXISTE el usuario" 
      } else {
        cadenaMsj="Reporte de "+this.nombrePersonaReporte;
      }
      this.listaTitulosReporte.push(cadenaMsj+""+this.idPersonaReporte);
    },
    (error) => {
      console.log('Error obteniendo los esquemas', error);
    }
  );
}
}
