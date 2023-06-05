import { Component, ElementRef, Input, OnChanges, Renderer2, SimpleChanges, ViewChild } from '@angular/core';
import { CalendarOptions } from '@fullcalendar/core'; // useful for typechecking
import esLocale from '@fullcalendar/core/locales/es';
import timeGridPlugin from '@fullcalendar/timegrid';
import html2canvas from 'html2canvas';
import { ReportRoom } from 'src/app/models/ReportRoom.model';


@Component({
  selector: 'app-calendario',
  templateUrl: './calendario.component.html',
  styleUrls: ['./calendario.component.scss']
})
export class CalendarioComponent implements OnChanges{
  @Input() eventosParaCalendario: ReportRoom[]=[];//trar los eventos para mapearlos en el fullcalendar 
  @Input() tituloImg: string="img";// titulo que contendra la imagen descargada

  esquemas: ReportRoom[]=[];//contendra los eventos que se van a mapear en el fullcalendar
  showFullCalendar: boolean = true; //bandera para que el fullcalendar se quite despues de que la imagen sea generada
    // ...

    @ViewChild('fullCalendarRef', { static: false }) fullCalendarRef: any; //referencia al fullcalendar para poder hacer la imagen
    @ViewChild('imageElement', { static: false }) imageElement!: ElementRef;// referencia a la etiqueta de imagen para poner el src
  
  imageSrc: string = '';//aqui se almacena la ruta
  
  daysOfWeekMap: { [key: string]: number } = {// como el fullcalendar asocia los dias a numeros este diccionario ayuda con esta transformacion
    lunes: 1,
    martes: 2,
    miercoles: 3,
    jueves: 4,
    viernes: 5,
    sábado: 6,
    domingo: 0,
  };
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

//-----------------------------------------------METODOS INICIADORES----------------------
  ngAfterViewInit() {
    this.generarSRCImagenCalendario2(); //Es para la etiqueta img señale a la imagen
    this.generarImagenCalendario(); //crea una imagen descargable con titulo y la guarda
    this.showFullCalendar = false; // quita al fullcalendar  y muestra la imagen
  }
  /**
   * asigna los esquemas que se combertiran en eventos 
   * a la variable que se trae como parametro imput
   * @param renderer 
   */
  constructor(private renderer: Renderer2) {
    
    this.esquemas =this.eventosParaCalendario;
    /**
    this.esquemas = [
      {
        id: 1,
        day: 'lunes',
        startingTime: '09:00',
        endingTime: '11:00', 
        startingDate: '',
        endingDate:'',
        environmentName: '',
        subjectName: '',
        programName: '',
        programColor: ''
      },
      {
        id: 2,
        day: 'martes',
        startingTime: '14:00',
        endingTime: '16:00', 
        startingDate: '',
        endingDate:'',
        environmentName: '',
        subjectName: '',
        programName: '',
        programColor: ''
      },
      {
        id: 3,
        day: 'viernes',
        startingTime: '10:00',
        endingTime: '12:00', 
        startingDate: '',
        endingDate:'',
        environmentName: '',
        subjectName: '',
        programName: '',
        programColor: ''
      }
    ];*/
   
  }
  /**
   * al haber cambios  se actualiizan los eventos del calendario 
   * @param changes 
   */
  ngOnChanges(changes: SimpleChanges) {
    if (changes['eventosParaCalendario'] && changes['eventosParaCalendario'].currentValue) {
      this.esquemas = changes['eventosParaCalendario'].currentValue;
      this.actualizarEventosCalendario();
    }    
    //this.generarSRCImagenCalendario2();
    //this.generarImagenCalendario();
  }
  /**
   * se encarga de mapear los esquemas como los eventos
   */
  actualizarEventosCalendario() {
    //const element = document.documentElement; // Obtén el elemento raíz del documento (html)
    //this.renderer.setStyle(element, '--fc-bg-event-opacity', '1'); // Cambia el valor a 0.5
    //alert(this.esquemas.length);
    this.calendarOptions.events = this.esquemas.map(esquema => ({
      title: 'Evento ' + esquema.id,
      daysOfWeek: [this.daysOfWeekMap[esquema.day.toLowerCase()]],
      startTime: esquema.startingTime,
      endTime: esquema.endingTime,
      display: 'background',
      backgroundColor: 'rgba(255, 0, 0, 1)',
    }));
  }
/**
 * generamos una imagen un url para actualizar 
 * la propiedad SRC dela etiqueta img que esta en el archivo html
 */
  generarSRCImagenCalendario2() {
    const calendarDiv = this.fullCalendarRef.element.nativeElement;//indicamos el elemento

    html2canvas(calendarDiv).then((canvas) => {

      //convertir el canvas en una imagen base64
      const imageDataUrl = canvas.toDataURL(); // Obtiene la representación de imagen del canvas en formato base64
  
      // Asigna la imagen base64 a la propiedad imageSrc para mostrarla en tu componente HTML
      this.imageSrc = imageDataUrl;

      // Actualiza la propiedad src del elemento de imagen
      this.imageElement.nativeElement.src = this.imageSrc;
      this.showFullCalendar = false;
    });
  }
  
// Genera la imagen del calendario y se hace la llamada para generar la descarga
generarImagenCalendario() {
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

      // Agregar el texto en la parte superior del canvas
      context.font = 'bold 20px Arial';
      context.textAlign = 'center';
      context.fillStyle = 'black';
      context.fillText(this.tituloImg.toUpperCase(), canvas.width / 2, 30); // Ajustar la posición vertical del texto
      this.descargar(canvasConTexto);

    } else {
      console.error('Error al obtener el contexto del canvas.');
    }
  }).catch((error) => {
    console.error('Error al generar la imagen del calendario:', error);
  });
}
/**
 * se encarga de crear un enlace a la descarga y hacer auto click para que se descargue automaticamente
 * @param canvasConTexto Contiene el canvas listo para descargar
 */
descargar(canvasConTexto:HTMLCanvasElement){
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

}
