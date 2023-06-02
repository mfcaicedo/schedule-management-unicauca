import { Component } from '@angular/core';
import { Faculty } from 'src/app/models/faculty.model';
import { FacultyService } from 'src/app/services/Faculty/faculty.service';

@Component({
  selector: 'app-report-faculty',
  templateUrl: './report-faculty.component.html',
  styleUrls: ['../HojaEstilosReportesSCSS/reportes.component.scss']
})
export class ReportFacultyComponent {
  //titulo de la pagina
  title="Reporte Facultad";


  //banderas
  isFacSelected:boolean=false;   ///Variable bandera que indica si se ha seleccionado la facultad


  readonly valDefecto:string="defecto";///valor por defecto que tiene una opcion del Formulario antes de ser seleccionada
  
  listafacultades:Faculty[]=[];   ///lista que contiene las facultades que se llenan en el desplegable html
  constructor(    
    private  facservice:FacultyService    ///servicio encargado de traer las facultades
  ){}
  ngOnInit(){
    //llenamos las Facultades desde el servicio de facultad
    this.facservice.getAllFaculty().subscribe(
      (data: Faculty[]) => {
        this.listafacultades = data as Faculty[]; // Asignar los datos emitidos a la variable listafacultades
        //alert(this.listafacultades[0].facultyName);    
      },
      (error) => {
        console.log('Error obteniendo todas las facultades', error);
      }
    );
  }
  onSelectedFacultad(event:Event){
    //comprueba que la facultad seleccionada no sea la agregada por defecto
    if (((event.target as HTMLInputElement).value)!=this.valDefecto){
      this.isFacSelected=true;//cambia el valor de esta variable para que se activen los otros campos
     
    }else{this.isFacSelected=false;//al ser la por defecto opcion deshabilita
    } 
  }
  GenerarReporte(){
    
  }
}
