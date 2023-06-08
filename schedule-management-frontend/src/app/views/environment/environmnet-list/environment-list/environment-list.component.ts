import { Component, Input, SimpleChanges, ChangeDetectorRef, Output, EventEmitter} from '@angular/core';
import { it } from 'node:test';
import { availabilityEnvironment } from 'src/app/models/availabilityEnvironment.model';
import { Environment } from 'src/app/models/environment.model';
import { EnvironmentService } from 'src/app/services/environment/environment.service';
import {ReserveEnvironmentService} from "src/app/services/reserve-environment.service";

@Component({
  selector: 'app-environment-list',
  templateUrl: './environment-list.component.html',
  styleUrls: ['./environment-list.component.scss']
})
export class EnvironmentListComponent {
  environments:Environment[]=[];
  environmentsMODI:Environment[]=[];
  columns:string[]=['Tipo Ambiente','Nombre','Ubicacion','Capacidad'];
  environmentTypes:string[]=[];
  environmentType!: string ;
  isTypeSelected:boolean=false
  totalItems:number=0;
  totalNumberPage:number=1;
  pageSize:number=0;
  @Output('envioAmbientes') envioAmbientes= new EventEmitter<Environment> ();

  @Input('ambientes') ambientes:Environment [] = [{

    id:0,
    name: '',
    location:'',
    capacity:0,
    environmentType:'',
    facultyId:'',
    availableResources: []

  }];

  @Input('formEnvironmentAvailability') formEnvironmentAvailability:availabilityEnvironment={
      'starting_date' :"",
      'starting_time' :"",
      'ending_time' : "",
      'recurrence' : "",
      'day': "1",
      "weeks" : "2"
  }

  constructor(
    private reserveService:ReserveEnvironmentService,
    private CDR : ChangeDetectorRef,
    private environmentService : EnvironmentService
    )
  {

  }


  loadTableEnvironments(args: number[], tipo: boolean) {
    //this.http.get(`http://localhost:8080/users?page=${page}&size=${this.paginationConfig.itemsPerPage}`)
    //this.http.get(this.endPoint+`?page=${page}&size=${this.itemsPerPage}`)
    //let tipo: string = args[2]

    console.log(args);
    let pageSolicitud:number = args[0];
    let pageSize: number = args[1]
      if(!pageSolicitud){
        pageSolicitud = 0;
      }
      if(!pageSize){
        pageSize=10
      }const startIndex = (pageSolicitud - 1) * pageSize;
      if(tipo == false){
        this.environmentType = "";
      }

      //this.environments.splice(0, this.environments.length);
      // return this.filterSchedules().slice(startIndex, startIndex + this.pageSize);
      this.environmentService.paginacion(
        this.environments,
        startIndex,
        pageSize,
        this.environmentType,
        tipo
      ).subscribe(response =>{
        // console.log("Data en load Time: ",response)
        this.environmentsMODI.splice(0, this.environmentsMODI.length);
        this.environmentsMODI.push(...response.elements as Environment[]);
        this.totalItems=response.paginator.totalItems as number
        this.totalNumberPage=response.paginator.totalNumberPage as number


      })

  }

  updateTableEnvironments(type:string){

    if(type == 'TODOS'){
      this.isTypeSelected=false
    }else{
      this.isTypeSelected=true
      this.environmentType=type
    }
    this.loadTableEnvironments([1,5], true)

  }

  sendInfo(){
    this.reserveService.postAllEnvironmentsByEnvironmentTypePage(this.formEnvironmentAvailability).subscribe((response)=>{

        console.log(response)
        this.environments = response.data as Environment[]
        this.CDR.detectChanges();
        this.loadTableEnvironments([1,5], false);
        console.log(this.environments);
        console.log(this.environmentsMODI);
        //this.totalItems=response.data.pagination.totalNumberElements as number
        //this.totalNumberPage=response.data.pagination.totalNumberPage as number
      });

   }

   ngOnChanges(changes: SimpleChanges): void {
    if(changes['formEnvironmentAvailability']){
      // console.log("environment cambio para form ",this.environment)

      this.loadTableEnvironments([1,5], false);

    }

  }

  agregarAmbiente(Environment: Environment,e:Event){
    const x = e.target as HTMLInputElement
    if(x.checked){
      // Seleccionaron un horario
      this.envioAmbientes.emit(Environment)
      /*this.isScheduleSelected=true //ya hay un curso horario seleccionado
      this.isCheckboxDisabled=true //deshabilitar que peuda seleccionar otros cursos
      this.showSelectedSchedule=true*/
    }
  }

  ngOnInit(): void {
    //Called after the constructor, initializing input properties, and the first call to ngOnChanges.
    //Add 'implements OnInit' to the class.
    console.log(this.formEnvironmentAvailability);
    this.environmentTypes=this.environmentService.getAllEnvironmentTypes();
    this.sendInfo();
  }

}
