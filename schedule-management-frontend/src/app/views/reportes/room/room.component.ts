import { AfterViewInit, ChangeDetectorRef, Component, ElementRef, OnInit, QueryList, Renderer2, ViewChild, ViewChildren } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import esLocale from '@fullcalendar/core/locales/es';
import timeGridPlugin from '@fullcalendar/timegrid';
import 'jspdf-autotable';
import { ReportRoom } from 'src/app/models/ReportRoom.model';
import { Environment } from 'src/app/models/environment.model';
import { Faculty } from 'src/app/models/faculty.model';
import { FacultyService } from 'src/app/services/Faculty/faculty.service';
import { EnvironmentService } from 'src/app/services/environment/environment.service';
import { ReportService } from 'src/app/services/report/report.service';
import { OptionsFullCalendarService } from 'src/app/views/reportes/options-full-calendar.service';
import { PdfService } from 'src/app/views/reportes/pdf.service';


import { FullCalendarComponent } from '@fullcalendar/angular';
import { CalendarOptions } from '@fullcalendar/core'; // useful for typechecking
@Component({
  selector: 'app-room',
  templateUrl: './room.component.html',
  styleUrls: ['../HojaEstilosReportesSCSS/reportes.component.scss']
})
export class RoomComponent implements OnInit,AfterViewInit {
//#region  declaracionVariables
@ViewChild('radioInput', { static: false }) radioInput!: ElementRef<HTMLInputElement>;///variable que me deja manipulr los radios del dom

//@ViewChild('miTablaI', { static: false }) miTabla!: ElementRef;// permite referenciar el objeto HTML  que se va a imprimir
@ViewChildren('miTablaI') tablas!: QueryList<ElementRef>;   ///Permite referenciar todas las tablas que se van a imprimir diciendo que aun no estan creadas pero se instanciaran mas adelante con el simbolo !
@ViewChild('miTablaTITULO', { static: false })
  miTablaTITULO!: ElementRef;

@ViewChildren('appCalendario', { read: FullCalendarComponent }) calendariosPDF!: QueryList<FullCalendarComponent>;
@ViewChildren('appCalendario') elementos!: QueryList<ElementRef>;
@ViewChildren('appCalendario') appCalendarios!: QueryList<ElementRef>;

//data: any = [];
  isDisabled:boolean=false;//usado en html para los checkbox
  //showSelectedEnvironment:boolean=false;
 // ambiente!:Environment;

  //totalItems:number=0;
  //totalNumberPage:number=1;
  //paginadorEnvironment: any;
  //pageSize:number=0;
  //@Input('continueCreatingSchedule')continueCreatingSchedule:boolean=false
  //@Output()selectedEnvironment = new EventEmitter<Environment|null>();
  //@Output()isEnvironmentSelected = new EventEmitter<boolean>();
  //@ViewChildren("checkboxes") checkboxes!: QueryList<ElementRef>;


  //DATOS REPORTE
  seleccionados: string[] = [];
  seleccionadoDic: Map<string, string> = new Map<string, string>();
  columnsReporte:string[]=['Id-sch','Dia','Hora Inicio','Hora Fin','Fecha Inici','Fecha Fin',
  'Ambiente','Materia','Programa', 'color'];
  esquemas: ReportRoom[][] = [];

  //Constante
  readonly valDefecto:string="defecto";///valor por defecto que tiene una opcion del Formulario antes de ser seleccionada

  //cadenas que contienen la seleccion del formulario
  seleccionEdificio:string="";
  seleccionTipo:string="";

  //Encabezados de Tabla
  columns:string[]=['Id','Nombre','Ubicacion','Tipo Ambiente','Seleccionar'];

  //listas
  environmentTypes:string[]=[];   ///lista que contiene los tipos de ambientes definidos en el servicio y mostrados en los radios
  environmentType!: string ;      ///indica que no puede ser nulo
  listafacultades:Faculty[]=[];   ///lista que contiene las facultades que se llenan en el desplegable html
  listaEdificios:Environment[]=[];///lista que contiene los edificios para llenar el desplegable en html
  listaAmbienteHijos: Environment[]=[];
  listaTitulosReporte:string[]=[]; ///tiene los titulos de los horarios

  //banderas
  isTypeSelected:boolean=false;  ///Variable bandera que indica si se ha seleccionado el tipo
  isFacSelected:boolean=false;   ///Variable bandera que indica si se ha seleccionado la facultad
  isBuildSelected:boolean=false; ///Variable bandera que indica si se ha seleccionado el edificio

//#endregion declaracionVariables
  //-----------------------------CARGANDO LOS RECURSOS--------------------------------------------

//#region constructores
  constructor(
    private render2:Renderer2,
    private route : ActivatedRoute,
    private  facservice:FacultyService,    ///servicio encargado de traer las facultades
    private envService:EnvironmentService, ///servicio encargado de traer los edificios y ambientes filtrados
    private reportService :ReportService,  ///servicio que se ejecuta al generar el reporte
    private cdr: ChangeDetectorRef,          ///es un detector de cambios de referencias
    private pdfService: PdfService,
    protected calendaroptionsService: OptionsFullCalendarService
  ){
    /*
    this.cambiarNombreVistaPreviaGenerar();// funcion constante ejecucion para ver si hay vistas previas
    */
  }

/**
 * Metodo Inicializador que usa los servicios inyectados para llenar facultades
 * y ambientes
 */
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
    //llena tipo Ambientes para cargarse desde el inicio del componente
    this.environmentTypes=this.envService.getAllEnvironmentTypes();
    //this.lista();//COMENTADO DE METODOS YA NO USADOS
  }
 /**
  * permite detectar cambios en la instanciacion correcta de elementos creados en tiempo de ejecucion
  */
 ngAfterViewInit() {
   this.cdr.detectChanges(); // Ejecuta la detección de cambios para asegurarte de que la tabla que se conbierte en PDF esté disponible
 }

  //----------------------el siguiente es un metodo de PRUEBAS  se puede BORRAR---------------------------------------
  /**muestra un mensaaje para saber que dato se selecciono */
  alerta(msj:string){
    alert(msj);
  }
//#endregion constructores
  //-----------------------------------LLENA DATOS--------------------------------------

//#region llenarListas
  /**
   * Metodo que obtiene todos los edificios desde el id de una facultad
   * y los almacena en la variable ${listaEdificios}
   * @param variableIdFac
   */
  llenarEdificios(variableIdFac:string){
    //llenamos los Edificios lamando un metodo del servicio
    this.envService.getBuildingsByFac(variableIdFac).subscribe(
      (data: Environment[]) => {
        this.listaEdificios = data as Environment[]; // Asignar los datos emitidos a la variable listaEdificios
        //alert(this.listafacultades[0].facultyName);
        // Otro código que depende de this.listafacultades.length

      },
      (error) => {
        console.log('Error obteniendo todos Edificios', error);
      }
    );
  }
  /**
   * metodo que consulta todos los ambientes que se encuentren dentro de un Edificio
   * desde el servicio de ambientes y para luego almacenarlo en ${listaAmbienteHijos}
   */
  llenarAmbientesDeEdificio(){
    let paramTipo:string[]=[this.seleccionTipo];
    if (paramTipo[0]=="TODOS"){
      paramTipo=this.environmentTypes.slice(1);
    }
    paramTipo.forEach((tipo: string) => {
      this.envService.getEnvironmentByBuildings(tipo,this.seleccionEdificio).subscribe(
        (data: Environment[]) => {
          this.listaAmbienteHijos = this.listaAmbienteHijos.concat(data); // Asignar los datos emitidos a la variable listafacultades
        },
        (error) => {
          console.log('Error obteniendo todas las ambientes', error);
        }
      );
    });
  }
//#endregion llenarListas
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
    this.deselecciondeInputs();
    //this.alerta(((event.target as HTMLInputElement).value));
    //comprueba que la facultad seleccionada no sea la agregada por defecto
    if (((event.target as HTMLInputElement).value)!=this.valDefecto){
      this.isFacSelected=true;//cambia el valor de esta variable para que se activen los otros campos
      this.llenarEdificios(((event.target as HTMLInputElement).value));//llena la lista del desplegable
    }else{this.isFacSelected=false;//al ser la por defecto opcion deshabilita
    }
  }
   /**
   * Este metodo se lanza cuando se selecciona el edificio
   *  altera la bandera  edificio seleccionado para informar
   * que se puede seguir al siguiente paso(seleccionar los ambientes que el edificio Contiene)
   * @param event  contine el evento que disparo  la funcion
   */
  onSelectedEdificio(event:Event){
    this.deselecciondeInputs();
    this.seleccionEdificio= (event.target as HTMLInputElement).value
    if ((this.seleccionEdificio)!=this.valDefecto){
      this.isBuildSelected=true;//cambia el valor de esta variable para que se activen los otros campos

      //TODO: falta llenar la tabla y comprobar si hay un tipo seleccionado
    }else{this.isBuildSelected=false;//al ser la por defecto opcion deshabilita
    }
  }
  /**
   *
   * @param event
   */
  onSelectedType(event:Event){
    this.resetearTabla();
    this.seleccionTipo =(event.target as HTMLInputElement).value;
    this.llenarAmbientesDeEdificio();
    //TODO:se debe filtrar por tipo la tabla al lanzarse este evento
  }
  /**
   * este metodo se dispara al seleccionar los checkbox de la tabla de ambientes filtrados
   * cuando el evento es de check se agrega a la lista
   * en caso contrario se saca y quita un indice y  desplaza la lisa una posicion
   * @param item
   * @param event
   */
  onSelectingEnvironment(item: any, event: any): void {
    this.esquemas=[];
    if (event.target.checked) {
      // Agrega el item.id a la lista de seleccionados que se encargara de mostrarlos en el Html
      this.seleccionados.push(item.id);
      this.seleccionadoDic.set(item.id, item.name);
    } else {
      // Remueve el item.id de la lista de seleccionados
      const index = this.seleccionados.indexOf(item.id);
      if (index !== -1) {
        this.seleccionados.splice(index, 1);
      }
    }
  }
  GenerarReporte(){
    this.esquemas = [];
    this.listaTitulosReporte=[];
    this.seleccionados.forEach((id) => {
      //this.alerta("PREPARANDONOS PARA :"+id);
      this.reportService.getReportRoom(id).subscribe(
        (data: ReportRoom[]) => {
          const esquema = data as ReportRoom[]; // Asignar los datos emitidos a la variable esquema

          // Agregar el esquema al arreglo esquemas
          this.esquemas.push(esquema);
          this.listaTitulosReporte.push("Reporte de "+this.getNombreAmbientedesdeId(id)+"(COD: "+id+").");
        },
        (error) => {
          console.log('Error obteniendo los esquemas', error);
        }
      );
    });
    this.generarPDF(0);
  }
  /**
   *
   * @param opcion si se desea descargar las imagenes enviar 1, si se desea descargar las tablas 2 sino se desea hacer descargas 0
   */
  generarPDF(opcion:number){
    if(opcion==1){
      this._generarDescargaImagenesPDF();
    }else if(opcion==2){
      this._generarDescargaTablasPDF
    }
  }
  _generarDescargaTablasPDF(){
      this.pdfService.generarPDFsDeTabla(this.tablas.toArray());
  }
  _generarDescargaImagenesPDF(): void {
    let imgElements: ElementRef[] = [];
    this.appCalendarios.forEach((appCalendario: ElementRef) => {
      //alert("iterando")
      const imgElement = appCalendario.nativeElement.querySelector('img');
      imgElements.push(imgElement);
    });
      this.pdfService._generarPDFsDeImagenes( imgElements);
  }
  resetearTabla(){
    this.listaAmbienteHijos=[];
    this.seleccionados=[];//al volver a cargar la tabla de opciones los seleccionados vuelven a iniciar
  }
  deselecciondeInputs(){
    if (this.radioInput) {
    this.radioInput.nativeElement.checked = false;
    }
    this.resetearTabla();
  }
//#endregion Metodos para enventos html
//------------------------------OTROS METODOS (NO USADOS ecepto los de la tabla)---------------------------------------------------
//#region otros

//#endregion otros
////-------------------METODOS DE TABLA---------------------------
/**
 * es un metodo usado den un atributo de tipongstyle
 * para poder cambiar el color segun el que se reciva
 * @param programColor resive un color obtenido en formato de la base de datos
 * @returns lo transforma en un color admitido por css
 */
getBackgroundColor(programColor: string) {
  const colorMap: { [key: string]: string } = {
    'bg-red': 'red',
    'bg-orange': 'orange',
    'bg-yellow': 'yellow',
    'bg-blue': 'blue',
    'bg-green': 'green',
    'bg-purple': 'purple',
    'bg-pink': 'pink',
    'bg-teal': 'teal',
    'bg-olive': 'olive',
    'bg-lightblue': 'lightblue',
    'bg-skyblue': 'skyblue',
    'bg-navy': 'navy',
    'bg-magenta': 'magenta',
    'bg-gray': 'gray',
    'bg-white': 'white',
    'bg-black': 'black',
  };

  return colorMap[programColor] || 'white';
}
/**
 *como tenemos los id de los seleccionados buscamos el nombre en un diccionario que
 tiene el id y nombre de los ambientes
 * @param i id del ambiente
 * @returns  nombre del ambiente
 */
getNombreAmbientedesdeId(i:string): string | undefined {

  return this.seleccionadoDic.get(i);
 }
/**
 * esta funcion nos permite cambiar el nombre del boton
 * cada vez que se cree o eliminen las vistas previas
 */
 cambiarNombreVistaPreviaGenerar():void {
  setInterval(() => {
    let Valor = "Descargar Reporte(s)";
    if (this.tablas.length === 0) {
      Valor = "Vista Previa";
    }
    var btnGenerar = document.getElementById('btn-generar') as HTMLInputElement;
    if (btnGenerar) {
      btnGenerar.value = Valor;
    }
  }, 1000);
}

getOptionesCalendar(eventosParaCalendario: ReportRoom[]):CalendarOptions{
  //alert("dlpdlc");
 //return this.calendaroptionsService.getCalendarOptions(eventosParaCalendario);

 let calendarOptions: CalendarOptions = {//quita opciones de navegacion entre dias
  headerToolbar: {
    left: '',
    center: '',
    right: ''
  },
  initialView: 'timeGridWeek',//muestra semanalmente el calendario
  allDaySlot: false, //quita fila que dice TODO EL DIA
  eventOverlap: false ,
  nowIndicator: false,
  events: [{
    title: 'Evento ',
    daysOfWeek: [1,3,5],
    startTime: '05:00:00',
    endTime: '15:00:00',
    display: 'background',
    backgroundColor: 'rgba(0, 0, 255, 1)',
  }],
  plugins: [timeGridPlugin],

  locale: esLocale,//pone en espaniol el calendario
  slotMinTime: '07:00:00', // Establece el intervalo de tiempo mínimo a las 7 AM
  slotMaxTime: '22:00:00',// hasta las 10pm
  slotDuration: '01:00:00', // Establece la duración de la ranura en 2 horas
  contentHeight: 'auto', // Ajusta automáticamente el tamaño al contenido
  eventDidMount: function(info) {
    info.el.style.borderColor = 'blue';
    info.el.style.color = 'white';
  }
};
return calendarOptions;
}

}
