import { Component, Input } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Environment } from 'src/app/models/environment.model';
import { EnvironmentService } from 'src/app/services/environment/environment.service';
import Swal from 'sweetalert2';
@Component({
  selector: 'app-environments-from-resource',
  templateUrl: './environments-from-resource.component.html',
  styleUrls: ['./environments-from-resource.component.scss']
})
export class EnvironmentsFromResourceComponent {

  environments:Environment[]=[];
  columns:string[]=['Id','Tipo Ambiente','Nombre','Ubicacion','Capacidad','Facultad'];
  environmentTypes:string[]=[];
  environmentType!: string | null;

  totalItems:number=0;
  totalNumberPage:number=1;
  pageSize:number=0;
  @Input('resourceId') resourceId:number=0;
  constructor(
    private environmentService : EnvironmentService,
    private route : ActivatedRoute
  ) { }
  ngOnInit(): void {
    //todos los ambientes
    //obteniendo el id de la url debe llamarse igual que en el app-routing
    this.loadTableEnvironments([1,5])

    //todos  los tipos de ambientes
    this.environmentTypes=this.environmentService.getAllEnvironmentTypes();
  }
  ngAfterViewInit(){


  }

  updateTableEnvironments(type:string){
    if(type == 'all'){
      this.environments=this.environmentService.getEnvironmentsFromResource(this.resourceId);
    }else{
      this.environments=this.environmentService.getEnvironmentsFromResourceAndResourceType(this.resourceId,type);
    }

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
      console.log("resource id es ",this.resourceId)
      this.environmentService.getEnvironmentsFromResourcePaged(this.resourceId,pageSolicitud,pageSize).subscribe(response =>{
        if(response.status ==200){
          this.environments=response.data.elements as Environment[]
          this.totalItems=response.data.pagination.totalNumberElements as number
          this.totalNumberPage=response.data.pagination.totalNumberPage as number
          this.pageSize=response.data.pagination.size as number
        }else if(response.status==500){
          Swal.fire('Recurso en ningun ambiente',
          `Este recurso no se encuentra en ningun ambiente`, 'warning');
        }

      })

      console.log(this.environments)

  }
}
