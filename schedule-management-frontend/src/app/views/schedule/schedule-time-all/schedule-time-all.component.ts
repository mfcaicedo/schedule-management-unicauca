import { Component, Output,EventEmitter, Input, ViewChildren, QueryList, ElementRef } from '@angular/core';
import { Environment } from 'src/app/models/environment.model';
import {Schedule} from 'src/app/models/schedule.model'
import {ScheduleService} from 'src/app/services/schedule/schedule.service'

@Component({
  selector: 'app-schedule-time-all',
  templateUrl: './schedule-time-all.component.html',
  styleUrls: ['./schedule-time-all.component.scss']
})
export class ScheduleTimeAllComponent {

  columns:string[]=["Id","Dia","Hora Inicio","Hora Fin","Seleccionado"]
  weekDays:string[]=["Lunes","Martes","Miercoles","Jueves","Viernes","Sabado"]
  bloques:number[]=[2,4]
  showSelectedSchedule:boolean=false;
  availableSchedules:Schedule[]=[];
  allSchedules:Schedule[]=[];
  isCheckboxDisabled:boolean=false;
  schedule!:Schedule;
  isScheduleSelected:boolean=false;


  selectedBlock:number=0;
  selectedDay: string ='';
  totalItems:number=0;
  totalNumberPage:number=1;
  paginadorEnvironment: any;
  pageSize:number=0;
  bloque:number =0
  filteredSchedules:Schedule[]=[]
  @Input('takenProfessorSchedule') takenProfessorSchedules:Schedule[]=[];
  @Input('takenEnvironmentSchedule') takenEnvironmentSchedules: Schedule[]=[]
  @Output() selectedSchedule = new EventEmitter<Schedule| null>();
  @ViewChildren("checkboxes") checkboxes!: QueryList<ElementRef>;



  constructor(
    private scheduleService:ScheduleService
  ){

  }
  ngOnInit(){
    // this.weekDays =this.scheduleService.getAllWeekDays();
    this.availableSchedules=this.scheduleService.getAllAvailableScheduleByEnvironment();
    console.log("Valor de taken profesor  ", this.takenProfessorSchedules , "  ")
    console.log("y environment" ,this.takenEnvironmentSchedules)

    // obtener todos los horarios vacios
    this.loadTableTime([1,7])

    // cruzar los horarios ocupados con los vacios y que queden solo los vacios
    // mostrar solo los horarios vacios o mostrar deshabilitado para escoger
  }


  onSelectingSchedule(schedule:Schedule,e:Event){

    const x = e.target as HTMLInputElement
    if(x.checked){
      // Seleccionaron un horario
      this.schedule=schedule;
      this.selectedSchedule.emit(schedule)
      this.isScheduleSelected=true //ya hay un curso horario seleccionado
      this.isCheckboxDisabled=true //deshabilitar que peuda seleccionar otros cursos
      this.showSelectedSchedule=true
    }
  }
  changeSelectedSchedule(){
    //TODO borrar progreso si cambia de horario volver a mostrar los horarios disponibles
    this.isCheckboxDisabled=false
    this.isScheduleSelected=false
    this.showSelectedSchedule=false
    this.checkboxes.forEach((element) => {
      element.nativeElement.checked = false;
    });
    this.selectedSchedule.emit(null)
  }
  updateTableTime(type:string,bloque:number){

    if(type == 'all' || type ==''){
      this.selectedDay=''
    }else{

      this.selectedDay=type
    }
    if(bloque == -1  ){ // se queda con el mismo bloque
      this.bloque = this.bloque
    }else{
      this.bloque = bloque
    }

    this.loadTableTime([1,7])

  }

  loadTableTime(args: number[]){

    let pageSolicitud:number = args[0];
    let pageSize: number = args[1]
      if(!pageSolicitud){
        pageSolicitud = 0;
      }
      if(!pageSize){
        pageSize=10
      }
      if(this.bloque ==0){
        this.bloque=2;
      }
      const startIndex = (pageSolicitud - 1) * pageSize;

      this.filteredSchedules.splice(0, this.filteredSchedules.length);
      // return this.filterSchedules().slice(startIndex, startIndex + this.pageSize);
      this.scheduleService.filterSchedulesPaged(
        this.availableSchedules,
        this.takenProfessorSchedules,
        this.takenEnvironmentSchedules,
        this.selectedDay,
        this.bloque,
        startIndex,
        pageSize
      ).subscribe(response =>{
        // console.log("Data en load Time: ",response)
        this.filteredSchedules.splice(0, this.filteredSchedules.length);
        this.filteredSchedules.push(...response.elements as Schedule[]);
        this.totalItems=response.paginator.totalItems as number
        this.totalNumberPage=response.paginator.totalNumberPage as number


      })
  }

}
