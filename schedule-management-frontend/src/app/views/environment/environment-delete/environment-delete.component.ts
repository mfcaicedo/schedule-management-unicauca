import { Component, OnInit,ChangeDetectorRef} from '@angular/core';
import { ActivatedRoute, Route, Router } from '@angular/router';
import { timeStamp } from 'console';
import { Environment } from 'src/app/models/environment.model';
import { Resource } from 'src/app/models/resource.model';
import { EnvironmentService } from 'src/app/services/environment/environment.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-environment-delete',
  templateUrl: './environment-delete.component.html',
  styleUrls: ['./environment-delete.component.scss']
})
export class EnvironmentDeleteComponent implements OnInit {

  show:boolean=false;
  environmentId:number = 0;
  isDisabled:boolean=true;
  showEnvironment:string=' ';
  isSent:boolean=false
  public visible = false;
  constructor(
    private route:ActivatedRoute,
    //private router:Router,
    private environmentService:EnvironmentService,
    //private readonly changeDetectorRef: ChangeDetectorRef
  ) { }
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
         console.log("Data en delete environment: ",response)
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
  // ngAfterViewChecked(): void {
  //   this.changeDetectorRef.detectChanges();
  // }
  onDeleteEnvironment(){
    console.log("entra a eliminar")
    this.environmentService.deleteEnvironment(this.environmentId).subscribe
  }



}
