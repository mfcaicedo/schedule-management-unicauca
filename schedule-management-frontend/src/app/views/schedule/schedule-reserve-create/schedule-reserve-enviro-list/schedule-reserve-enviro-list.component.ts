
import { Component, Input, SimpleChanges, ChangeDetectorRef, Output, EventEmitter } from '@angular/core';
import { it } from 'node:test';
import { finalEventSchedule } from 'src/app/models/FinalEventSchedule.model';
import { availabilityEnvironment } from 'src/app/models/availabilityEnvironment.model';
import { Environment } from 'src/app/models/environment.model';
import { event } from 'src/app/models/event.model';
import { eventToSchedule, eventToScheduleAUX } from 'src/app/models/eventToschedule.model';
import { EnvironmentService } from 'src/app/services/environment/environment.service';
import { ReserveEnvironmentService } from "src/app/services/schedule-reserve/reserve-environment.service";
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-schedule-reserve-enviro-list',
  templateUrl: './schedule-reserve-enviro-list.component.html',
  styleUrls: ['./schedule-reserve-enviro-list.component.scss']
})
export class ScheduleReserveEnviroListComponent {


  columns: string[] = ['Tipo Ambiente', 'Nombre', 'Ubicacion', 'Capacidad', 'Facultad'];
  environmentTypes: string[] = [];
  environmentType!: string;
  isTypeSelected: boolean = false
  @Output('envioAmbientes') envioAmbientes = new EventEmitter<eventToScheduleAUX>();
  @Input('ambientes') ambientes: Environment[] = [];
  @Input('check') check: boolean = false;
  @Output('checkRespone') checkRespone = new EventEmitter<boolean>();
  @Input('totalItems') totalItems: number = 0;
  @Input('totalNumberPage') totalNumberPage: number = 1;
  @Input('pageSize') pageSize: number = 0;
  @Input('formEnvironmentAvailability') formEnvironmentAvailability!: availabilityEnvironment;


  finalEventScheduleDTO: finalEventSchedule = {
    event: null,
    eventToScheduleList: []
  }
  enviromentvacio: Environment = {
    id: 0,
    name: '',
    location: '',
    capacity: 0,
    environmentType: '',
    facultyId: '',
    availableResources: []
  }
  eventToScheduleAUX: eventToScheduleAUX = {
    startingDate: '',
    startingTime: '',
    endingTime: '',
    recurrence: '',
    day: '',
    weeks: null,
    environment: this.enviromentvacio
  }




  constructor(
    private reserveService: ReserveEnvironmentService,
    private CDR: ChangeDetectorRef,
    private environmentService: EnvironmentService
  ) {

  }


  loadTableEnvironments(args: number[]) {
    let pageSolicitud: number = args[0];
    let pageSize: number = args[1]
    if (!pageSolicitud) {
      pageSolicitud = 0;
    }
    if (!pageSize) {
      pageSize = 10
    }
    if (!this.isTypeSelected) {
      this.reserveService.postAllEnvironmentsByEnvironmentALL(pageSolicitud, pageSize, this.formEnvironmentAvailability).subscribe((response) => {

        this.ambientes = response.data.elements as Environment[]
        this.totalItems = response.data.pagination.totalNumberElements as number
        this.totalNumberPage = response.data.pagination.totalNumberPage as number
        this.check=false;

      });
    } else {

    }

  }

  
  updateTableEnvironments(type: string) {

    if (type == 'TODOS') {
      this.isTypeSelected = false
    } else {
      this.isTypeSelected = true
      this.environmentType = type
    }
    this.loadTableEnvironments([1, 5])

  }



  ngOnChanges(changes: SimpleChanges): void {
    
    if (changes['formEnvironmentAvailability']) {
      console.log("environment cambio para form ")
      this.loadTableEnvironments([1, 5]);
      //this.check = false;
    }
    if (changes['check']) {
      console.log("check cambio para hijo ")
      //this.loadTableEnvironments([1, 5]);
      //this.check = false;

    }

  }

  agregarAmbiente(Environment: Environment, e: Event, formEnvironmentAvailability: availabilityEnvironment) {
    const x = e.target as HTMLInputElement
    
    if (x.checked) {
      // Seleccionaron un horario
      //x.disabled=true;  
      console.log(formEnvironmentAvailability.day + "dia primera vez")
      this.eventToScheduleAUX.day = formEnvironmentAvailability.day;
      this.eventToScheduleAUX.endingTime = formEnvironmentAvailability.ending_time;
      this.eventToScheduleAUX.recurrence = formEnvironmentAvailability.recurrence;
      this.eventToScheduleAUX.startingDate = formEnvironmentAvailability.starting_date;
      this.eventToScheduleAUX.weeks = formEnvironmentAvailability.weeks;
      this.eventToScheduleAUX.startingTime = formEnvironmentAvailability.starting_time;
      this.eventToScheduleAUX.environment = Environment;
      this.envioAmbientes.emit(this.eventToScheduleAUX);
      this.check = true;
      this.checkRespone.emit(this.check);
      this.limpiarLista()
      console.log(this.eventToScheduleAUX.day + "form")

    }
  }
  limpiarLista() {
    this.eventToScheduleAUX = {
      startingDate: '',
      startingTime: '',
      endingTime: '',
      recurrence: '',
      day: '',
      weeks: null,
      environment: this.enviromentvacio

    }

  }

  ngOnInit(): void {

    //this.environmentTypes = this.environmentService.getAllEnvironmentTypes();

  }

}
