import { ChangeDetectorRef, Component, EventEmitter, Input, Output, SimpleChanges } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Resource } from 'src/app/models/resource.model';
import { ResourceService } from 'src/app/services/resource/resource.service';

@Component({
  selector: 'app-resources-form',
  templateUrl: './resources-form.component.html',
  styleUrls: ['./resources-form.component.scss']
})
//F:\Universidad\2022.2\Proyecto i\gestion-horaria\gestion-horaria-front\src\scss\_formsUDC.scss
export class ResourcesFormComponent {
  form!: FormGroup;
  //si vamos a editar un ambiente el input de ese id deberia ser readonly=true osea is edit=true
  @Input('isEdit')isEdit!:boolean;
  resourceTypes:string[]=[]
  @Input('resource') resource!:Resource;
  //emitir al padre el abiente creado en el emmiter form
  @Output()emitterForm= new EventEmitter<Resource>();
  @Input() isSent!:boolean;
  formResource:Resource={
    'id':0,
    'name':'',
    'resourceType':''
  }
  constructor(
    private formBuilder:FormBuilder,
    private router: Router,
    private resourceService:ResourceService,
    private readonly changeDetectorRef: ChangeDetectorRef
  ){

    this.buildForm();
  }
  ngOnInit(){
    this.resourceTypes=this.resourceService.getAllResourceTypes()
    this.fillForm()

  }
  fillForm(){
    if(this.isEdit==true){
      const resourceFill={
        'id':this.resource.id,
        'name':this.resource.name,
        'resourceType':this.resource.resourceType

      }
      this.form.patchValue(resourceFill);
    }
  }
  private buildForm(){
    this.form = this.formBuilder.group({
      id: ['', []],
      name: ['', [Validators.required , Validators.minLength(2)]],
      resourceType: ['', [Validators.required]],
    });
  }
  ngOnChanges(changes: SimpleChanges): void {
    if(changes['resource']){
      this.resource=changes['resource'].currentValue
      this.fillForm();
    }

  }
  onSelectedValue(event:Event){

    this.form.controls['resourceType'].setValue((event.target as HTMLInputElement).value);
  }

  setValues(){
    this.formResource.id=this.form.get('id')?.value;
    this.formResource.name=this.form.get('name')?.value;
    this.formResource.resourceType=this.form.get('resourceType')?.value;

    this.emitterForm.emit(this.formResource)
  }

  get name(){
    return this.form.get("name")
  }
  get resourceType(){
    return this.form.get("resourceType")
  }
  get isNameInvalid(){
    return  this.name?.touched && this.name?.invalid
  }
  get isResourceTypeInvalid(){
    return  this.resourceType?.touched && this.resourceType?.invalid
  }

}
