//import { Component } from '@angular/core';
import { Component, Input, Output, EventEmitter, AfterViewChecked, ChangeDetectorRef, SimpleChanges } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { reserveEnvironment } from 'src/app/models/reserve-environment.model';
import { Environment } from 'src/app/models/environment.model';
import { Faculty } from 'src/app/models/faculty.model';
import { EnvironmentService } from 'src/app/services/environment/environment.service';
import { availabilityEnvironment } from 'src/app/models/availabilityEnvironment.model';
//import { collapseTextChangeRangesAcrossMultipleVersions } from 'typescript/lib/tsserverlibrary';
import { ReserveEnvironmentService } from "src/app/services/schedule-reserve/reserve-environment.service"
import { event } from 'src/app/models/event.model';
import { eventToSchedule, eventToScheduleAUX } from 'src/app/models/eventToschedule.model';
import { finalEventSchedule } from 'src/app/models/FinalEventSchedule.model';
import Swal from 'sweetalert2';
@Component({
  selector: 'app-schedule-reserve-create',
  templateUrl: './schedule-reserve-create.component.html',
  styleUrls: ['./schedule-reserve-create.component.scss']
})
export class ScheduleReserveCreateComponent {

  reserveEnvironments: reserveEnvironment[] = [];
  avaibilityEnvironments: availabilityEnvironment[] = [];
  event: event[] = [];
  eventToSChedule: eventToSchedule[] = [];
  columns: string[] = ['Tipo de evento', 'nombre', 'Nombre del encargado',
    'Cedula del encargado', 'Descripcion', 'Fecha Inicio', 'Recurrencia', 'Hora Inicio', 'Hora Fin'];
  columnsCheckeados: string[] = ['Tipo de salon', 'nombre', 'Ubicacion',
    'Dia', 'Fecha Inicio', 'Recurrencia', 'Hora Inicio', 'Hora Fin'];
  environments: Environment[] = [];
  columnsEnvironments: string[] = ['Id', 'Tipo Ambiente', 'Nombre', 'Ubicacion', 'Capacidad', 'Facultad'];
  reserveEnvironmentTypesTabla: string[] = [];
  reserveEnvironmentTypeTabla!: string;
  ennvironmentTypesTabla: string[] = [];
  environmentTypeTabla!: string;
  isTypeSelected: boolean = false
  totalItems: number = 0;
  totalNumberPage: number = 1;
  pageSize: number = 0;
  visible: boolean = false;
  headers: string[] = ["hora", "lunes", "martes", "miercoles", "jueves", "viernes", "sabado"]
  weekDays = ["lunes", "martes", "miercoles", "jueves", "viernes", "sabado"]
  horasDia = ["07:00:00", "09:00:00", "11:00:00", "13:00:00", "14:00:00", "16:00:00", "18:00:00", "20:00:00", "22:00:00"]
  AmbientesChekeados: eventToScheduleAUX[] = [];
  eventToScheduleList: eventToSchedule[] = [];
  check: boolean = true;
  form!: FormGroup;
  @Input('isEdit') isEdit!: boolean;
  @Input('environment') environment!: reserveEnvironment;
  @Input('isSent') isSent!: boolean;
  @Input('getEnvironment') getEnvironment!: boolean;
  @Output() showAddResource = new EventEmitter<boolean>();
  @Output() emitterForm = new EventEmitter<reserveEnvironment>();

  formEnvironment: reserveEnvironment = {

    'availableResources': [],
    'tipoEvento': "",
    'nombreEvento': "",
    'nombreEncargado': "",
    'cedulaEncargado': 0,
    'recurrencia': "",
    'descripcion': "",
    'fechaInicio': '',
    'horaInicio': '',
    'horaFin': "",
    'day': "",

  };

  formEnvironmentAvailability: availabilityEnvironment = {
    'starting_date': "",
    'starting_time': "",
    'ending_time': "",
    'recurrence': "",
    'day': "",
    "weeks": "1"
  }

  formEvent: event = {
    'eventId': 0,
    'eventName': "",
    'eventManagerName': "",
    'description': "",
    'person': "",
    'eventType': ""
  }
  formEventToSchedule: eventToSchedule = {
    'startingDate': "",
    'endingDate': "",
    'startingTime': "",
    'endingTime': "",
    'recurrence': "",
    'day': "",
    "environmentId": 0,
    'weeks': ""
  }

  aux: eventToSchedule = {
    startingDate: "",
    endingDate: "",
    startingTime: "",
    endingTime: "",
    recurrence: "",
    day: "",
    weeks: "",
    environmentId: 0
  }

  formFinalEventSchedule: finalEventSchedule = {
    event: this.formEvent,
    eventToScheduleList: []

  }

  environmentTypes: string[] = []
  environmentType!: string;
  eventTypes: string[] = []
  recurrenciaTypes: string[] = []
  facultys: string[] = [];



  constructor(
    private formBuilder: FormBuilder,
    private router: Router,
    private environmentService: EnvironmentService,
    private readonly changeDetectorRef: ChangeDetectorRef,
    private reserveService: ReserveEnvironmentService,
    

  ) {

    this.buildForm();
  }

  ngAfterViewChecked(): void {
    this.changeDetectorRef.detectChanges();
  }
  actualizarValorInput(nuevoValor: boolean) {

    console.log(nuevoValor + "valor actualizado desde el padre")
    this.check = nuevoValor;


  }


  ngOnInit(): void {
    this.environmentTypes = this.environmentService.getAllEnvironmentTypes();
    //this.facultys = this.environmentService.getAllFacultys();
    this.eventTypes = this.environmentService.getAllEventTypes();
    this.recurrenciaTypes = this.environmentService.getAllRecurrenciaTypes();


  }


  private fillForm(reserveEnvironment: reserveEnvironment) {

    if (this.isEdit == true) {

      const environmentFill = {
        'tipoEvento': reserveEnvironment.tipoEvento,
        'eventName': reserveEnvironment.nombreEvento,
        'eventEncargado': reserveEnvironment.nombreEncargado,
        'cedulaEncargado': reserveEnvironment.cedulaEncargado,
        'descripcion': reserveEnvironment.descripcion,
        'fechaInicio': reserveEnvironment.fechaInicio,
        'recurrencia': reserveEnvironment.recurrencia,
        'horaInicio': reserveEnvironment.horaInicio,
        'horaFin': reserveEnvironment.horaFin
      }
      console.log("name en fill ", environmentFill.eventName)
      this.form.patchValue(environmentFill);
    }
  }
  private buildForm() {
    this.form = this.formBuilder.group({
      tipoEvento: ['', [Validators.required]],
      eventName: ['', [Validators.required]],
      eventEncargado: ['', [Validators.required]],
      cedulaEncargado: ['', [Validators.required]],
      descripcion: ['', [Validators.required]],
      fechaInicio: ['', [Validators.required]],
      recurrencia: ['', [Validators.required]],
      horaInicio: ['', [Validators.required]],
      horaFin: ['', [Validators.required]],
      day: ['', []],
      weeks: ['', []]
    });
  }

  onSelectedEVENT(event: Event) {

    this.form.controls['tipoEvento'].setValue((event.target as HTMLInputElement).value);
  }
  onSelectedValued(event: Event) {

    this.form.controls['recurrencia'].setValue((event.target as HTMLInputElement).value);
  }
  onSelectedFaculty(event: Event) {

    this.form.controls['faculty'].setValue((event.target as HTMLInputElement).value);
  }



  setValues() {

    this.formEnvironment.tipoEvento = this.form.get('tipoEvento')?.value;
    this.formEnvironment.nombreEvento = this.form.get('eventName')?.value;
    this.formEnvironment.nombreEncargado = this.form.get('eventEncargado')?.value;
    this.formEnvironment.cedulaEncargado = this.form.get('cedulaEncargado')?.value;
    this.formEnvironment.descripcion = this.form.get('descripcion')?.value;
    this.formEnvironment.fechaInicio = this.form.get('fechaInicio')?.value;
    this.formEnvironment.recurrencia = this.form.get('recurrencia')?.value;
    this.formEnvironment.horaInicio = this.form.get('horaInicio')?.value;
    this.formEnvironment.horaFin = this.form.get('horaFin')?.value;
    this.formEnvironmentAvailability.starting_date = this.form.get('fechaInicio')?.value;
    this.formEnvironmentAvailability.starting_time = this.form.get('horaInicio')?.value;
    this.formEnvironmentAvailability.ending_time = this.form.get('horaFin')?.value;
    this.formEnvironmentAvailability.recurrence = this.form.get('recurrencia')?.value;
    this.formEnvironmentAvailability.weeks = this.form.get('weeks')?.value;
    this.formEnvironmentAvailability.day = this.form.get('day')?.value;
    if (this.formEnvironmentAvailability.recurrence == "DIA") {
      const fecha = new Date(this.formEnvironmentAvailability.starting_date);
      const diasSemana = ['LUNES', 'MARTES', 'MIERCOLES', 'JUEVES', 'VIERNES', 'SABADO'];
      this.formEnvironmentAvailability.day = diasSemana[fecha.getDay()];
      this.formEventToSchedule.day = diasSemana[fecha.getDay()];

      console.log(this.formEnvironmentAvailability.day + "dia asignado")

    }
    this.formEvent.eventType = this.form.get('tipoEvento')?.value;
    this.formEvent.eventName = this.form.get('eventName')?.value;
    this.formEvent.eventManagerName = this.form.get('eventEncargado')?.value
    this.formEvent.person = this.form.get('cedulaEncargado')?.value;
    this.formEvent.description = this.form.get('descripcion')?.value;
    this.formEvent.eventId = Math.floor(Math.random() * (100 - 1 + 1)) + 1;
    this.formEventToSchedule.startingDate = this.form.get('fechaInicio')?.value;
    this.formEventToSchedule.startingTime = this.form.get('horaInicio')?.value;
    this.formEventToSchedule.endingTime = this.form.get('horaFin')?.value;
    this.formEventToSchedule.recurrence = this.form.get('recurrencia')?.value;
    this.formEventToSchedule.weeks = this.form.get('weeks')?.value;
    //this.formEventToSchedule.day = this.form.get('day')?.value;
    console.log(this.formEnvironmentAvailability.day + "dia asignado")
    console.log(this.form.valid + "estado")
    console.log(this.form.invalid + "estado")
    if (this.form.valid) {
      console.log("formulario valido");
      this.sendInfo();
    } else {
      console.log("formulario invalido," + this.form.controls);
      Swal.fire('Error', 'El formulario es invalido,Debe rellenar todos los campos', 'warning');
    }

  }
  setValuesDTOFinal() {
    this.formFinalEventSchedule.event = this.formEvent;
    this.formFinalEventSchedule.eventToScheduleList = this.eventToScheduleList;
    this.reserveService.postSaveEventInSchedule(this.formFinalEventSchedule).subscribe(response=>{
      //aqui me da el evento ya creado, decidir que hacer despues
      console.log(response)
      if(response.data == null){
        Swal.fire('Error','No fue posible crear la reserva debido a que no se encontro un profesor con ese ID','error');
      }else{
        this.recargarComponente()
        Swal.fire('Exito','El evento y la reserva fueron creados Correctamente','success');
        console.log(response+"evento creado")
      }
  
    })
   }


  sendInfo() {
    this.reserveService.postAllEnvironmentsByEnvironmentALL(1, 5, this.formEnvironmentAvailability).subscribe(response => {
      console.log("Data : ", response)
      console.log(response.error);
      if (response.data == null) {
        Swal.fire('Error', response.errorCode, 'error');
        //this.recargarComponente();
      } else {
        this.environments = response.data.elements as Environment[]
        this.totalItems = response.data.pagination.totalNumberElements as number
        this.totalNumberPage = response.data.pagination.totalNumberPage as number
        this.pageSize = response.data.pagination.size as number
        this.visible = true;
        this.check = false;

      }

    })

  }
  recargarComponente() {
    const currentUrl = this.router.url;

    this.router.navigateByUrl('/', { skipLocationChange: true }).then(() => {
      this.router.navigate([currentUrl]);
    });
  }





  ngOnChanges(changes: SimpleChanges): void {
    if (changes['environment']) {
      // console.log("environment cambio para form ",this.environment)
      this.fillForm(this.environment);
    }
    if (changes['check']) {
       console.log(" cambio para check ",this.check)
      
    }
    if (changes['isSent']) {
      if (changes['isSent'].currentValue == true) {
        this.form.reset()
      }

    }

  }


  agregarEventToSchedule() {
    if (this.AmbientesChekeados.length == 0) {
      Swal.fire('Error', 'No se ha seleccionado ningun ambiente para reservar', 'error');
    } else {
      this.AmbientesChekeados.forEach((elemento) => {
        this.aux.startingDate = elemento.startingDate
        this.aux.startingTime = elemento.startingTime
        this.aux.endingTime = elemento.endingTime
        this.aux.recurrence = elemento.recurrence
        this.aux.day = elemento.day
        this.aux.weeks = elemento.weeks
        this.aux.environmentId = elemento.environment.id
        this.eventToScheduleList.push(this.aux)
        this.limpiarListaAux()
        console.log(this.aux + "aux")
      })
      console.log(this.eventToScheduleList + "lista de schedules")
      this.setValuesDTOFinal()

    }

  }
  limpiarListaAux() {
    this.aux = {
      startingDate: "",
      endingDate: "",
      startingTime: "",
      endingTime: "",
      recurrence: "",
      day: "",
      weeks: "",
      environmentId: 0
    }

  }


  agregarAmbiente(eventToScheduleAUX: eventToScheduleAUX) {
    this.AmbientesChekeados.push(eventToScheduleAUX);
    console.log(this.AmbientesChekeados);


  }

  get tipoEvento() {
    return this.form.get("tipoEvento")
  }
  get eventName() {
    return this.form.get("eventName")
  }
  get eventEncargado() {
    return this.form.get("eventEncargado");
  }
  get cedulaEncargado() {
    return this.form.get("cedulaEncargado")
  }
  get descripcion() {
    return this.form.get("descripcion")
  }

  get fechaInicio() {
    return this.form.get("fechaInicio")
  }

  get weeks() {
    return this.form.get("weeks")
  }
  get day() {
    return this.form.get("day")
  }

  get horaInicio() {
    return this.form.get("horaInicio")
  }

  get horaFin() {
    return this.form.get("horaFin")
  }

  get recurrencia() {
    return this.form.get("recurrencia")
  }

  getInfo() {
    this
  }

  get isEventNameInvalid() {
    return this.eventName?.touched && this.eventName?.invalid
  }
  get isTipoEventoInvalid() {
    return this.tipoEvento?.touched && this.tipoEvento?.invalid
  }
  get isEventEncargadoInvalid() {
    return this.eventEncargado?.touched && this.eventEncargado?.invalid
  }
  get isCedulaEncargadoInvalid() {
    return this.cedulaEncargado?.touched && this.cedulaEncargado?.invalid
  }
  get isDescripcionInvalid() {
    return this.descripcion?.touched && this.descripcion?.invalid
  }
  get isFechaInicioInvalid() {
    return this.fechaInicio?.touched && this.fechaInicio?.invalid
  }

  get isWeeksInvalid() {
    return this.weeks?.touched && this.weeks?.invalid
  }

  get isHoraInicioInvalid() {
    return this.horaInicio?.touched && this.horaInicio?.invalid
  }

  get isHoraFinInvalid() {
    return this.horaFin?.touched && this.horaFin?.invalid
  }

  get isRecurrenciaInvalid() {
    return this.recurrencia?.touched && this.recurrencia?.invalid
  }


  get isEnvironmentTypeInvalid() {
    return this.recurrencia?.touched && this.recurrencia?.invalid
  }

  get isDayInvalid() {
    return this.day?.touched && this.day?.invalid
  }



  get materiaSelected() {
    return this.tipoEvento?.value == "Academico"
  }
  get eventoSelected() {
    return this.tipoEvento?.value == "Administrativo"
  }

  EliminarAmbChekado(i: number): void {

    console.log(i + "indice");
    this.AmbientesChekeados.splice(i, 1);
    if(this.AmbientesChekeados.length==0){
      this.check=false;
      this.sendInfo(); //probar
    }
  }




}
