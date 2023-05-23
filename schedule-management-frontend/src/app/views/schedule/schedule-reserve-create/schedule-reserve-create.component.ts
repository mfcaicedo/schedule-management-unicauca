//import { Component } from '@angular/core';
import { Component,Input ,Output,EventEmitter,AfterViewChecked, ChangeDetectorRef, SimpleChanges } from '@angular/core';
import { FormGroup ,FormBuilder,Validators} from '@angular/forms';
import { ActivatedRoute,Router } from '@angular/router';
import { reserveEnvironment } from 'src/app/models/reserve-environment.model';
import { Environment } from 'src/app/models/environment.model';
import { Faculty } from 'src/app/models/faculty.model';
import { EnvironmentService } from 'src/app/services/environment/environment.service';
import { availabilityEnvironment } from 'src/app/models/availabilityEnvironment.model';

@Component({
  selector: 'app-schedule-reserve-create',
  templateUrl: './schedule-reserve-create.component.html',
  styleUrls: ['./schedule-reserve-create.component.scss']
})
export class ScheduleReserveCreateComponent {

  reserveEnvironments:reserveEnvironment[]=[];
  avaibilityEnvironments:availabilityEnvironment[]=[];
  columns:string[]=['Tipo de evento','nombre','Nombre del encargado',
  'Cedula del encargado','Descripcion','Fecha Inicio','Recurrencia','Hora Inicio','Hora Fin'];
  environments:Environment[]=[];
  columnsEnvironments:string[]=['Id','Tipo Ambiente','Nombre','Ubicacion','Capacidad','Facultad','Opciones'];
  reserveEnvironmentTypesTabla:string[]=[];
  reserveEnvironmentTypeTabla!: string ;
  ennvironmentTypesTabla:string[]=[];
  environmentTypeTabla!: string ;
  isTypeSelected:boolean=false
  totalItems:number=0;
  totalNumberPage:number=1;
  pageSize:number=0;
  

  form!: FormGroup;
  //si vamos a editar un ambiente el input de ese id deberia ser readonly=true osea is edit=true
  @Input('isEdit')isEdit!:boolean;

  //obtener el ambiente cuando me llega de edit

  //@Input('environment')environment!:Environment;

  @Input('environment')environment!:reserveEnvironment;

  @Input('isSent') isSent!:boolean;
  //obtener el ambiente del form cuando lo va a crear
  @Input('getEnvironment') getEnvironment!:boolean;

  //decirle al padre para mostrar añadir recursos al ambiente
  @Output()showAddResource = new EventEmitter<boolean>();

  //emitir al padre el abiente creado en el emmiter form

  //@Output()emitterForm= new EventEmitter<Environment>();

  @Output()emitterForm= new EventEmitter<reserveEnvironment>();

  //variable para recolectar info del formulario
  formEnvironment:reserveEnvironment={
    
    'availableResources':[],
    'tipoEvento':"",
    'nombreEvento':"",
    'nombreEncargado':"",
    'cedulaEncargado':0,
    'recurrencia':"",
    'descripcion':"",
    'fechaInicio':'12-1-23',
    'horaInicio': '12-1-23',
    'horaFin': "00:00"

  };

  formEnvironmentAvailability:availabilityEnvironment={
    'starting_date' :"",
    'starting_time' :"",
    'ending_time' : "",
    'recurrence' : "",
    'day': "1",
    "weeks" : "2"
  }

/*
  formEnvironment:Environment={
    'id':0,
    'name':'',
    'location':'',
    'capacity':0,
    'environmentType':'',
    'facultyId':'',
    'availableResources':[]
  };
*/
  environmentTypes:string[]=[]
  eventTypes:string[]=[]
  recurrenciaTypes:string[]=[]
  // facultys:Faculty[]=[];
  facultys:string[]=[];

  constructor(
    private formBuilder:FormBuilder,
    private router: Router,
    private environmentService:EnvironmentService,
    private readonly changeDetectorRef: ChangeDetectorRef
  ){

    this.buildForm();
  }
  ngAfterViewChecked(): void {
    this.changeDetectorRef.detectChanges();
  }

  ngOnInit():void{
    this.environmentTypes=this.environmentService.getAllEnvironmentTypes();
    this.facultys=this.environmentService.getAllFacultys();

    this.eventTypes = this.environmentService.getAllEventTypes();
    this.recurrenciaTypes = this.environmentService.getAllRecurrenciaTypes();

    // this.fillForm();

  }
  private fillForm(reserveEnvironment:reserveEnvironment){

    if(this.isEdit==true){

      const environmentFill={
        'tipoEvento' :reserveEnvironment.tipoEvento,
        'eventName' :reserveEnvironment.nombreEvento,
        'eventEncargado' :reserveEnvironment.nombreEncargado,
        'cedulaEncargado' :reserveEnvironment.cedulaEncargado,
        'descripcion' :reserveEnvironment.descripcion,
        'fechaInicio' :reserveEnvironment.fechaInicio,
        'recurrencia' :reserveEnvironment.recurrencia,
        'horaInicio' :reserveEnvironment.horaInicio,
        'horaFin' :reserveEnvironment.horaFin
      }
      console.log("name en fill ",environmentFill.eventName)
      this.form.patchValue(environmentFill);
    }
  }
  private buildForm(){
    this.form = this.formBuilder.group({
      tipoEvento: ['', []],
      eventName: ['', [Validators.required]],
      eventEncargado: ['',[Validators.required]],
      cedulaEncargado: ['',[Validators.required]],
      descripcion: ['',[Validators.required]],
      fechaInicio: ['',[Validators.required]],
      recurrencia:['',[Validators.required]],
      horaInicio: ['',[Validators.required]],
      horaFin: ['',[Validators.required]]
    });
  }

  onSelectedValue(event:Event){

    this.form.controls['environmentType'].setValue((event.target as HTMLInputElement).value);
  }
  onSelectedFaculty(event:Event){

    this.form.controls['faculty'].setValue((event.target as HTMLInputElement).value);
  }

 /*
  setValues(){
    // console.log("entra a set values ")
    this.formEnvironment.id=this.form.get('id')?.value;
    this.formEnvironment.name=this.form.get('name')?.value;
    this.formEnvironment.location=this.form.get('location')?.value;
    this.formEnvironment.capacity=this.form.get('capacity')?.value;
    this.formEnvironment.environmentType=this.form.get('environmentType')?.value;
    this.formEnvironment.facultyId=this.form.get('faculty')?.value;

    this.emitterForm.emit(this.formEnvironment);


  }
  */
 setValues(){
  // console.log("entra a set values ")
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

 }


  showResource(){
    this.setValues();
    this.showAddResource.emit(true);
  }

  ngOnChanges(changes: SimpleChanges): void {
    if(changes['environment']){
      // console.log("environment cambio para form ",this.environment)

      this.fillForm(this.environment);

    }

    if(changes['isSent'] ){
      if(changes['isSent'].currentValue==true){
        this.form.reset()
      }

    }

  }
  /*
  updateTableEnvironments(type:string){

    if(type == 'all'){
      this.isTypeSelected=false
    }else{
      this.isTypeSelected=true
      this.reserveEnvironmentTypeTabla=type
    }
    this.loadTableEnvironments([1,5])
  }
  */
  updateTableEnvironments(type:string){

    if(type == 'all'){
      this.isTypeSelected=false
    }else{
      this.isTypeSelected=true
      this.environmentTypeTabla=type
    }
    this.loadTableEnvironments([1,5])

  }
  
/*
  loadTableEnvironments(args: number[]) {
    //this.http.get(`http://localhost:8080/users?page=${page}&size=${this.paginationConfig.itemsPerPage}`)
    //this.http.get(this.endPoint+`?page=${page}&size=${this.itemsPerPage}`)
    let pageSolicitud:number = args[0];
    let pageSize: number = args[1]
      if(!pageSolicitud){
        pageSolicitud = 0;
      }
      if(!pageSize){
        pageSize=10
      }
    if(!this.isTypeSelected){
      this.environmentService.getAllEnvironmentsPage(pageSolicitud,pageSize).subscribe((response) =>{

        this.reserveEnvironments = response.data.elements as reserveEnvironment[]
        this.totalItems=response.data.pagination.totalNumberElements as number
        this.totalNumberPage=response.data.pagination.totalNumberPage as number

      });
    }else{
      this.environmentService.getAllEnvironmentsByEnvironmentTypePage(this.reserveEnvironmentTypeTabla,pageSolicitud,pageSize).subscribe(response =>{
        console.log("Data en load Type: ",response)
        this.reserveEnvironments=response.data.elements as reserveEnvironment[]
        this.totalItems=response.data.pagination.totalNumberElements as number
        this.totalNumberPage=response.data.pagination.totalNumberPage as number


      })
    }

  }
*/

loadTableEnvironments(args: number[]) {
  //this.http.get(`http://localhost:8080/users?page=${page}&size=${this.paginationConfig.itemsPerPage}`)
  //this.http.get(this.endPoint+`?page=${page}&size=${this.itemsPerPage}`)
  let pageSolicitud:number = args[0];
  let pageSize: number = args[1]
    if(!pageSolicitud){
      pageSolicitud = 0;
    }
    if(!pageSize){
      pageSize=10
    }
  if(!this.isTypeSelected){
    this.environmentService.getAllEnvironmentsPage(pageSolicitud,pageSize).subscribe((response) =>{

      this.environments = response.data.elements as Environment[]
      this.totalItems=response.data.pagination.totalNumberElements as number
      this.totalNumberPage=response.data.pagination.totalNumberPage as number

    });
  }else{
    this.environmentService.getAllEnvironmentsByEnvironmentTypePage(this.environmentTypeTabla,pageSolicitud,pageSize).subscribe(response =>{
      console.log("Data en load Type: ",response)
      this.environments=response.data.elements as Environment[]
      this.totalItems=response.data.pagination.totalNumberElements as number
      this.totalNumberPage=response.data.pagination.totalNumberPage as number


    })
  }

}

  get tipoEvento(){
    return this.form.get("tipoEvento")
  }
  get eventName(){
    return this.form.get("eventName")
  }
  get eventEncargado(){
    return this.form.get("eventEncargado");
  }
  get cedulaEncargado(){
    return this.form.get("cedulaEncargado")
  }
  get descripcion(){
    return this.form.get("descripcion")
  }

  get fechaInicio(){
    return this.form.get("fechaInicio")
  }

  get weeks(){
    return this.form.get("weeks")
  }

  get horaInicio(){
    return this.form.get("horaInicio")
  }

  get horaFin(){
    return this.form.get("horaFin")
  }
  
  get recurrencia(){
    return this.form.get("recurrencia")
  }

  getInfo(){
    this
  }

  get isEventNameInvalid(){
    return  this.eventName?.touched && this.eventName?.invalid
  }
  get isTipoEventoInvalid(){
    return  this.tipoEvento?.touched && this.tipoEvento?.invalid
  }
  get isEventEncargadoInvalid(){
    return  this.eventEncargado?.touched && this.eventEncargado?.invalid
  }
  get isCedulaEncargadoInvalid(){
    return  this.cedulaEncargado?.touched && this.cedulaEncargado?.invalid
  }
  get isDescripcionInvalid(){
    return  this.descripcion?.touched && this.descripcion?.invalid
  }
  get isFechaInicioInvalid(){
    return  this.fechaInicio?.touched && this.fechaInicio?.invalid
  }

  get isWeeksInvalid(){
    return  this.weeks?.touched && this.weeks?.invalid
  }

  get isHoraInicioInvalid(){
    return this.horaInicio?.touched && this.horaInicio?.invalid
  }

  get isHoraFinInvalid(){
    return this.horaFin?.touched && this.horaFin?.invalid
  }

  get isRecurrenciaInvalid(){
    return this.recurrencia?.touched && this.recurrencia?.invalid
  }

  
  get isEnvironmentTypeInvalid(){
    return this.recurrencia?.touched && this.recurrencia?.invalid
  }

 

  get materiaSelected(){
    return this.tipoEvento?.value == "Academico"
  }
  get eventoSelected(){
    return this.tipoEvento?.value == "Administrativo"
  }

  


}
