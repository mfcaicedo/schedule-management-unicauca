import { Component, OnInit,Input} from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { SlicePipe } from '@angular/common';

import {Environment} from 'src/app/models/environment.model'
import {EnvironmentService} from 'src/app/services/environment/environment.service'
import { HttpClient } from '@angular/common/http';
import { ignoreElements } from 'rxjs';

// import '@coreui/icons/css/coreui-icons.min.css';

@Component({
  selector: 'app-environments',
  templateUrl: './environments.component.html',
  styleUrls: ['./environments.component.scss'],
  providers:[SlicePipe]
})
export class EnvironmentsComponent {

  environments:Environment[]=[];
  columns:string[]=['Id','Tipo Ambiente','Nombre','Ubicacion','Capacidad','Facultad','Opciones'];
  environmentTypes:string[]=[];
  environmentType!: string ;
  isTypeSelected:boolean=false
  totalItems:number=0;
  totalNumberPage:number=1;
  pageSize:number=0;

  @Input('fromResource') fromResource:boolean=false;
  @Input('resourceId') resourceId:number=0;


  constructor(
    private environmentService : EnvironmentService,
    private route : ActivatedRoute
  ) { }
  ngOnInit(): void {
    //todos los ambientes
    //obteniendo el id de la url debe llamarse igual que en el app-routing
    if(this.fromResource==true){
      this.environments = this.environmentService.getEnvironmentsFromResource(this.resourceId);
    }else {

      // this.environments=this.environmentService.getAllEnvironments();
      this.environmentService.getAllEnvironmentsPage(1,5).subscribe(response =>{
        console.log("Data : ",response)
        this.environments=response.data.elements as Environment[]
        this.totalItems=response.data.pagination.totalNumberElements as number
        this.totalNumberPage=response.data.pagination.totalNumberPage as number
        this.pageSize=response.data.pagination.size as number
      })

    }


    //TODO todos  los tipos de ambientes
    this.environmentTypes=this.environmentService.getAllEnvironmentTypes();
  }

  updateTableEnvironments(type:string){

    if(type == 'TODOS'){
      this.isTypeSelected=false
    }else{
      this.isTypeSelected=true
      this.environmentType=type
    }
    this.loadTableEnvironments([1,5])

  }

  // aqui viene el numero de pagina solicitada y el tamaño que debe tener
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
      this.environmentService.getAllEnvironmentsByEnvironmentTypePage(this.environmentType,pageSolicitud,pageSize).subscribe(response =>{
        console.log("Data en load Type: ",response)
        this.environments=response.data.elements as Environment[]
        this.totalItems=response.data.pagination.totalNumberElements as number
        this.totalNumberPage=response.data.pagination.totalNumberPage as number


      })
    }

  }
  onPageChange(event:any){

  }
}
