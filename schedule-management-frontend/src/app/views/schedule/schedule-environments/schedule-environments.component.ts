import { NonNullAssert } from '@angular/compiler';
import { Component, ElementRef, EventEmitter, Input, OnInit, Output, QueryList, Renderer2, SimpleChanges, ViewChildren } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Environment } from 'src/app/models/environment.model';
import { EnvironmentService } from 'src/app/services/environment/environment.service';

@Component({
  selector: 'app-schedule-environments',
  templateUrl: './schedule-environments.component.html',
  styleUrls: ['./schedule-environments.component.scss']
})
export class ScheduleEnvironmentsComponent implements OnInit {

  environments:Environment[]=[];
  columns:string[]=['Id','Tipo Ambiente','Nombre','Ubicacion','Capacidad','Facultad','Seleccionar'];
  environmentTypes:string[]=[];
  environmentType!: string ;
  isDisabled:boolean=false;

  showSelectedEnvironment:boolean=false;
  ambiente!:Environment;


  isTypeSelected:boolean=false
  totalItems:number=0;
  totalNumberPage:number=1;
  paginadorEnvironment: any;
  pageSize:number=0;
  @Input('continueCreatingSchedule')continueCreatingSchedule:boolean=false
  @Output()selectedEnvironment = new EventEmitter<Environment|null>();
  @Output()isEnvironmentSelected = new EventEmitter<boolean>();
  @ViewChildren("checkboxes") checkboxes!: QueryList<ElementRef>;

  constructor(
    private environmentService : EnvironmentService,
    private render2:Renderer2,
    private route : ActivatedRoute
  ) { }
  ngOnInit(): void {
    this.environmentService.getAllEnvironmentsPage(1,5).subscribe(response =>{
      // console.log("Data : ",response)
      this.environments=response.data.elements as Environment[]
      this.totalItems=response.data.pagination.totalNumberElements as number
      this.totalNumberPage=response.data.pagination.totalNumberPage as number
      this.pageSize=response.data.pagination.size as number
    })

    if(this.continueCreatingSchedule==true ){
      this.changeSelectedEnvironment()
    }
    //TODO consumir todos los tipos de ambientes
    this.environmentTypes=this.environmentService.getAllEnvironmentTypes();
  }

  ngOnChanges(changes: SimpleChanges): void {
    if(changes['continueCreatingSchedule']){
      if(changes['continueCreatingSchedule'].currentValue == true){
        this.changeSelectedEnvironment()
      }

    }

  }

  updateTableEnvironments(type:string){
    if(type == 'all'){
      this.isTypeSelected=false
    }else{
      this.isTypeSelected=true
      this.environmentType=type
    }
    this.loadTableEnvironmentsSchedule([1,5])
  }


  onSelectingEnvironment(environment:Environment, e:Event){

    const x = e.target as HTMLInputElement
    if(x.checked){
      this.ambiente=environment;
      this.selectedEnvironment.emit(environment)
      this.isEnvironmentSelected.emit(true)
      this.isDisabled=true
      this.showSelectedEnvironment=true;
    }
  }
  changeSelectedEnvironment(){
    this.isDisabled=false
    // this.render2.setAttribute(this.casilla.nativeElement,'checked','false')
    this.checkboxes.forEach((element) => {
      element.nativeElement.checked = false;
    });
    this.selectedEnvironment.emit(null)
    this.isEnvironmentSelected.emit(false)
    this.showSelectedEnvironment=false;
  }

  loadTableEnvironmentsSchedule(args: number[]){
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
}
