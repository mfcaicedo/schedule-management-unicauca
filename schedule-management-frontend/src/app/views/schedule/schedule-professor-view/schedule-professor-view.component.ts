import { NonNullAssert } from '@angular/compiler';
import { ChangeDetectorRef, Component, ElementRef, EventEmitter, Input, OnInit, Output, QueryList, Renderer2, SimpleChanges, ViewChildren } from '@angular/core';
import { Professor } from 'src/app/models/professor.model';
import { ActivatedRoute } from '@angular/router';
import { Schedule, ScheduleColor } from 'src/app/models/schedule.model';
import {ScheduleProfessorService} from 'src/app/services/schedule-professor/schedule-professor.service'
import { ScheduleService } from 'src/app/services/schedule/schedule.service';


@Component({
  selector: 'app-schedule-professor-view',
  templateUrl: './schedule-professor-view.component.html',
  styleUrls: ['./schedule-professor-view.component.scss']
})
export class ScheduleProfessorViewComponent {


//   numeroDia?: number;
//   contador: number = 0;
//   headers:string[]=["hora","lunes","martes","miercoles","jueves","viernes","sabado"]
//   weekDays=["lunes","martes","miercoles","jueves","viernes","sabado"]
//   horariosAmbiente!:Schedule[];
//   horariosAmbienteColor!:ScheduleColor[];
//   horasDia=["07:00","08:00","09:00","10:00","11:00","12:00","13:00","14:00","15:00","16:00","17:00","18:00","19:00","20:00","21:00","22:00"]
//   colores :{[key:number]:string;}={
//     1:"bg-sky",
//     2:"bg-orange",
//     3:"bg-green",
//     4:"bg-yellow",
//     5:"bg-pink",
//     6:"bg-purple",
//     // 7:"bg-lightred"
//   }
//   iteradorColores:number=1;
//   isDisabled:boolean = false;
//   @ViewChildren("checkboxes") checkboxes!: QueryList<ElementRef>;
//   @Output()selectdProfessor = new EventEmitter<Professor|null>();


//   @Input()profesor!:Professor;
//   constructor(
//     private professorService: ScheduleProfessorService
//   ){}
//   ngOnInit(): void{}

//   changeSelectedProfessor(){
//     this.isDisabled=false
//     // this.render2.setAttribute(this.casilla.nativeElement,'checked','false')
//     this.checkboxes.forEach((element) => {
//       element.nativeElement.checked = false;
//     });
//     this.selectdProfessor.emit(null)
//   }

// }
// function ngOnInit() {
//   throw new Error('Function not implemented.');

numeroDia?: number;
  contador: number = 0;
  headers:string[]=["hora","lunes","martes","miercoles","jueves","viernes","sabado"]
  weekDays=["lunes","martes","miercoles","jueves","viernes","sabado"]
  horariosProfesorColor!:ScheduleColor[];

  horasDia=["07:00:00","08:00:00","09:00:00","10:00:00","11:00:00","12:00:00","13:00:00","14:00:00","15:00:00","16:00:00","17:00:00","18:00:00","19:00:00","20:00:00","21:00:00","22:00:00"]
  showHorario=false
  @Input('profesor') profesor!:Professor;
  // @ViewChild('child') child!: ScheduleRowComponent;

  horariosColor:ScheduleColor[]=[];
  constructor(
    private professorService: ScheduleProfessorService,
    private scheduleService: ScheduleService,
    private cdr: ChangeDetectorRef
  ){

  }
  ngAfterViewInit(): void {
    // this.scheduleService.getTakenEnvironmentSchedule(this.ambiente.id).subscribe((response) =>{
    //   console.log("Responseee ",response)
    //   this.horariosAmbiente = response as Schedule[]
    //   this.child.callWithData(this.horariosAmbiente)

    // });
    this.scheduleService.getTakenProfessorSchedule(this.profesor.personCode).subscribe((response) =>{
      console.log("Responseee ",response)
      this.horariosProfesorColor = response as ScheduleColor[]
      //this.callWithData(this.horariosAmbiente)

    });
  }

  //llamar al servicio para traer los horarios ocupados de ese ambiente
    // this.horariosAmbiente = this.scheduleService.getAllScheduleFromEnvironment();
  ngOnInit(){



  }


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

