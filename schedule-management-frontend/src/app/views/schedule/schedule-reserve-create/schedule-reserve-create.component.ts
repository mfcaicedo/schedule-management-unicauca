//import { Component } from '@angular/core';
import { Component,Input ,Output,EventEmitter,AfterViewChecked, ChangeDetectorRef, SimpleChanges } from '@angular/core';
import { FormGroup ,FormBuilder,Validators} from '@angular/forms';
import { ActivatedRoute,Router } from '@angular/router';
import { reserveEnvironment } from 'src/app/models/reserve-environment.model';
import { Environment } from 'src/app/models/environment.model';
import { Faculty } from 'src/app/models/faculty.model';
import { EnvironmentService } from 'src/app/services/environment/environment.service';

@Component({
  selector: 'app-schedule-reserve-create',
  templateUrl: './schedule-reserve-create.component.html',
  styleUrls: ['./schedule-reserve-create.component.scss']
})
export class ScheduleReserveCreateComponent {

  environments:Environment[]=[];
  columns:string[]=['Id','Tipo Ambiente','Nombre','Ubicacion','Capacidad','Facultad','Opciones'];
  environmentTypesTabla:string[]=[];
  environmentTypeTabla!: string ;
  isTypeSelected:boolean=false
  totalItems:number=0;
  totalNumberPage:number=1;
  pageSize:number=0;

  form!: FormGroup;
  //si vamos a editar un ambiente el input de ese id deberia ser readonly=true osea is edit=true
  @Input('isEdit')isEdit!:boolean;

  //obtener el ambiente cuando me llega de edit

  @Input('environment')environment!:Environment;

  //@Input('environment')environment!:reserveEnvironment;

  @Input('isSent') isSent!:boolean;
  //obtener el ambiente del form cuando lo va a crear
  @Input('getEnvironment') getEnvironment!:boolean;

  //decirle al padre para mostrar añadir recursos al ambiente
  @Output()showAddResource = new EventEmitter<boolean>();

  //emitir al padre el abiente creado en el emmiter form

  @Output()emitterForm= new EventEmitter<Environment>();

  //@Output()emitterForm= new EventEmitter<reserveEnvironment>();

  //variable para recolectar info del formulario
  /*formEnvironment:reserveEnvironment={
    'availableResources':[],

    'tipoEvento':"",
    'nombreEvento':"",
    'nombreEncargado':"",
    'cedulaEncargado':0,
    'recurrencia':"",
    'descripcion':"",
    'fechaInicio':'12-1-23',
    'horaInicio': ,
    'horaFin':

  };*/

  formEnvironment:Environment={
    'id':0,
    'name':'',
    'location':'',
    'capacity':0,
    'environmentType':'',
    'facultyId':'',
    'availableResources':[]
  };

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
  private fillForm(environment:Environment){

    if(this.isEdit==true){

      const environmentFill={
        'id' :environment.id,
        'name':environment.name,
        'location':environment.location,
        'capacity':environment.capacity,
        'environmentType':environment.environmentType,
        'faculty':environment.facultyId
      }
      console.log("name en fill ",environmentFill.name)
      this.form.patchValue(environmentFill);
    }
  }
  private buildForm(){
    this.form = this.formBuilder.group({
      id: ['', []],
      name: ['', [Validators.required]],
      location: ['',[Validators.required]],
      capacity: ['', [Validators.required, Validators.min(2)]],
      environmentType: ['', [Validators.required]],
      faculty:['',[Validators.required]],
    });
  }

  onSelectedValue(event:Event){

    this.form.controls['environmentType'].setValue((event.target as HTMLInputElement).value);
  }
  onSelectedFaculty(event:Event){

    this.form.controls['faculty'].setValue((event.target as HTMLInputElement).value);
  }


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

  updateTableEnvironments(type:string){

    if(type == 'all'){
      this.isTypeSelected=false
    }else{
      this.isTypeSelected=true
      this.environmentTypeTabla=type
    }
    this.loadTableEnvironments([1,5])

  }

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

  get name(){
    return this.form.get("name")
  }
  get location(){
    return this.form.get("location")
  }
  get capacity(){
    return this.form.get("capacity");
  }
  get environmentType(){
    return this.form.get("environmentType")
  }
  get faculty(){
    return this.form.get("faculty")
  }
  get isNameInvalid(){
    return  this.name?.touched && this.name?.invalid
  }
  get isLocationInvalid(){
    return  this.location?.touched && this.location?.invalid
  }
  get isCapacityInvalid(){
    return  this.capacity?.touched && this.capacity?.invalid
  }
  get isEnvironmentTypeInvalid(){
    return  this.environmentType?.touched && this.environmentType?.invalid
  }
  get isFacultyInvalid(){
    return  this.faculty?.touched && this.faculty?.invalid
  }
}
