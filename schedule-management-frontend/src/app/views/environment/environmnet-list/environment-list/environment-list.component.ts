import { Component, Input, SimpleChanges, ChangeDetectorRef, Output, EventEmitter} from '@angular/core';
import { availabilityEnvironment } from 'src/app/models/availabilityEnvironment.model';
import { Environment } from 'src/app/models/environment.model';
import {ReserveEnvironmentService} from "src/app/services/reserve-environment.service";

@Component({
  selector: 'app-environment-list',
  templateUrl: './environment-list.component.html',
  styleUrls: ['./environment-list.component.scss']
})
export class EnvironmentListComponent {
  environments:Environment[]=[];
  columns:string[]=['Id','Tipo Ambiente','Nombre','Ubicacion','Capacidad','Facultad'];
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
    private CDR : ChangeDetectorRef
    )
  {

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

  sendInfo(){
    this.reserveService.postAllEnvironmentsByEnvironmentTypePage(this.formEnvironmentAvailability).subscribe((response)=>{

        console.log(response)
        this.environments = response.data as Environment[]
        this.CDR.detectChanges();
        //this.totalItems=response.data.pagination.totalNumberElements as number
        //this.totalNumberPage=response.data.pagination.totalNumberPage as number
      });

   }

   ngOnChanges(changes: SimpleChanges): void {
    if(changes['formEnvironmentAvailability']){
      // console.log("environment cambio para form ",this.environment)

      this.sendInfo();

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
  }

}
