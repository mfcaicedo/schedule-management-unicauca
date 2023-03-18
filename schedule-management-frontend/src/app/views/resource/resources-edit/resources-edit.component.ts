import { Component } from '@angular/core';
import { ActivatedRoute, Route, Router } from '@angular/router';
import { Resource } from 'src/app/models/resource.model';
import { ResourceService } from 'src/app/services/resource/resource.service';
import Swal from 'sweetalert2'
@Component({
  selector: 'app-resources-edit',
  templateUrl: './resources-edit.component.html',
  styleUrls: ['./resources-edit.component.scss']
})
export class ResourcesEditComponent {

  showEnvironments:boolean=false;
  resource:Resource={
    'id':0,
    'name':'',
    'resourceType':''
  }
  resourceId:number=0;
  updatedResource!:Resource;
  constructor(
    private route:ActivatedRoute,
    private routeNav : Router,
    private resourceService:ResourceService
  ) { }

  ngOnInit(): void {

  }
  ngAfterViewInit(){
    this.route.paramMap.subscribe(
      params=>{this.resourceId= Number(params.get('resourceId'));
      if(this.resourceId!= null ){
        this.resourceService.getResourceByResourceId(this.resourceId).subscribe(response=>{
          this.resource = response
        })
      }
      else{
        //this.ambiente=null;
      }

    }
    )
  }
  getResourceForm(resource:Resource){
    this.updatedResource=resource
  }
  onUpdateResource(){
    this.resourceService.updateResource(this.resourceId,this.updatedResource).subscribe(response=>{
      if(response.id){
        Swal.fire('Recurso actualizado',
        `El Recurso : ${this.updatedResource.id} | ${this.updatedResource.name} \nfue actualizado exitosamente`, 'success');
        this.routeNav.navigate(['//resource/all']);
      }
    })
  }

  getInfoRecurso(){

  }
  onShowEnvironments(){
    this.showEnvironments=true;
  }
}
