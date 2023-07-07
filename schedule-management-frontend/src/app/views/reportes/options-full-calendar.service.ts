import { Injectable } from '@angular/core';
import { ReportRoom } from 'src/app/models/ReportRoom.model';

import { CalendarOptions } from '@fullcalendar/core'; // useful for typechecking
import esLocale from '@fullcalendar/core/locales/es';
import timeGridPlugin from '@fullcalendar/timegrid';

@Injectable({
  providedIn: 'root'
})
export class OptionsFullCalendarService {
  
  private daysOfWeekMap: { [key: string]: number } = {
    lunes: 1,
    martes: 2,
    miercoles: 3,
    jueves: 4,
    viernes: 5,
    sábado: 6,
    domingo: 0,
  };

  private calendarOptions: CalendarOptions = {
    headerToolbar: {
      left: '',
      center: '',
      right: ''
    },
    initialView: 'timeGridWeek',
    allDaySlot: false,
    eventOverlap: false,
    nowIndicator: false,
    events: [],
    plugins: [timeGridPlugin],
    locale: esLocale,
    slotMinTime: '07:00:00',
    slotMaxTime: '22:00:00',
    slotDuration: '01:00:00',
    contentHeight: 'auto',
    eventDidMount: function(info) {
      info.el.style.borderColor = 'blue';
      info.el.style.color = 'white';
    }
  };

  constructor() { }

  public getCalendarOptions(eventosParaCalendario: ReportRoom[]): CalendarOptions {
    this.updateEvents(eventosParaCalendario);
    return this.calendarOptions;
  }

  public updateEvents(eventosParaCalendario: ReportRoom[]): void {
    this.calendarOptions.events = eventosParaCalendario.map(esquema => ({
      title: 'Evento ' + esquema.id,
      daysOfWeek: [this.daysOfWeekMap[esquema.day.toLowerCase()]],
      startTime: esquema.startingTime,
      endTime: esquema.endingTime,
      display: 'background',
      backgroundColor: 'rgba(0, 0, 255, 1)',
    }));
  }
}
