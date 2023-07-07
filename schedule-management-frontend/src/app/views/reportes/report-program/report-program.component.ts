import { AfterViewInit, ChangeDetectorRef, Component, ElementRef, QueryList, ViewChild, ViewChildren } from '@angular/core';
import 'jspdf-autotable';
import { ReportRoom } from 'src/app/models/ReportRoom.model';
import { Faculty } from 'src/app/models/faculty.model';
import { Program } from 'src/app/models/program.model';
import { FacultyService } from 'src/app/services/Faculty/faculty.service';
import { ProgramService } from 'src/app/services/program/program.service';
import { ReportService } from 'src/app/services/report/report.service';
import { PdfService } from 'src/app/views/reportes/pdf.service';

@Component({
  selector: 'app-report-program',
  templateUrl: './report-program.component.html',
  styleUrls: ['../HojaEstilosReportesSCSS/reportes.component.scss']
})
export class ReportProgramComponent implements AfterViewInit{
  
  @ViewChild('radioInput', { static: false }) radioInput!: ElementRef<HTMLInputElement>;///variable que me deja manipulr los radios del dom
  @ViewChildren('miTablaI') tablas!: QueryList<ElementRef>; 
  @ViewChild('miTablaI', { static: false }) miTabla!: ElementRef;// permite referenciar el objeto HTML  que se va a imprimir


  title="Reporte Programa";
  
  isDisabled:boolean=false;//usado en html para los checkbox
  
  //encabezados de tablas  
  columnsTableProgramas:string[]=['Id Programa','Nombre del Programa','Seleccionar'];//Encabezados de Tabla Programas

  //banderas
  isFacSelected:boolean=false;   ///Variable bandera que indica si se ha seleccionado la facultad


  readonly valDefecto:string="defecto";///valor por defecto que tiene una opcion del Formulario antes de ser seleccionada
  
  //listas
  listafacultades:Faculty[]=[];   ///lista que contiene las facultades que se llenan en el desplegable html
  listasProgramas:Program[]=[];   ///lista que contiene los programaas de la facultad
  listaTitulosReporte:string[]=[]; ///tiene los titulos de los horarios

  //DATOS REPORTE
  seleccionados: string[] = [];//contiene el id de los programas
  seleccionadoDic: Map<string, string> = new Map<string, string>();//contiene el id del programa como el nombre
  columnsReporte:string[]=['Materia','Grupo', 'Ambiente','Dia','Hora Inicio','Hora Fin','Fecha Inici','Fecha Fin'];//TODO:se debe cambiar las filas de reporte este es por programa
  esquemas: ReportRoom[][] = [];//TODO:se debe cambiar el tipo de reporte este es por programa

  constructor(    
    private  facservice:FacultyService,     ///servicio encargado de traer las facultades
    private programService:ProgramService,  ///servicio encargado de traer los programas de la facultad seleccionada
    private reportService:ReportService,    ///servicio encargado de generar el reporte por cada uno  de los codigos seleccionados
    private cdr: ChangeDetectorRef,          ///es un detector de cambios de referencias
    private pdfService: PdfService
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
  /**
   * permite detectar cambios en la instanciacion correcta de elementos creados en tiempo de ejecucion
   */
  ngAfterViewInit() {
    this.cdr.detectChanges(); // Ejecuta la detección de cambios para asegurarte de que la tabla esté disponible
  }

  /**
   * este metodo es utilizado al seleccionar la facultad
   * cuando sea un valor valido (no el por defecto) cargara los programas
   * y permitira activar una vandera para en el html activar el bloque
   *  que contiene la tabla de progrmas y el boton de generar el reporte
   */
  onSelectedFacultad(event:Event){
    let valorseleccionadoEnFacultad=(event.target as HTMLInputElement).value;
    //comprueba que la facultad seleccionada no sea la agregada por defecto
    if ((valorseleccionadoEnFacultad)!=this.valDefecto){
      this.isFacSelected=true;//cambia el valor de esta variable para que se activen los otros campos
      this.cargarProgramas(valorseleccionadoEnFacultad);
    }else{this.isFacSelected=false;//al ser la por defecto opcion deshabilita
    } 
  }
  cargarProgramas(idFacultad:string){
    this.programService.getProgramByFacultyId(idFacultad).subscribe(
      (data: Program[]) => {
        this.listasProgramas = data as Program[]; // Asignar los datos emitidos a la variable listafacultades
        //alert(this.listafacultades[0].facultyName);    
      },
      (error) => {
        console.log('Error cargando los programas de la facultad seleccionada', error);
      }
    );
  }
  GenerarReporte(){

    this.listaTitulosReporte=[];
    //alert("Descargando...");
    /*
    const doc = new jsPDF();
    
      const table = this.miTabla.nativeElement;

      (doc as any).autoTable({ html: table });
      doc.save('tabla.pdf');*/
    this.esquemas = [];
    this.seleccionados.forEach((id) => {
      //this.alerta("PREPARANDONOS PARA :"+id);
      this.reportService.getReportProgram(id).subscribe(
        (data: ReportRoom[]) => {
          const esquema = data as ReportRoom[]; // Asignar los datos emitidos a la variable esquema
          
          this.listaTitulosReporte.push("PROGRAMA "+this.getNombrePrograma(id)+"(COD: "+id+").");
          // Agregar el esquema al arreglo esquemas
          this.esquemas.push(esquema);
        },
        (error) => {
          console.log('Error obteniendo los esquemas', error);
        }
      );
    });
    //this.pdfService.generarPDFsDeTabla(this.tablas.toArray());
  }
  /**
   * Este metodo sirve para controlar los items chequeados en la tabla y asi poder generar el reporte 
   * 
   * @param item es de tipo programa
   * @param event sirve para identificar si se ha chequeado o des chequeado
   */
  onSelectingPrograms(item: any, event: any): void {
    this.esquemas=[];
    if (event.target.checked) {
      //alert('Seleccionado'+ item.programId);
      // Agrega el id del programa a la lista de seleccionados que se encargara de generar el reporte
      this.seleccionados.push(item.programId);
      this.seleccionadoDic.set(item.programId, item.name);//asocia en diccionario el nombre del rograma al id
    } else {
      // Remueve el item.id de la lista de seleccionados
      const index = this.seleccionados.indexOf(item.programId);
      if (index !== -1) {
        this.seleccionados.splice(index, 1);
      }
    }
  }
  resetearTabla(){    
    this.seleccionados=[];//al volver a cargar la tabla de opciones los seleccionados vuelven a iniciar
    
  }
  descargartablas(){
    this.pdfService.generarPDFsDeTabla(this.tablas.toArray());
  }
  
  deselecciondeInputs(){  
    if (this.radioInput) {
    this.radioInput.nativeElement.checked = false;
    }
    this.resetearTabla();
  }
  
/**
 *como tenemos los id de los seleccionados buscamos el nombre en un diccionario que 
 tiene el id y nombre de los ambientes
 * @param i id del ambiente
 * @returns  nombre del ambiente
 */
getNombrePrograma(i:string): string | undefined {  
  return this.seleccionadoDic.get(i);
 }
}
