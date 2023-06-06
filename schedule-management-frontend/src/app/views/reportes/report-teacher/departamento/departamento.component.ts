import { ChangeDetectorRef, Component, OnInit, Renderer2 } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Faculty } from 'src/app/models/faculty.model';
import { FacultyService } from 'src/app/services/Faculty/faculty.service';
import { ReportService } from 'src/app/services/report/report.service';
import { PdfService } from '../../pdf.service';

@Component({
  selector: 'app-departamento',
  templateUrl: './departamento.component.html',
  styleUrls: ['./departamento.component.scss','../../HojaEstilosReportesSCSS/reportes.component.scss']
})
export class DepartamentoComponent implements OnInit {
  //banderas
  isFacSelected:boolean=false;   ///Variable bandera que indica si se ha seleccionado la facultad
  isDepartmentSelected:boolean=false; ///Variable bandera que indica si se ha seleccionado el edificio
  
  //Constante
  readonly valDefecto:string="defecto";///valor por defecto que tiene una opcion del Formulario antes de ser seleccionada
  
  //listas
  listafacultades:Faculty[]=[];   ///lista que contiene las facultades que se llenan en el desplegable html
  

  //-----------------------------CARGANDO LOS RECURSOS--------------------------------------------
 
//#region constructores 
constructor(
  private render2:Renderer2,
  private route : ActivatedRoute,
  private  facservice:FacultyService,    ///servicio encargado de traer las facultades
  private reportService :ReportService,  ///servicio que se ejecuta al generar el reporte
  private cdr: ChangeDetectorRef,          ///es un detector de cambios de referencias
  private pdfService: PdfService,
){ }

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
  /*

 //----------------------METODOS DISPARADOS POR EVENTOS DE SELECCION----------------------------------------
 //#region  Metodos para enventos html
  /**
   * Este metodo se lanza cuando se selecciona la facultad
   * llama al metodo parallena los edificios
   *  y altera la bandera de facultad para informar 
   * que se puede seguir al siguiente paso(seleccionar edificio)
   * @param event  contine el evento que disparo  la funcion
   */
  onSelectedFacultad(event:Event){
    //this.alerta(((event.target as HTMLInputElement).value));
    //comprueba que la facultad seleccionada no sea la agregada por defecto
    if (((event.target as HTMLInputElement).value)!=this.valDefecto){
      this.isFacSelected=true;//cambia el valor de esta variable para que se activen los otros campos
      this.llenarDepartamentos(((event.target as HTMLInputElement).value));//llena la lista del desplegable
    }else{this.isFacSelected=false;//al ser la por defecto opcion deshabilita
    }
  }
   //-----------------------------------LLENA DATOS--------------------------------------

  /**
   * Metodo que obtiene todos los edificios desde el id de una facultad 
   * y los almacena en la variable ${listaEdificios}
   * @param variableIdFac 
   */
  llenarDepartamentos(variableIdFac:string){
    //llenamos los Edificios lamando un metodo del servicio
    /**
    this.envService.getBuildingsByFac(variableIdFac).subscribe(
      (data: Environment[]) => {
        this.listaEdificios = data as Environment[]; // Asignar los datos emitidos a la variable listaEdificios
        //alert(this.listafacultades[0].facultyName);    
        // Otro código que depende de this.listafacultades.length
    
      },
      (error) => {
        console.log('Error obteniendo todos Edificios', error);
      }
    );*/
  }
}
