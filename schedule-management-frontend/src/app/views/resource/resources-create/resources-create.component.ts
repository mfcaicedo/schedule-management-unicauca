import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Resource } from 'src/app/models/resource.model';
import { ResourceService } from 'src/app/services/resource/resource.service';
import Swal from 'sweetalert2';
@Component({
  selector: 'app-resources-create',
  templateUrl: './resources-create.component.html',
  styleUrls: ['./resources-create.component.scss']
})
export class ResourcesCreateComponent {

  public visible = false;
  showResource:string=''
  resource!:Resource;
  isSent:boolean=false;
  constructor(
    private resourceService : ResourceService,
    private router:Router
  ){}

  getResourceForm(resource:Resource){
    //obteniendo el resource del form
    this.resource=resource
  }

  getInfoResource(){
    this.showResource=JSON.stringify(this.resource)
    this.visible=true
  }

  toggleLiveDemo() {
    this.visible = !this.visible;
  }

  handleLiveDemoChange(event: any) {
    this.visible = event;
  }

  saveResource(){
    this.resourceService.saveResource(this.resource).subscribe(
      response => {
        if(response.id != null){
          console.log("Data",response)
          this.isSent=true
          Swal.fire('Recurso creado',
          `El Recurso : ${response.id.toString()} | ${response.name} \nfue creado exitosamente`, 'success');
          this.router.navigate(['//resource/all']);
        }else{
          Swal.fire('Problema creando recurso',
          `verifique que la información sea correcta`, 'warning');
        }

      }
      );
  }

}
