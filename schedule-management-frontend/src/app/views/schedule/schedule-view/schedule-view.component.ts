import { AfterViewInit, ChangeDetectorRef, Component, EventEmitter, Input, Output, SimpleChanges, ViewChild } from '@angular/core';
import { Environment } from 'src/app/models/environment.model';
import { Schedule, ScheduleColor, ScheduleDTO } from 'src/app/models/schedule.model';
import { ScheduleService } from 'src/app/services/schedule/schedule.service';
import { ScheduleRowComponent } from '../schedule-row/schedule-row.component';
import { Program } from 'src/app/models/program.model';
import { Subject } from 'src/app/models/subject.model';
import { Router } from '@angular/router';
import { Course } from 'src/app/models/course.model';
import { ScheduleBeforeCreateFormComponent } from '../schedule-before-create-form/schedule-before-create-form.component';
import Swal from 'sweetalert2';
import { FormGroup, FormBuilder } from '@angular/forms';
import { EnvironmentService } from 'src/app/services/environment/environment.service';


@Component({
  selector: 'app-schedule-view',
  templateUrl: './schedule-view.component.html',
  styleUrls: ['./schedule-view.component.scss']
})


export class ScheduleViewComponent implements AfterViewInit {

  numeroDia?: number;
  contador: number = 0;
  headers: string[] = ["hora", "lunes", "martes", "miercoles", "jueves", "viernes", "sabado"]
  weekDays = ["lunes", "martes", "miercoles", "jueves", "viernes", "sabado"]
  horariosAmbienteColor!: ScheduleColor[];
  horarioId!: number;

  horasDia = ["07:00:00", "08:00:00", "09:00:00", "10:00:00", "11:00:00", "12:00:00", "13:00:00", "14:00:00", "15:00:00", "16:00:00", "17:00:00", "18:00:00", "19:00:00", "20:00:00", "21:00:00", "22:00:00"]
  showHorario = false
  @Input('ambiente') ambiente!: Environment;
  @ViewChild('child') child!: ScheduleRowComponent;



  form!: FormGroup;
  @Output() isFormValid = new EventEmitter<boolean>;
  @Output() progress = new EventEmitter<number>()
  @Output() selectedEnvironment = new EventEmitter<Environment>();
  @Output() scheduleCreated = new EventEmitter<ScheduleDTO>();
  @Input('selectedProgram') program!: Program;
  @Input('selectedSemester') semesterr!: number;
  @Input('isEdit') isEdit!: boolean;
  //intento 
  items = [1, 2, 3, 4];
  progressMadeProgramSemester: number = 0;
  progressMadeForm: number = 0;
  sumProgress: number = 10;
  showForm: boolean = true;
  createFormIsValid = false
  programm: Program = {
    'programId': '0',
    'name': '',
    'department_id': '',
    'color':''
  };
  // @ViewChild('beforeFormAccordion',{static:true}) beforeFormAccordion !:ElementRef ;
  @ViewChild('beforeForm', { static: false }) beforeForm!: ScheduleBeforeCreateFormComponent;
  showSelectedProgramAndSemester: boolean = false;
  showScheduleView: boolean = false;
  semester: number = 0;
  course: Course = { 'courseId': 1, 'courseGroup': 'A', 'courseCapacity': 20, 'periodId': '', 'subject': {} as Subject, 'personCode': '', 'remainingHours': 0, 'typeEnvironmentRequired': '' }
  // environmentSelected!: Environment;
  scheduleToCreate!: Schedule;
  continueCreatingSchedule: boolean = false
  changeValue: boolean = true
  constructor(
    private scheduleService: ScheduleService,
    private cdr: ChangeDetectorRef,
    private router: Router,
    private environmentService: EnvironmentService,
    private formBuilder: FormBuilder,
    
  ) {
    this.initializeHorario();
  }
  ngAfterViewInit(): void {
  
    
  this.scheduleService.getTakenEnvironmentSchedule(this.ambiente.id).subscribe((response) => {
    this.horariosAmbienteColor = Object.values(response) as ScheduleColor[];
    console.log("ESTE ES EL RESPONSE DEL BOBO HPTA DE DANIEL",response);
    


      
   
    });
  }
  

  ngOnInit() {

  }

  ngOnChanges(changes: SimpleChanges): void {

  }

  getShowHorario(value: boolean) {
    this.showHorario = value
  }

  timeInRange(inicial: string, final: string, franja: string) {
    //lo va a pintar si
    //inicial es igual a la franja o ( si el final es mayor a la franja y el inicial es menor a la franja)

    if (inicial == franja || (parseInt(final) > parseInt(franja) && parseInt(inicial) < parseInt(franja))) {
      // console.log("retorna true para pintar ")
      return true
    }

    return false
  }


  horario: any = {};



  initializeHorario() {
    for (let i = 0; i < this.horasDia.length; i++) {
      this.horario[this.horasDia[i]] = {
        1: [],
        2: [],
        3: [],
        4: [],
        5: [],
        6: []
      };
    }
  }


  allowDrop(event: any) {
    event.preventDefault();
  }

  drop(event: any, day: number, hour: string, inicial: string, final: string, dia: string, environmentId: number) {
    event.preventDefault();
    const materia = event.dataTransfer.getData("application/json");
    console.log("verrrrrrrrrrrrr " + materia);
    const courseIdCaracter = (materia.split(" ")[0]);
    console.log("EL IDE DEL CURSO ES: ", courseIdCaracter);
    const courseId = parseInt(courseIdCaracter.split('"')[1]);
    console.log("EL IDE DEL CURSO ES: ", courseId);

    // Verificar si las dos franjas horarias consecutivas están vacías
    if (
      this.horario[hour][day].length >= 1 ||
      this.horario[this.getNextHour(hour)][day].length >= 1
    ) {
      // Si alguna de las dos franjas horarias ya tiene una materia, no se permite agregar más
      return;
    }

    // Obtener la siguiente hora en formato de cadena
    const nextHour = this.getNextHour(hour);

    // Obtener las franjas horarias desde hour hasta nextHour
    const hoursRange = this.getHoursRange(hour, nextHour);

    // Verificar si alguna de las franjas horarias ya tiene una materia
    if (hoursRange.some((h) => this.horario[h][day].length >= 1)) {
      // Si alguna franja horaria ya tiene una materia, no se permite agregar más
      return;
    }

    // Agregar la materia en todas las franjas horarias del rango
    hoursRange.forEach((h) => {
      this.horario[h][day].push(materia);
    });

    // Eliminar las dos franjas horarias correspondientes de la lista original
    const index = this.horasDia.indexOf(materia);
    if (index !== -1) {
      this.horasDia.splice(index, 2); // Eliminar dos elementos consecutivos

    }

    let scheduleCreated: ScheduleDTO = { id: 0, day: '', startingTime: '', endingTime: '', courseId: 0, environmentId: 0 };
    scheduleCreated.day = dia.toUpperCase()
    scheduleCreated.startingTime = hour
    scheduleCreated.endingTime = this.getNextHour(this.getNextHour(hour));
    scheduleCreated.courseId = courseId
    scheduleCreated.environmentId = environmentId
    console.log("Emitiendo schedule ", scheduleCreated)
    this.scheduleCreated.emit(scheduleCreated)
    console.log("EL ID DEL HORARIO ES", this.horarioId);
    
    setTimeout(() => {
      window.location.reload();
    }, 2000);
  }


  getNextHour(hour: string): string {
    const [hourStr] = hour.split(":");
    const hourInt = parseInt(hourStr);
    const nextHourInt = hourInt + 1; // Avanzar una hora
    const nextHourStr = nextHourInt.toString().padStart(2, "0");
    return nextHourStr + ":00:00";
  }

  getHoursRange(startHour: string, endHour: string): string[] {
    const hoursRange: string[] = [];
    let currentHour = startHour;

    while (currentHour <= endHour) {
      hoursRange.push(currentHour);
      currentHour = this.getNextHour(currentHour);
    }

    return hoursRange;
  }


  getPreviousHour(hour: string): string {
    const [hourStr] = hour.split(":");
    const hourInt = parseInt(hourStr);
    const previousHourInt = hourInt - 1; // Retroceder una hora
    const previousHourStr = previousHourInt.toString().padStart(2, "0");
    return previousHourStr + ":00:00";
  }


  removeMateria(day: number, hour: string, index: number) {
    const previousHour = this.getPreviousHour(hour);
    const nextHour = this.getNextHour(hour);
    const removedMateria = this.horario[hour][day].splice(index, 1)[0]; // Eliminar el elemento de la franja horaria actual
    let scheduleresponse: Schedule;
    // Buscar el índice del elemento correspondiente en la franja horaria siguiente
    const nextHourIndex = this.horario[nextHour][day].indexOf(removedMateria);

    if (nextHourIndex !== -1) {
      // Si se encuentra el elemento en la franja horaria siguiente, eliminarlo
      this.horario[nextHour][day].splice(nextHourIndex, 1);
      console.log("el ideee es: ", this.horarioId);
      this.scheduleService.deleteSchedule(this.horarioId).subscribe(
        response => {
          if (response != null) {
            scheduleresponse = response as Schedule;

            Swal.fire(`Franja eliminada`,
              `La franja: ${scheduleresponse.startingTime} ${scheduleresponse.endingTime}\nCurso: ${scheduleresponse.course.courseId}\nha sido eliminada exitosamente`, 'success');
            // this.router.navigate(['//schedule/detail']);
          }
        },
        err => {
          Swal.fire(`Error: ${err.message}`,
            `La franja: ${scheduleresponse.startingTime} ${scheduleresponse.endingTime}\nCurso: ${scheduleresponse.course.courseId}\n`, 'warning');
          this.router.navigate(['//schedule/detail']);
        }
      );
    }

    // Buscar el índice del elemento correspondiente en la franja horaria anterior
    const previousHourIndex = this.horario[previousHour][day].indexOf(removedMateria);

    if (previousHourIndex !== -1) {
      // Si se encuentra el elemento en la franja horaria anterior, eliminarlo
      this.horario[previousHour][day].splice(previousHourIndex, 1);


      console.log("el ideee es: ", this.horarioId);

      this.scheduleService.deleteSchedule(this.horarioId).subscribe(
        response => {
          if (response != null) {
            scheduleresponse = response as Schedule;

            Swal.fire(`Franja eliminada`,
              `La franja: ${scheduleresponse.startingTime} ${scheduleresponse.endingTime}\nCurso: ${scheduleresponse.course.courseId}\nha sido eliminada exitosamente`, 'success');
            // this.router.navigate(['//schedule/detail']);
          }
        },
        err => {
          Swal.fire(`Error: ${err.message}`,
            `La franja: ${scheduleresponse.startingTime} ${scheduleresponse.endingTime}\nCurso: ${scheduleresponse.course.courseId}\n`, 'warning');
          this.router.navigate(['//schedule/detail']);
        }
      );
    }
  }

  confirmRemoveMateria(day: number, hour: string, index: number): void {
    if (window.confirm("¿Estás seguro de que deseas eliminar este curso?")) {
      this.removeMateria(day, hour, index);
    }

  }

  confirmRemoveMateriaa(day: number, hour: string, id: number): void {
    const confirmationTitle = "Eliminar curso";
    const confirmationMessage = "¿Estás seguro de que deseas eliminar este curso?";
  
    Swal.fire({
      title: confirmationTitle,
      text: confirmationMessage,
      icon: "warning",
      showCancelButton: true,
      confirmButtonColor: "#d33",
      cancelButtonColor: "#3085d6",
      confirmButtonText: "Eliminar",
      cancelButtonText: "Cancelar",
    }).then((result) => {
      if (result.isConfirmed) {
        this.removeMateriaa(day, hour, id);
        window.location.reload();
      }
    });
  }
  
  removeMateriaa(day: number, hour: string, id: number) {
    const previousHour = this.getPreviousHour(hour);
    const nextHour = this.getNextHour(hour);

    let scheduleresponse: Schedule;
    // Buscar el índice del elemento correspondiente en la franja horaria siguiente

    console.log("el ideee es: ", id);
    this.scheduleService.deleteSchedule(id).subscribe(
      response => {
        if (response != null) {
          scheduleresponse = response as Schedule;

          Swal.fire(`Franja eliminada`,
            `La franja: ${scheduleresponse.startingTime} ${scheduleresponse.endingTime}\nCurso: ${scheduleresponse.course.courseId}\nha sido eliminada exitosamente`, 'success');
          // this.router.navigate(['//schedule/detail']);
        }
      },
      err => {
        Swal.fire(`Error: ${err.message}`,
          `La franja: ${scheduleresponse.startingTime} ${scheduleresponse.endingTime}\nCurso: ${scheduleresponse.course.courseId}\n`, 'warning');
        this.router.navigate(['//schedule/detail']);
      }
    );

  }

  getMaterias(day: number, hour: string) {
    return this.horario[hour][day];
  }

  getid(horario: number) {
    this.horarioId = horario;
    console.log("horario oooooo: ", horario);
    console.log("ver WWWWWWWWWWWWW: ", this.horarioId);
  }
  weekDayToInteger(weekDays: String[], day: string) {
    for (let i = 0; i < weekDays.length; i++) {
      if (weekDays[i] === day) {
        return i + 1;
      }
    }
    return -1;
  }
  
}