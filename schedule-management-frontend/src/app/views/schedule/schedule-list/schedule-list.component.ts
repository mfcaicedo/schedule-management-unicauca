import { Component, ElementRef, EventEmitter, Input, Output, QueryList, ViewChildren } from '@angular/core';
import { Schedule } from 'src/app/models/schedule.model';
import { ScheduleService } from 'src/app/services/schedule/schedule.service';

@Component({
  selector: 'app-schedule-list',
  templateUrl: './schedule-list.component.html',
  styleUrls: ['./schedule-list.component.scss']
})
export class ScheduleListComponent {
  columns:string[]=["Id","Dia","Hora Inicio","Hora Fin","Curso Id", "Seleccionado"]
  weekDays:string[]=["all","Lunes","Martes","Miercoles","Jueves","Viernes","Sabado"]
  showSelectedSchedule:boolean=false;
  isCheckboxDisabled:boolean=false;
  isScheduleSelected:boolean=false;
  schedule!:Schedule;
  selectedDay: string ='';

  totalItems:number=0;
  totalNumberPage:number=1;
  paginadorEnvironment: any;
  pageSize:number=0;

  @Output() selectedSchedule = new EventEmitter<Schedule| null>();
  @Input('takenSchedules') takenSchedules: Schedule[]=[]
  @ViewChildren("checkboxes") checkboxes!: QueryList<ElementRef>;

  constructor(
    private scheduleService:ScheduleService
  ){

  }
  changeSelectedSchedule(){

    this.isCheckboxDisabled=false
    this.isScheduleSelected=false
    this.showSelectedSchedule=false
    this.checkboxes.forEach((element) => {
      element.nativeElement.checked = false;
    });
    // this.selectedSchedule.emit(null)
  }
  updateTableTime(type:string){

    if(type == 'all'.toUpperCase() || type ==''){
      this.selectedDay=''
    }else{

      this.selectedDay=type
    }
    this.loadTableTime([1,7])

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

  loadTableTime(args: number[]){

    let pageSolicitud:number = args[0];
    let pageSize: number = args[1]
      if(!pageSolicitud){
        pageSolicitud = 0;
      }
      if(!pageSize){
        pageSize=10
      }
      const startIndex = (pageSolicitud - 1) * pageSize;

      this.takenSchedules.splice(0, this.takenSchedules.length);

      this.scheduleService.takenSchedulesPaged(
        this. takenSchedules,
        this.selectedDay,
        startIndex,
        pageSize
      ).subscribe(response =>{
        // console.log("Data en load Time: ",response)
        this.takenSchedules.splice(0, this.takenSchedules.length);
        this.takenSchedules.push(...response.elements as Schedule[]);
        this.totalItems=response.paginator.totalItems as number
        this.totalNumberPage=response.paginator.totalNumberPage as number


      })
  }

}
