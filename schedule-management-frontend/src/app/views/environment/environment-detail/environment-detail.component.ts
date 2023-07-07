import { Component, OnInit,ChangeDetectorRef} from '@angular/core';
import { ActivatedRoute, Route, Router } from '@angular/router';
import { timeStamp } from 'console';
import { Environment } from 'src/app/models/environment.model';
import { Resource } from 'src/app/models/resource.model';
import { EnvironmentService } from 'src/app/services/environment/environment.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-environment-detail',
  templateUrl: './environment-detail.component.html',
  styleUrls: ['./environment-detail.component.scss']
})
export class EnvironmentDetailComponent implements OnInit {

  show:boolean=false;
  environment!:Environment;
  isDisabled:boolean=true;
  showEnvironment:string=' ';
  isSent:boolean=false
  public visible = false;
  constructor(
    private router:Router,
    private environmentService:EnvironmentService,
    private readonly changeDetectorRef: ChangeDetectorRef
  ) { }

  ngOnInit(): void {

  }
  ngAfterViewChecked(): void {
    this.changeDetectorRef.detectChanges();
  }

  showResource(){
    this.show= !this.show
  }

  getEnvironmentForm(environment:Environment){
    // console.log("environment en detail ",environment)
    this.environment=environment;
  }
  onAddedResource(resource:Resource){
    //agregar resource a la lista del environment que ya esta aqui
    this.environment.availableResources.push(resource)
  }
  onRemoveResource(resource:Resource){
    const index = this.environment.availableResources.indexOf(resource)
    this.environment.availableResources.splice(index,1);
  }

  getInfo(){
    // console.log("Environment : ",this.environment)
    // console.log("recursos ",this.environment.availableResources)
    this.showEnvironment= JSON.stringify(this.environment)
    this.visible=true
  }


  toggleLiveDemo() {
    this.visible = !this.visible;
  }

  handleLiveDemoChange(event: any) {
    this.visible = event;
  }

  showDetail(env:Environment){
    return 'Id: '+env.id+'\n'+'Nombre: '+env.name
  }

  onSaveEnvironment(){
    // console.log("entra a save envi")
    let status :number=0
    //llamar a recurso de save environment
  this.environmentService.saveEnvironment(this.environment).subscribe(
    response => {

      status=response.status
      if(response.status ==200){

        this.environment.id=response.data.id
        Swal.fire('Ambiente creado',
        `El ambiente : ${this.environment.id.toString()} | ${this.environment.facultyId} \nfue creado exitosamente`, 'success');
        this.router.navigate(['//environment/all']);
        this.isSent=true //enviar señal al formulario hijo de que puede limpiarse
        this.show=false
      }

    }
    );
    if(status ==200){
      this.callingAddResourcesToEnvironment();
    }
  }
  async callingAddResourcesToEnvironment(){
    const id:number=this.environment.id
    //iterar por cada recurso que se agrego al add Resource llamar al metodo de on Add Resoruce por cada uno
    this.environment.availableResources.forEach(recurso=> this.onAddResourceToEnvironment(recurso.id,id))

  }

  onAddResourceToEnvironment(resourceId:number,environmentId:number){
    // console.log("Legan recursos para agregar ",resourceId, " envi ",environmentId)
    this.environmentService.addResourceToEnvironment(resourceId,environmentId).subscribe(
      response=> {
        console.log("Data",response)
      }
    )
  }
}
