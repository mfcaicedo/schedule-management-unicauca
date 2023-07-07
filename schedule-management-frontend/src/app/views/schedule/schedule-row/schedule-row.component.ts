import { AfterViewInit, Component, EventEmitter, Input, OnInit, Output, SimpleChanges } from '@angular/core';
import { Environment } from 'src/app/models/environment.model';
import { Schedule, ScheduleColor } from 'src/app/models/schedule.model';
import { ScheduleService } from 'src/app/services/schedule/schedule.service';


@Component({
  selector: 'app-schedule-row',
  templateUrl: './schedule-row.component.html',
  styleUrls: ['./schedule-row.component.scss']
})
export class ScheduleRowComponent implements OnInit, AfterViewInit{


  numeroDia?: number;


  horariosColor:ScheduleColor[]=[];
  horariosAmbiente:Schedule[]=[]
 showHorario:boolean=false
  @Input("hora") hora:string="";
  @Input("header") header:string="";
 

  colores :{[key:number]:string;}={
    1:"bg-sky",
    2:"bg-orange",
    3:"bg-green",
    4:"bg-yellow",
    5:"bg-pink",
    6:"bg-purple",
    7:"bg-lightred"
  }
  constructor(
    private scheduleService:ScheduleService
  ){

  }


  ngOnInit(){
    //Los horarios ya vienen desde el padre y deben estar con el color

    // this.horariosColorHijo= this.horariosColor
  }
  ngAfterViewInit(): void {

  }
  callWithData(schedules: Schedule[]){
    console.log("horarios desde el padre ",schedules)
    this.horariosColor = this.scheduleService.getScheduleWithColor(schedules);
    this.showHorario=true
    console.log("horarios color ", this.horariosColor)
    this.ngOnInit()
  }

  // ngOnChanges(changes: SimpleChanges): void {
  //   if(changes['horariosColor']){
  //     this.horariosColor=changes['horariosColor'].currentValue

  //   }

  // }

  timeInRange(inicial:string, final:string,franja:string){
    //lo va a pintar si
    //inicial es igual a la franja o ( si el final es mayor a la franja y el inicial es menor a la franja)
    // console.log("Llegan a range ",inicial, " ",final, " ",franja)

    if(inicial==franja || ( parseInt(final)>parseInt(franja) && parseInt(inicial)<parseInt(franja)) ){
      // console.log("retorna true para pintar ")
      return true
    }
    // console.log("no pasan")
    return false
  }



}
