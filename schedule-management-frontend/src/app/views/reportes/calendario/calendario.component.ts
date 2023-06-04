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
  @Input() eventosParaCalendario: ReportRoom[]=[];
  esquemas: ReportRoom[]=[];//contendra los eventos que se van a mapear en el fullcalendar
  showFullCalendar: boolean = true;
    // ...

    @ViewChild('fullCalendarRef', { static: false }) fullCalendarRef: any;
    @ViewChild('imageElement', { static: false }) imageElement!: ElementRef;
  
  imageSrc: string = '';
  
  daysOfWeekMap: { [key: string]: number } = {// como el fullcalendar asocia los dias a numeros este diccionario ayuda con esta transformacion
    lunes: 1,
    martes: 2,
    miercoles: 3,
    jueves: 4,
    viernes: 5,
    sábado: 6,
    domingo: 0,
  };
  calendarOptions: CalendarOptions = {//quita opciones de navegacion entre dias
    headerToolbar: {
      left: '',
      center: '',
      right: ''
    },
    initialView: 'timeGridWeek',//muestra semanalmente el calendario
    allDaySlot: false, //quita fila que dice TODO EL DIA
    eventOverlap: false ,
    nowIndicator: false,
    events: [],
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
  ngAfterViewInit() {
    this.generarImagenCalendario();
    this.showFullCalendar = false;
  }
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
  ngOnChanges(changes: SimpleChanges) {
    if (changes['eventosParaCalendario'] && changes['eventosParaCalendario'].currentValue) {
      this.esquemas = changes['eventosParaCalendario'].currentValue;
      this.actualizarEventosCalendario();
    }
    
    this.generarImagenCalendario();
  }
  actualizarEventosCalendario() {
    const element = document.documentElement; // Obtén el elemento raíz del documento (html)
    this.renderer.setStyle(element, '--fc-bg-event-opacity', '1'); // Cambia el valor a 0.5
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
  getOptions():CalendarOptions{
    return this.calendarOptions;
  }
  generarImagenCalendario() {
    const calendarDiv = this.fullCalendarRef.element.nativeElement;

    html2canvas(calendarDiv).then((canvas) => {
      // Aquí puedes realizar cualquier manipulación adicional en el canvas, como cambiar su tamaño, agregar texto, etc.
  
      // Una vez que hayas finalizado, puedes convertir el canvas en una imagen base64
      const imageDataUrl = canvas.toDataURL(); // Obtiene la representación de imagen del canvas en formato base64
  
      // Asigna la imagen base64 a la propiedad imageSrc para mostrarla en tu componente HTML
      this.imageSrc = imageDataUrl;

      // Actualiza la propiedad src del elemento de imagen
      this.imageElement.nativeElement.src = this.imageSrc;
      this.showFullCalendar = false;
    });
  }
  
}
