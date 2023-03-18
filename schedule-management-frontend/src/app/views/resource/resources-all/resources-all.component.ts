import { Component, OnInit } from '@angular/core';
import { Resource } from 'src/app/models/resource.model';
import { ResourceService } from 'src/app/services/resource/resource.service';

@Component({
  selector: 'app-resources-all',
  templateUrl: './resources-all.component.html',
  styleUrls: ['./resources-all.component.scss']
})
export class ResourcesAllComponent implements OnInit {

  columns:string[]=['Id','Nombre','Tipo Recurso','Editar'];
  resources:Resource[]=[];
  resourceTypes:string[]=[]
  totalItems:number=1
  totalNumberPage:number=1;
  pageSize:number=0;
  paginadorResource:any
  isTypeSelected:boolean=false
  resourceType!: string ;


  constructor(
    private resourceService: ResourceService
  ){

  }
  ngOnInit(): void {
    //this.resources=this.resourceService.getAllResources();

    this.resourceService.getAllResourcesPage(1,5).subscribe(response =>{
      // console.log("Data Resource: ",response)
      this.resources = response.elements as Resource[]
      this.totalItems = response.pagination.totalNumberElements as number
      this.totalNumberPage=response.pagination.totalNumberPage as number
      this.pageSize=response.pagination.size as number
    })

    this.resourceTypes=this.resourceService.getAllResourceTypes();


    //this.totalItems=this.resourceService.getTotalItems()
    this.totalItems=1
  }
  updateTableResource(type:string){
    if(type == 'all'){
      this.isTypeSelected=false
    }else{
      this.isTypeSelected=true
      this.resourceType=type
    }

    this.loadTableResource([1,5])

  }

  loadTableResource(args: number[]) {
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
      this.resourceService.getAllResourcesPage(pageSolicitud,pageSize).subscribe((response) =>{
        console.log("Data en load Type: ",response)
        this.resources = response.elements as Resource[];
        this.totalItems = response.pagination.totalNumberElements as number
        this.paginadorResource=response;
        this.totalNumberPage=response.pagination.totalNumberPage as number
      });
    }else{
      this.resourceService.getResourcesByResourceType(this.resourceType,pageSolicitud,pageSize).subscribe(response =>{
        console.log("Data en load Type: ",response)
        this.resources = response.elements as Resource[];
        this.totalItems = response.pagination.totalNumberElements as number
        this.paginadorResource=response;
        this.totalNumberPage=response.pagination.totalNumberPage as number
      })
    }

  }
}
