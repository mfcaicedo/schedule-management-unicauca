import { Injectable } from '@angular/core';
import { Course } from 'src/app/models/course.model';
import { Environment } from 'src/app/models/environment.model';
import { Period } from 'src/app/models/period.model';
import { Schedule, ScheduleColor, ScheduleDTO } from 'src/app/models/schedule.model';
import { Program } from 'src/app/models/program.model';
import { Person } from 'src/app/models/person.model';
import { Subject } from 'src/app/models/subject.model';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { catchError, map, Observable, of, scheduled, throwError } from 'rxjs';
import { ResponseData } from 'src/app/models/responseData.model'
import { schedule } from 'src/schedule/schedule';
import Swal from 'sweetalert2'
import { ProgramService } from '../program/program.service';
import { CourseService } from '../course/course.service';
import { Color } from 'chart.js';
import { ReturnStatement } from '@angular/compiler';
@Injectable({
  providedIn: 'root'
})
export class ScheduleService {



  programs!: ProgramService;
  color!: string;
  subjetc1!: Subject;
  period: Period = {
    'periodId': '2022.2', 'state': 'true', endDate: "2023-07-15T00:00:00.000+0000",
    initDate: "2023-07-15T00:00:00.000+0000"
  }
  program: Program = { programId: 'PIS', name: 'INGENIERIA DE SISTEMAS', department_id: '1', color: 'bg-orange' }
  subject: Subject = { 'subjectCode': '1', 'name': 'Programacion orientada a objetos', 'weeklyOverload': 6, 'timeBlock': true, 'semester': 2, 'programId': this.program }
  person: Person = { 'personCode': '104618021314', 'fullName': 'PPC', 'personType': 'TEACHER', 'department': { 'departmentId': '1', 'departmentName': 'Ingenieria de sistemas' } }

  curso: Course = {
    'courseId': 1, 'courseGroup': 'A', 'courseCapacity': 20, 'periodId': this.period.periodId,
    'subject': this.subject, 'personCode': this.person.personCode, 'remainingHours': 2, 'typeEnvironmentRequired': 'asd'
  }

  courseprueba!: CourseService;
  course!: Course;
  envi!: Environment;
  schedule: Schedule[] = [
    { id: 1, day: "LUNES", startingTime: '07:00:00', endingTime: '09:00:00', course: this.curso, environment: this.envi },
    { id: 2, day: "LUNES", startingTime: '09:00:00', endingTime: '11:00:00', course: this.curso, environment: this.envi },
    { id: 3, day: "LUNES", startingTime: '11:00:00', endingTime: '13:00:00', course: this.curso, environment: this.envi },
    { id: 4, day: "LUNES", startingTime: '14:00:00', endingTime: '16:00:00', course: this.curso, environment: this.envi },
    { id: 5, day: "LUNES", startingTime: '16:00:00', endingTime: '18:00:00', course: this.curso, environment: this.envi },
    { id: 6, day: "LUNES", startingTime: '18:00:00', endingTime: '20:00:00', course: this.curso, environment: this.envi },
    { id: 7, day: "LUNES", startingTime: '20:00:00', endingTime: '22:00:00', course: this.curso, environment: this.envi },

    { id: 8, day: "MARTES", startingTime: '07:00:00', endingTime: '09:00:00', course: this.curso, environment: this.envi },
    { id: 9, day: "MARTES", startingTime: '09:00:00', endingTime: '11:00:00', course: this.curso, environment: this.envi },
    { id: 10, day: "MARTES", startingTime: '11:00:00', endingTime: '13:00:00', course: this.curso, environment: this.envi },
    { id: 11, day: "MARTES", startingTime: '14:00:00', endingTime: '16:00:00', course: this.curso, environment: this.envi },
    { id: 12, day: "MARTES", startingTime: '16:00:00', endingTime: '18:00:00', course: this.curso, environment: this.envi },
    { id: 13, day: "MARTES", startingTime: '18:00:00', endingTime: '20:00:00', course: this.curso, environment: this.envi },
    { id: 14, day: "MARTES", startingTime: '20:00:00', endingTime: '22:00:00', course: this.curso, environment: this.envi },

    { id: 15, day: "MIERCOLES", startingTime: '07:00:00', endingTime: '09:00:00', course: this.curso, environment: this.envi },
    { id: 16, day: "MIERCOLES", startingTime: '09:00:00', endingTime: '11:00:00', course: this.curso, environment: this.envi },
    { id: 17, day: "MIERCOLES", startingTime: '11:00:00', endingTime: '13:00:00', course: this.curso, environment: this.envi },
    { id: 18, day: "MIERCOLES", startingTime: '14:00:00', endingTime: '16:00:00', course: this.curso, environment: this.envi },
    { id: 19, day: "MIERCOLES", startingTime: '16:00:00', endingTime: '18:00:00', course: this.curso, environment: this.envi },
    { id: 20, day: "MIERCOLES", startingTime: '18:00:00', endingTime: '20:00:00', course: this.curso, environment: this.envi },
    { id: 21, day: "MIERCOLES", startingTime: '20:00:00', endingTime: '22:00:00', course: this.curso, environment: this.envi },

    { id: 22, day: "JUEVES", startingTime: '07:00:00', endingTime: '09:00:00', course: this.curso, environment: this.envi },
    { id: 23, day: "JUEVES", startingTime: '09:00:00', endingTime: '11:00:00', course: this.curso, environment: this.envi },
    { id: 24, day: "JUEVES", startingTime: '11:00:00', endingTime: '13:00:00', course: this.curso, environment: this.envi },
    { id: 25, day: "JUEVES", startingTime: '14:00:00', endingTime: '16:00:00', course: this.curso, environment: this.envi },
    { id: 26, day: "JUEVES", startingTime: '16:00:00', endingTime: '18:00:00', course: this.curso, environment: this.envi },
    { id: 27, day: "JUEVES", startingTime: '18:00:00', endingTime: '20:00:00', course: this.curso, environment: this.envi },
    { id: 28, day: "JUEVES", startingTime: '20:00:00', endingTime: '22:00:00', course: this.curso, environment: this.envi },

    { id: 29, day: "VIERNES", startingTime: '07:00:00', endingTime: '09:00:00', course: this.curso, environment: this.envi },
    { id: 30, day: "VIERNES", startingTime: '09:00:00', endingTime: '11:00:00', course: this.curso, environment: this.envi },
    { id: 31, day: "VIERNES", startingTime: '11:00:00', endingTime: '13:00:00', course: this.curso, environment: this.envi },
    { id: 32, day: "VIERNES", startingTime: '14:00:00', endingTime: '16:00:00', course: this.curso, environment: this.envi },
    { id: 33, day: "VIERNES", startingTime: '16:00:00', endingTime: '18:00:00', course: this.curso, environment: this.envi },
    { id: 34, day: "VIERNES", startingTime: '18:00:00', endingTime: '20:00:00', course: this.curso, environment: this.envi },
    { id: 35, day: "VIERNES", startingTime: '20:00:00', endingTime: '22:00:00', course: this.curso, environment: this.envi },

    { id: 36, day: "SABADO", startingTime: '07:00:00', endingTime: '09:00:00', course: this.curso, environment: this.envi },
    { id: 37, day: "SABADO", startingTime: '09:00:00', endingTime: '11:00:00', course: this.curso, environment: this.envi },
    { id: 38, day: "SABADO", startingTime: '11:00:00', endingTime: '13:00:00', course: this.curso, environment: this.envi },
    { id: 39, day: "SABADO", startingTime: '14:00:00', endingTime: '16:00:00', course: this.curso, environment: this.envi },
    { id: 40, day: "SABADO", startingTime: '16:00:00', endingTime: '18:00:00', course: this.curso, environment: this.envi },
    { id: 41, day: "SABADO", startingTime: '18:00:00', endingTime: '20:00:00', course: this.curso, environment: this.envi },
    { id: 42, day: "SABADO", startingTime: '20:00:00', endingTime: '22:00:00', course: this.curso, environment: this.envi },

    { id: 43, day: "LUNES", startingTime: '08:00:00', endingTime: '12:00:00', course: this.curso, environment: this.envi },
    { id: 44, day: "LUNES", startingTime: '12:00:00', endingTime: '16:00:00', course: this.curso, environment: this.envi },
    { id: 45, day: "LUNES", startingTime: '16:00:00', endingTime: '20:00:00', course: this.curso, environment: this.envi },
    { id: 46, day: "LUNES", startingTime: '18:00:00', endingTime: '22:00:00', course: this.curso, environment: this.envi },


    { id: 47, day: "MARTES", startingTime: '08:00:00', endingTime: '12:00:00', course: this.curso, environment: this.envi },
    { id: 48, day: "MARTES", startingTime: '12:00:00', endingTime: '16:00:00', course: this.curso, environment: this.envi },
    { id: 49, day: "MARTES", startingTime: '16:00:00', endingTime: '20:00:00', course: this.curso, environment: this.envi },
    { id: 50, day: "MARTES", startingTime: '18:00:00', endingTime: '22:00:00', course: this.curso, environment: this.envi },


    { id: 51, day: "MIERCOLES", startingTime: '08:00:00', endingTime: '12:00:00', course: this.curso, environment: this.envi },
    { id: 52, day: "MIERCOLES", startingTime: '12:00:00', endingTime: '16:00:00', course: this.curso, environment: this.envi },
    { id: 53, day: "MIERCOLES", startingTime: '16:00:00', endingTime: '20:00:00', course: this.curso, environment: this.envi },
    { id: 54, day: "MIERCOLES", startingTime: '18:00:00', endingTime: '22:00:00', course: this.curso, environment: this.envi },


    { id: 55, day: "JUEVES", startingTime: '08:00:00', endingTime: '12:00:00', course: this.curso, environment: this.envi },
    { id: 56, day: "JUEVES", startingTime: '12:00:00', endingTime: '16:00:00', course: this.curso, environment: this.envi },
    { id: 57, day: "JUEVES", startingTime: '16:00:00', endingTime: '20:00:00', course: this.curso, environment: this.envi },
    { id: 58, day: "JUEVES", startingTime: '18:00:00', endingTime: '22:00:00', course: this.curso, environment: this.envi },


    { id: 59, day: "VIERNES", startingTime: '08:00:00', endingTime: '12:00:00', course: this.curso, environment: this.envi },
    { id: 60, day: "VIERNES", startingTime: '12:00:00', endingTime: '16:00:00', course: this.curso, environment: this.envi },
    { id: 61, day: "VIERNES", startingTime: '16:00:00', endingTime: '20:00:00', course: this.curso, environment: this.envi },
    { id: 62, day: "VIERNES", startingTime: '18:00:00', endingTime: '22:00:00', course: this.curso, environment: this.envi },


    { id: 63, day: "SABADO", startingTime: '08:00:00', endingTime: '12:00:00', course: this.curso, environment: this.envi },
    { id: 64, day: "SABADO", startingTime: '12:00:00', endingTime: '16:00:00', course: this.curso, environment: this.envi },
    { id: 65, day: "SABADO", startingTime: '16:00:00', endingTime: '20:00:00', course: this.curso, environment: this.envi },
    { id: 66, day: "SABADO", startingTime: '18:00:00', endingTime: '22:00:00', course: this.curso, environment: this.envi },

  ]
  iteradorColores: number = 0
  colores: { [key: number]: string; } = {


    1: "bg-sky",
    2: "bg-orange",
    3: "bg-green",
    4: "bg-yellow",
    5: "bg-pink",
    6: "bg-purple",
    // 7:"bg-lightred"
  }

  continueCreatingScheduleForCourse: boolean = false;
  // endPoint: String = 'api/schedule'
  endPoint: string = schedule.urlSch;///se crea otro endPoint por que el de arriba esta retornando http://localhost:4200/ y no conecta
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
      Authorization: 'my-auth-token'
    })
  };
  constructor(private courseService: CourseService,
    private http: HttpClient
  ) { }
  getAllAvailableScheduleByEnvironment() {
    // Consultar todos los horarios disponibles de este ambiente (donde course es null) p
    return this.schedule;
  }

  getAllScheduleFromEnvironment() {
    //consultar todos los horarios ya ocupados de un ambiente
    return this.schedule;
  }

  getTakenProfessorSchedule(personCode: string) {
    //TODO consumir servicio para obtener el horario ocupado del profesor
    // http://localhost:8081/api/schedule/byPerson?personCode=1

    return this.http.get<any>(this.endPoint + `/byPerson?personCode=${personCode}`, this.httpOptions)
      .pipe(
        catchError((e) => {

          console.log('Error obteniendo  horario ocupado del profesor ', e.error.mensaje, 'error');
          return throwError(e);

        })
      )

  }
  getColorToSchedule(courseId: number) {

    let courseColor: Course

    console.log("ENTRAAAAAAAAAAAAAAAA", this.courseprueba)
    return this.courseService.getCourseById(courseId).subscribe((response) => {
      // console.log("Response de takenprofesor" ,response)
      courseColor = response as Course
      console.log(courseColor)

      this.color = courseColor.subject.programId.color;

      console.log("ESTE ES EL COLOOOOR ", this.color)


    });



  }



  getTakenEnvironmentSchedule(environmentId: number) {
    //TODO consumir servicio para obtener el horario ocupado del ambiente


    return this.http.get<any>(this.endPoint + `/byEnvironmentId/${environmentId}`, this.httpOptions)
      .pipe(
        map((response: any) => {


          const scheduleColors: ScheduleColor[] = Object.values(response.data).map((item: any) => {
            const courseId: number = item.course.courseId;
            console.log('AQUI DEBE IR EL ID DEL CURSO: ' + courseId)
            this.getColorToSchedule(courseId)
            console.log("PRUEBAAAAAAAAAAAAAAAAAA", this.getColorToSchedule(courseId))


            const scheduleColor: ScheduleColor = {
              id: item.id,
              day: item.day,
              startingTime: item.startingTime,
              endingTime: item.endingTime,
              color: this.color,
              course: item.course, // Asigna el valor correspondiente a 'course'
              environment: item.environment // Asigna el valor correspondiente a 'environment'

            };


            return scheduleColor;
          });
          return scheduleColors;
        })

      );
  }

  fillTakenProfessorSchedule(personCode: string): Observable<Schedule[]> {
    let takenProfessorSchedules: Schedule[] = []
    this.getTakenProfessorSchedule(personCode).subscribe((response) => {
      // console.log("Response de takenprofesor" ,response)
      takenProfessorSchedules = response as Schedule[]


    });
    return of(takenProfessorSchedules)
  }
  fillTakenEnvironmentSchedule(environmentId: number): Observable<Schedule[]> {
    let takenEnvironmentSchedules: Schedule[] = []
    this.getTakenEnvironmentSchedule(environmentId).subscribe((response) => {

      takenEnvironmentSchedules = Object.values(response) as Schedule[]

    });
    return of(takenEnvironmentSchedules)
  }

  saveSchedule(schedule: ScheduleDTO) {
    return this.http.post<any>(this.endPoint + '', schedule, this.httpOptions)
      .pipe(
        catchError((e) => {

          console.log('Error obteniendo  guardando schedule ', e, 'error');
          Swal.fire(`No se pudo crear la franja  ${schedule.startingTime} ${schedule.endingTime}`,
            `Error: ${e.error.message} \n`, 'warning');
          return throwError(e);

          // this.router.navigate(['//schedule/detail']);
        })
      )
  }
  updateSchedule(schedule: ScheduleDTO) {
    // http://localhost:8081/api/schedule?scheduleId=1
    console.log("Le llega schedule a update ", schedule)
    return this.http.put<any>(this.endPoint + `?scheduleId=${schedule.id}` + '', schedule)
      .pipe(
        catchError((e) => {

          console.log('Error obteniendo  actualizando shcedule ', e, 'error');
          Swal.fire(`No se pudo crear la franja ${schedule.startingTime} ${schedule.endingTime}`,
            `Error: ${e.error.message} `, 'warning');

          return throwError(e);

          // this.router.navigate(['//schedule/detail']);

        })
      )
  }
  deleteSchedule(scheduleId: number) {
    // http://localhost:8081/api/schedule?scheduleId=1
    return this.http.delete<any>(this.endPoint + `?scheduleId=${scheduleId}`)
      .pipe(
        catchError((e) => {

          console.log('Error eliminando shcedule ', e.error.mensaje, 'error');
          return of(e);

        })
      )
  }

  getScheduleWithColor(schedules: Schedule[]): ScheduleColor[] {


    return this.fillColorSchedule(schedules);
  }
  fillColorSchedule(horariosAmbiente: Schedule[]) {


    let horariosColor = horariosAmbiente.map((x) => { return { ...x, color: "" } })

    horariosColor.forEach(x => x.color = this.choseRandomColor())

    // console.log("colores de horario ",horariosColor)
    return horariosColor
  }

  choseRandomColor() {


    let randomColorValue: string = this.colores[this.iteradorColores];
    if (this.iteradorColores < 4) {
      this.iteradorColores += 1
    } else {
      this.iteradorColores = 1
    }
    return randomColorValue
  }

  filterSchedulesPaged(
    availableSchedules: Schedule[],
    takenProfessorSchedules: Schedule[],
    takenEnvironmentSchedules: Schedule[],
    selectedDay: string,
    bloque: number,
    startIndex: number,
    pageSize: number

  ): Observable<ResponseData> {


    let filteredSchedules = availableSchedules.filter(schedule => {
      // check if the schedule overlaps with any schedules in the takenProfessorSchedules array
      const professorOverlap = takenProfessorSchedules.some(pSchedule => {
        return schedule.day === pSchedule.day &&
          (schedule.startingTime >= pSchedule.startingTime && schedule.startingTime < pSchedule.endingTime ||
            schedule.endingTime > pSchedule.startingTime && schedule.endingTime <= pSchedule.endingTime);
      });

      // check if the schedule overlaps with any schedules in the takenEnvironmentSchedules array
      const environmentOverlap = takenEnvironmentSchedules.some(eSchedule => {
        return schedule.day === eSchedule.day &&
          (schedule.startingTime >= eSchedule.startingTime && schedule.startingTime < eSchedule.endingTime ||
            schedule.endingTime > eSchedule.startingTime && schedule.endingTime <= eSchedule.endingTime);
      });

      // only return the schedule if it doesn't overlap with either the professor or environment schedules
      return !professorOverlap && !environmentOverlap;
    });
    // filtrar los disponibles por dia si es que lo enviaron
    if (selectedDay != '') {
      filteredSchedules = filteredSchedules.filter(x => x.day == selectedDay)
    }
    if (bloque) {
      filteredSchedules = filteredSchedules.filter(x => {
        const startingTime = new Date('1970-01-01T' + x.startingTime + 'Z');
        const endingTime = new Date('1970-01-01T' + x.endingTime + 'Z');
        const timeInMillisecondsStart = startingTime.getTime();
        const timeInMillisecondsEnd = endingTime.getTime();
        const timeInHoursStart = timeInMillisecondsStart / 1000 / 60 / 60;
        const timeInHoursEnd = timeInMillisecondsEnd / 1000 / 60 / 60;
        const difference = timeInHoursEnd - timeInHoursStart;
        // const differenceInMilliseconds = difference;
        // const differenceInHours = differenceInMilliseconds / 1000 / 60 / 60;
        // console.log("DIFERENCIA : ",difference)
        return difference == bloque;
      })

    }
    let numberPages = Math.ceil(filteredSchedules.length / pageSize)
    // console.log("Elementos filtrados es ",filteredSchedules)
    // console.log("Total items es ",filteredSchedules.length, "  y number page es ", numberPages)

    let response: ResponseData = {
      elements: filteredSchedules.slice(startIndex, startIndex + pageSize),
      paginator: {
        totalItems: filteredSchedules.length,
        totalNumberPage: numberPages
      }
    }
    return of(response)
  }
  takenSchedulesPaged(
    takenSchedules: Schedule[],
    selectedDay: string,
    startIndex: number,
    pageSize: number
  ) {
    if (selectedDay != '') {
      takenSchedules = takenSchedules.filter(x => x.day == selectedDay)
    }
    let numberPages = Math.ceil(takenSchedules.length / pageSize)
    // console.log("Elementos filtrados es ",filteredSchedules)
    // console.log("Total items es ",filteredSchedules.length, "  y number page es ", numberPages)

    let response: ResponseData = {
      elements: takenSchedules.slice(startIndex, startIndex + pageSize),
      paginator: {
        totalItems: takenSchedules.length,
        totalNumberPage: numberPages
      }
    }
    return of(response)
  }
  getScheduleById(scheduleId: number) {
    return this.http.get<any>(this.endPoint + `/${scheduleId}`, this.httpOptions)
      .pipe(
        catchError((e) => {

          console.log('Error obteniendo  el schedule', e.error.mensaje, 'error');
          return throwError(e);

        })
      )
  }
  mapScheduleToDTO(schedule: Schedule): ScheduleDTO {
    return Object.assign({}, schedule, {
      courseId: schedule.course.courseId,
      environmentId: schedule.environment.id
    });
  }

  updateContinueCreatingForCourse(value: boolean) {
    this.continueCreatingScheduleForCourse = value;
  }
  getValueContinueCreatingForCourse() {
    return this.continueCreatingScheduleForCourse;
  }



  getEmptySchedule() {
    return {
      id: 0,
      day: '',
      startingTime: '',
      endingTime: '',
      course: { 'courseId': 1, 'courseGroup': 'A', 'courseCapacity': 20, 'periodId': '', 'subject': {} as Subject, 'personCode': '', 'remainingHours': 0, 'typeEnvironmentRequired': '' },
      environment: {
        id: 0,
        name: '',
        location: '',
        capacity: 0,
        environmentType: '',
        facultyId: '',
        availableResources: []
      },

    }
  }
  //-------------------------------------------------------------/
  /**
   * recupera el horario de un ambiente solicitado con el codigo y los lleva en un arreglo de tipo
   * Schedule
   * @param idEnviroment  id del ambiente que buscamos
   * @returns  lista de esquemas para el horario
   */
  getScheduleByEnviroment(idEnviroment: string): Observable<Schedule[]> {
    //alert("LA CADENA:"+(this.endPoint+"/byTypeAndParentId/"+tipoAmbiente+"/"+idEdificio));
    return this.http.get<any>(this.endPoint + "/byEnvironmentId/" + idEnviroment).pipe(
      map((response: any) => response.data), // Proporcionar un tipo explícito para 'response'
      catchError((e) => {
        console.log('Error obteniendo los Edificios de una Fac', e.error.mensaje, 'error');
        return throwError(e);
      })
    );
  }
}
