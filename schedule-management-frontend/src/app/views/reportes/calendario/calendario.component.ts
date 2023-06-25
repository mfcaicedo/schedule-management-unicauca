import { Component, ElementRef, EventEmitter, Input, OnChanges, Output, Renderer2, SimpleChanges, ViewChild } from '@angular/core';
import { CalendarOptions } from '@fullcalendar/core'; // useful for typechecking
import esLocale from '@fullcalendar/core/locales/es';
import timeGridPlugin from '@fullcalendar/timegrid';
import html2canvas from 'html2canvas';
import { ReportRoom } from 'src/app/models/ReportRoom.model';
import { PdfService } from 'src/app/views/reportes/pdf.service';


@Component({
  selector: 'app-calendario',
  templateUrl: './calendario.component.html',
  styleUrls: ['./calendario.component.scss']
})
/**
 * al usar este metodo debe tenerse en claro que ademas de crear el calendario en una imagen
 * a travez del fullcalendar (mandandole los eventos en un objeto ReportRoom)
 *  y mostrarla en donde sea llamado tambien descargara en el dispositivo
 * de forma automatica
 */
export class CalendarioComponent implements OnChanges{
  @Output() onLoadComplete: EventEmitter<void> = new EventEmitter<void>();

  @Input() eventosParaCalendario: ReportRoom[]=[];//trae desde un componente externo los datos del reporte que se van a mapear en fullcalendar como eventos
  @Input() tituloImg: string="img";// trae desde un componente externotitulo que contendra la imagen descargada por defecto sera img

  esquemas: ReportRoom[]=[];//variable que contiene los eventos que se van a mapear en el fullcalendar
  showFullCalendar: boolean = true; //variable bandera para que el fullcalendar se quite despues de que la imagen sea generada

  @ViewChild('fullCalendarRef', { static: false }) fullCalendarRef: any; //referencia al fullcalendar para poder hacer la imagen
  @ViewChild('imageElement', { static: false }) imageElement!: ElementRef;// referencia a la etiqueta de imagen para poner el src
  
  imageSrc: string = '';//variable que almacena la ruta de la imagen para mostrarse en el HTML

 //-------------------------------------constantes-------------------------------- 
  daysOfWeekMap: { [key: string]: number } = {// como el fullcalendar asocia los dias a numeros este diccionario ayuda con esta transformacion
    lunes: 1,
    martes: 2,
    miercoles: 3,
    jueves: 4,
    viernes: 5,
    sábado: 6,
    domingo: 0,
  };

   colorMap: { [key: string]: string } = {
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
//----------------------------------Estructura principal de calendario -----------------------------------

  calendarOptions: CalendarOptions = {//Declaracion de los campos Utiles de FullCalendar
    headerToolbar: {// Quitamos las opciones de navegacion entre dias
      left: '',
      center: '',
      right: ''
    },
    initialView: 'timeGridWeek',//muestra semanalmente el calendario
    allDaySlot: false, //quita fila que dice TODO EL DIA
    eventOverlap: false ,  // no permite solapamiento
    nowIndicator: false, //quita indicador de dia actual
    events: [], //eventos vacios que se llenaran al iniciar el componente
    plugins: [timeGridPlugin], 
    dayHeaderFormat: { weekday: 'long' }, // Muestra solo el día en el encabezado
    locale: esLocale,//pone en espaniol el calendario
    slotMinTime: '07:00:00', // Establece el intervalo de tiempo mínimo a las 7 AM
    slotMaxTime: '22:00:00',// hasta las 10pm
    //slotDuration: '2:00:00', // Establece la duración de la ranura en 2 horas
    contentHeight: 'auto', // Ajusta automáticamente el tamaño al contenido  
    
  slotLabelInterval: { hours: 2 },  
    slotLabelFormat: [
      { hour: 'numeric', minute: '2-digit' } // Formato de hora con minutos
    ],
    eventDidMount: function(info) {
      info.el.style.color = 'blue';
    }
  };

//-----------------------------------------------METODOS INICIADORES----------------------
/**
 * Ocurre despues de que la vista se haya generado totalmente ya qye para generar
 * la imagen el fullcalendar debe estar totalmente creado y mostrado
 */  
ngAfterViewInit() {
    this._generarSRCImagenCalendario(); //Es para la etiqueta img señale a la imagen en su atributo src
    this._generarDescargableCalendario(); //crea una imagen descargable con titulo y la descarga en el ordenador o dispositivo
    this.showFullCalendar = false; // quita al fullcalendar  y muestra la imagen en su remplazo
    this.onLoadComplete.emit();
    // Llama a la función después de que el componente se haya cargado para informar a padre que ya termino
 
  }
  /**
   * asigna los esquemas que se combertiran en eventos
   * a la variable que se trae como parametro imput no pasar valores a esta funcion
   * ya que los que tiene son servicios
   * @param renderer Permite hacer el renderizado ngAfterViewInit
   */
  constructor(private renderer: Renderer2, private serviciopdf: PdfService) {
    this.esquemas =this.eventosParaCalendario;
  }
  /**
   * Funcion que se activa cuando un @Input cambia de valor para registrar los cambios
   * y actualiza de esta forma los eventos en el calendario
   * @param changes 
   */
  ngOnChanges(changes: SimpleChanges) {
    if (changes['eventosParaCalendario'] && changes['eventosParaCalendario'].currentValue) {
      this.esquemas = changes['eventosParaCalendario'].currentValue;
      this._actualizarEventosCalendario();
    }    
    
  }
  /** */
  onLoadComplet() {
    //alert("estamaos listos");
  }
  /**
   * se encarga de actualizar los eventos 
   * de la configuracion del fullcalendar con
   * los eventos obtenidos del arreglo de esquemas
   * extrayendo cada uno de los parametros
   */
  _actualizarEventosCalendario() {
    this.calendarOptions.events = this.esquemas.map(esquema => ({
      title: esquema.subjectName+"."+esquema.courseGroup +" ("+esquema.environmentName+")",
      daysOfWeek: [this.daysOfWeekMap[esquema.day.toLowerCase()]],
      startTime: esquema.startingTime,
      endTime: esquema.endingTime,
      display: 'background',
      backgroundColor: this.colorMap[esquema.programColor] || 'black',
    }));
  }

/**
 * generamos una canvas desde el fullcalendar que esta en el HTML
 * luego lo combierte en una Imagen base64 y le saca el url
 * para actualizar la variable que se usa en el src 
 * de la etiqueta img en el HTML y poderla mostrar
 */
  _generarSRCImagenCalendario() {
    
    const calendarDiv = this.fullCalendarRef.element.nativeElement;//indicamos el elemento

    html2canvas(calendarDiv).then((canvas) => {
      //convertir el canvas en una imagen base64
      const imageDataUrl = canvas.toDataURL(); // Obtiene la representación de imagen del canvas en formato base64
  
      // Asigna la imagen base64 a la propiedad imageSrc para mostrarla en tu componente HTML
      this.imageSrc = imageDataUrl;

      // Actualiza la propiedad src del elemento de imagen
      this.imageElement.nativeElement.src = this.imageSrc;
    });
  }
  /**
   * Este metodo da formato a la descarga que se va ha hacer 
   * con las variables glovales que tiene 
   * y redirige al tipo de descarga que desea ejecutarse 
   */
  _generarDescargableCalendario() {
    const calendarDiv = this.fullCalendarRef.element.nativeElement;

    html2canvas(calendarDiv).then((canvas) => {
      // Crear un nuevo canvas con el texto 
      const canvasConTexto = document.createElement('canvas');
      const context = canvasConTexto.getContext('2d');

      if (context) {
        // Ajustar el tamaño del nuevo canvas para que quepa el calendario y el texto
        canvasConTexto.width = canvas.width;//el ancho no se modifica
        canvasConTexto.height = canvas.height + 50; // Altura adicional para el texto

        // Dibujar el calendario en el nuevo canvas
        context.drawImage(canvas, 0, 50); // Ajustar la posición vertical del calendario

        // Dibujar un rectángulo blanco en la parte superior del canvas
        context.fillStyle = 'white';
        context.fillRect(0, 0, canvasConTexto.width, 50);
        // Agregar el texto en la parte superior del canvas
        context.font = 'bold 20px Arial';
        context.textAlign = 'center';
        context.fillStyle = 'black';

        context.fillText(this.tituloImg.toUpperCase(), canvas.width / 2, 30); // Ajustar la posición vertical del texto
        this._descargaFormato(canvasConTexto,1);

      } else {
        console.error('Error al obtener el contexto del canvas.');
      }
    }).catch((error) => {
      console.error('Error al generar la imagen del calendario:', error);
    });
  }
  /**
   * 
   * @param canvasConTexto es un canvas que contiene la imagen del reporte 
   * @param opcion  si se envia el numero 1 es descargado por pdf , el 0 o cualquier otro numero por imagen 
   */
  _descargaFormato(canvasConTexto:HTMLCanvasElement,opcion:number){
    if(opcion){
      this._descargaFormatoPdf(canvasConTexto);
    }else{
      this._descargarFormatoImg(canvasConTexto);
    }
  }
/**
 * llamado desde el metodo _descargaFormato cuando se va a descargar por 
 * imagen :
 * se encarga de crear un enlace a la descarga 
 * y hacer auto click para que se descargue automaticamente
 * en formato de imagen
 * @param canvasConTexto es un canvas que contiene la imagen del reporte 
 */
  _descargarFormatoImg(canvasConTexto:HTMLCanvasElement){
      // Convertir el nuevo canvas en un objeto Blob
      canvasConTexto.toBlob((blob) => {
        if (blob) {
          // Crear un enlace de descarga
          const enlaceDescarga = document.createElement('a');
          enlaceDescarga.href = URL.createObjectURL(blob);

          // Establecer el nombre de archivo para la descarga
          enlaceDescarga.download = this.tituloImg + '.png';

          // Simular un clic en el enlace para iniciar la descarga
          enlaceDescarga.click();

          // Liberar los recursos del objeto Blob
          URL.revokeObjectURL(enlaceDescarga.href);
          
        } else {
          console.error('Error al generar la imagen del calendario.');
        }
      });
  }
  /**
   * llamado desde el metodo _descargaFormato cuando se va a descargar por 
   * pdf :
   * hace uso del servicio PdfService que descarga en Formato Pdf el elemento canva
   * @param canvasConTexto  es un canvas que contiene la imagen del reporte 
   */
  _descargaFormatoPdf(canvasConTexto:HTMLCanvasElement){
    this.serviciopdf.generarPDFsDeCanvaElement(canvasConTexto, this.tituloImg.toUpperCase());
  }

}
