import { AfterViewInit, ChangeDetectorRef, Component, Input, SimpleChanges, ViewChild } from '@angular/core';
import { Environment } from 'src/app/models/environment.model';
import { Schedule, ScheduleColor } from 'src/app/models/schedule.model';
import { ScheduleService } from 'src/app/services/schedule/schedule.service';
import { ScheduleRowComponent } from '../schedule-row/schedule-row.component';

@Component({
  selector: 'app-schedule-view',
  templateUrl: './schedule-view.component.html',
  styleUrls: ['./schedule-view.component.scss']
})
export class ScheduleViewComponent implements AfterViewInit {

  numeroDia?: number;
  contador: number = 0;
  headers:string[]=["hora","lunes","martes","miercoles","jueves","viernes","sabado"]
  weekDays=["lunes","martes","miercoles","jueves","viernes","sabado"]
  horariosAmbienteColor!:ScheduleColor[];
  // horariosAmbienteColor:ScheduleColor[]=[];
  horasDia=["07:00:00","08:00:00","09:00:00","10:00:00","11:00:00","12:00:00","13:00:00","14:00:00","15:00:00","16:00:00","17:00:00","18:00:00","19:00:00","20:00:00","21:00:00","22:00:00"]
  showHorario=false
  @Input('ambiente') ambiente!:Environment;
  @ViewChild('child') child!: ScheduleRowComponent;


  constructor(
    private scheduleService:ScheduleService,
    private cdr: ChangeDetectorRef
  ){

  }
  ngAfterViewInit(): void {

    this.scheduleService.getTakenEnvironmentSchedule(this.ambiente.id).subscribe((response) =>{
      // console.log("Responseee ",response)
      this.horariosAmbienteColor = response as ScheduleColor[]
      // this.callWithData(this.horariosAmbiente)

    });
  }


  ngOnInit(){



  }
  // callWithData(schedules: Schedule[]){
  //   // console.log("horarios desde el padre ",schedules)
  //   this.horariosColor = this.scheduleService.getScheduleWithColor(schedules);
  //   this.showHorario=true
  //   // console.log("horarios color ", this.horariosColor)
  //   this.cdr.detectChanges();

  // }

  ngOnChanges(changes: SimpleChanges): void {


  }
  getShowHorario(value:boolean){
    this.showHorario=value
  }

  timeInRange(inicial:string, final:string,franja:string){
    //lo va a pintar si
    //inicial es igual a la franja o ( si el final es mayor a la franja y el inicial es menor a la franja)

    if(inicial==franja || ( parseInt(final)>parseInt(franja) && parseInt(inicial)<parseInt(franja)) ){
      // console.log("retorna true para pintar ")
      return true
    }
    // console.log("no pasan")
    return false
  }





}
