import { ChangeDetectorRef, Component, OnInit, Renderer2 } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ReportRoom } from 'src/app/models/ReportRoom.model';
import { Department } from 'src/app/models/department.model';
import { Faculty } from 'src/app/models/faculty.model';
import { Person } from 'src/app/models/person.model';
import { FacultyService } from 'src/app/services/Faculty/faculty.service';
import { DepartmentService } from 'src/app/services/department/department.service';
import { ReportService } from 'src/app/services/report/report.service';
import { PdfService } from '../../pdf.service';

@Component({
  selector: 'app-departamento',
  templateUrl: './departamento.component.html',
  styleUrls: ['./departamento.component.scss','../../HojaEstilosReportesSCSS/reportes.component.scss']
})
export class DepartamentoComponent implements OnInit {
  isDisabled:boolean=false;//usado en html para los checkbox
  //banderas
  isFacSelected:boolean=false;        ///Variable bandera que indica si se ha seleccionado la facultad
  isDepartmentSelected:boolean=false; ///Variable bandera que indica si se ha seleccionado el edificio
  
  //Constante
  readonly valDefecto:string="defecto";///valor por defecto que tiene una opcion del Formulario antes de ser seleccionada
  
  //DATOS REPORTE
  seleccionados: string[] = [];//contiene el id de los DOCENTES
  seleccionadoDic: Map<string, string> = new Map<string, string>();//contiene el id del DOCENTE como el nombre
  esquemas: ReportRoom[][] = [];

  //listas
  listafacultades:Faculty[]=[];        ///lista que contiene las facultades que se llenan en el desplegable html
  listaDepatamentos:Department[]=[];   ///lista que contiene los deartamentos que pertenecen a la facultad seleccionada
  listasDocente:Person[]=[]; 
  listaTitulosReporte:string[]=[]; ///tiene los titulos de los horarios

  columnsTableDocente:string[]=['Identificacion','Nombre del Docente','Seleccionar'];//Encabezados de Tabla Docente

  filterText: string="";
  filteredData: Person[]=[];
  //-----------------------------CARGANDO LOS RECURSOS--------------------------------------------
 
//#region constructores 
constructor(
  private render2:Renderer2,
  private route : ActivatedRoute,
  private  facservice:FacultyService,         ///servicio encargado de traer las facultades
  private reportService :ReportService,       ///servicio que se ejecuta al generar el reporte
  private departService :DepartmentService,   ///Servicio para llenar los departamentos dentro de la fac seleccionada por el usuario
  private cdr: ChangeDetectorRef,          ///es un detector de cambios de referencias
  private pdfService: PdfService,
){ }

ngOnInit(){
  //llenamos las Facultades desde el servicio de facultad
  this.llenarFacultades();
}
applyFilter() {
  if (this.filterText) {
    this.filteredData = this.listasDocente.filter(item =>
      item.fullName.toLowerCase().includes(this.filterText.toLowerCase())
    );
  } else {
    this.filteredData = this.listasDocente;
  }
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
      let idFacultadSeleccionada=((event.target as HTMLInputElement).value);
      this.llenarDepartamentos(idFacultadSeleccionada);//llena la lista del desplegable
    }else{this.isFacSelected=false;//al ser la por defecto opcion deshabilita
    }
  }
  
  onSelectedDepartamento(event:Event){
    //this.alerta(((event.target as HTMLInputElement).value));
    //comprueba que la facultad seleccionada no sea la agregada por defecto
    this.seleccionados=[];
    if (((event.target as HTMLInputElement).value)!=this.valDefecto){
      
      let idDeptoSeleccionado=((event.target as HTMLInputElement).value);
      this.isDepartmentSelected=true;
      this.llenarDocentes(idDeptoSeleccionado);
     // alert(idFacultadSeleccionada);//llena la lista del desplegable
    }else{this.isDepartmentSelected=false;//al ser la por defecto opcion deshabilita
    }
  }
  onSelectingDocente(item: any, event: any): void {
    //this.esquemas=[];
    if (event.target.checked) {
      //alert('Seleccionado'+ item.programId);
      this.seleccionados.push(item.personCode);
      this.seleccionadoDic.set(item.personCode, item.fullName);//asocia en diccionario el nombre del rograma al id
    } else {
      // Remueve el item.id de la lista de seleccionados
      const index = this.seleccionados.indexOf(item.personCode);
      if (index !== -1) {
        this.seleccionados.splice(index, 1);
      }
    }
  }
   //-----------------------------------LLENA DATOS--------------------------------------

  /**
   * Metodo que obtiene todos los edificios desde el id de una facultad 
   * y los almacena en la variable ${listaEdificios}
   * @param variableIdFac 
   */
  llenarDepartamentos(variableIdFac:string){
   
    this.departService.getByFaculty(variableIdFac).subscribe(
      (data: Department[]) => {
        this.listaDepatamentos = data as Department[];     
      },
      (error) => {
        console.log('Error obteniendo todos Edificios', error);
      }
    );
    }
  llenarFacultades(){
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
  llenarDocentes(idDepto :string){
    this.reportService.getProfesorPorDeptoId(idDepto).subscribe(
      (data: Person[]) => {
        this.listasDocente = data as Person[]; // Asignar los datos emitidos a la variable listafacultades
        this.filteredData=data as Person[];     //asigna a una variable que almacena termporalmente y funciona como filtro
        //alert(this.listafacultades[0].facultyName);    
      },
      (error) => {
        console.log('Error cargando los programas de la facultad seleccionada', error);
      }
    );
    //this.filteredData = this.listasDocente;
  }
  
  GenerarReporte(){
    this.esquemas = [];
    this.listaTitulosReporte=[];
    this.seleccionados.forEach((id) => {
      //alert("PREPARANDONOS PARA :"+id);
      this.reportService.getReportPerson(id).subscribe(
        (data: ReportRoom[]) => {
          const esquema = data as ReportRoom[]; // Asignar los datos emitidos a la variable esquema
  
          // Agregar el esquema al arreglo esquemas
          this.esquemas.push(esquema);
          this.listaTitulosReporte.push("Reporte de "+this.getNombreDocenteId(id)+"(COD: "+id+").");
        },
        (error) => {
          console.log('Error obteniendo los esquemas', error);
        }
      );
    });
  }
  getNombreDocenteId(i:string): string | undefined {
  
    return this.seleccionadoDic.get(i);
   }
}
