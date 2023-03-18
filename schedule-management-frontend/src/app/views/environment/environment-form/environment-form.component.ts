import { Component,Input ,Output,EventEmitter,AfterViewChecked, ChangeDetectorRef, SimpleChanges } from '@angular/core';
import { FormGroup ,FormBuilder,Validators} from '@angular/forms';
import { ActivatedRoute,Router } from '@angular/router';
import { Environment } from 'src/app/models/environment.model';
import { Faculty } from 'src/app/models/faculty.model';
import { EnvironmentService } from 'src/app/services/environment/environment.service';
@Component({
  selector: 'app-environment-form',
  templateUrl: './environment-form.component.html',
  styleUrls: ['./environment-form.component.scss']
})
export class EnvironmentFormComponent {
  form!: FormGroup;
  //si vamos a editar un ambiente el input de ese id deberia ser readonly=true osea is edit=true
  @Input('isEdit')isEdit!:boolean;

  //obtener el ambiente cuando me llega de edit
  @Input('environment')environment!:Environment;
  @Input('isSent') isSent!:boolean;
  //obtener el ambiente del form cuando lo va a crear
  @Input('getEnvironment') getEnvironment!:boolean;

  //decirle al padre para mostrar añadir recursos al ambiente
  @Output()showAddResource = new EventEmitter<boolean>();

  //emitir al padre el abiente creado en el emmiter form
  @Output()emitterForm= new EventEmitter<Environment>();

  //variable para recolectar info del formulario
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
