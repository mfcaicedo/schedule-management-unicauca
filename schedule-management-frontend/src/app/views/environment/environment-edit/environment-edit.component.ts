import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { switchMap } from 'rxjs';
import { Environment } from 'src/app/models/environment.model';
import { Resource } from 'src/app/models/resource.model';
import { EnvironmentService } from 'src/app/services/environment/environment.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-environment-edit',
  templateUrl: './environment-edit.component.html',
  styleUrls: ['./environment-edit.component.scss']
})
export class EnvironmentEditComponent {

  constructor(
    private route:ActivatedRoute,
    private environmentService:EnvironmentService
  ) { }
   show:boolean=false;
   environmentId:number | null = null;
   showEnvironment:string=' ';
   isSent:boolean=false;
    public visible = false;
    environmentParent:Environment ={
    'id':0,
    'name':'',
    'location':'',
    'capacity':0,
    'environmentType':'',
    'facultyId':'',
    'availableResources':[]

   }
   ngOnInit(): void {
     //obteniendo el id de la url debe llamarse igual que en el app-routing

     this.route.paramMap.subscribe(
       params=>{this.environmentId= Number(params.get('environmentId'));
       if(this.environmentId!= null ){
        //  this.environment=this.environmentService.getEnvironmentsByEnvironmentId(this.environmentId);
         this.environmentService.getEnvironmentsByEnvironmentId(this.environmentId).subscribe(response =>{
          console.log("Data en edit environment: ",response)
          this.environmentParent=response.data as Environment

        })
        // this.environment=this.environmentService.getEnvironmentsByEnvironmentId(this.environmentId)
      }
       else{

       }

     }
     )

     //recursos correspondientes a un ambiente
     //this.recursos= this.recursoService.getRecursosByAmbienteId(this.ambiente.idAmbiente);
   }

   onUpdateEnvironment(){
    console.log("entra a update envi")
    let status :number=0
    //llamar a recurso de save environment
    this.environmentService.updateEnvironment(this.environmentParent).subscribe(
      response => {

      status=response.status
        if(response.status ==200){

          this.environmentParent.id=response.data.id
          Swal.fire('Ambiente actualizado',
          `El ambiente : ${this.environmentParent.id.toString()} | ${this.environmentParent.facultyId} \nfue actualizado exitosamente`, 'success');

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
    const id:number=this.environmentParent.id
    //iterar por cada recurso que se agrego al add Resource llamar al metodo de on Add Resoruce por cada uno
    this.environmentParent.availableResources.forEach(recurso=> this.onAddResourceToEnvironment(recurso.id,id))

  }

  onAddResourceToEnvironment(resourceId:number,environmentId:number){
    console.log("Legan recursos para agregar ",resourceId, " envi ",environmentId)
    this.environmentService.addResourceToEnvironment(resourceId,environmentId).subscribe(
      response=> {
        console.log("Data",response)
      }
    )
  }

   showResource(){
    this.show= !this.show
  }

   onAddedResource(resource:Resource){
    //agregar resource a la lista del environment que ya esta aqui
    this.environmentParent.availableResources.push(resource)
  }

  onremoveResource(resource:Resource){
    this.environmentParent.availableResources = this.environmentParent.availableResources.filter(item => item.id != resource.id)
  }

  getInfo(){
    console.log("Environment : ",this.environmentParent)
    console.log("recursos ",this.environmentParent.availableResources)
    this.showEnvironment= JSON.stringify(this.environmentParent)
    this.visible=true
  }



  toggleLiveDemo() {
    this.visible = !this.visible;
  }

  handleLiveDemoChange(event: any) {
    this.visible = event;
  }

}
