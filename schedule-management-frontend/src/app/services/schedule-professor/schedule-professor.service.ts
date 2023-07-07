import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Professor } from 'src/app/models/professor.model';
import { Schedule } from 'src/app/models/schedule.model';
import { Course } from 'src/app/models/course.model';
import { Period } from 'src/app/models/period.model';
import { Person } from 'src/app/models/person.model';
import { Subject } from 'src/app/models/subject.model';
import { Environment } from 'src/app/models/environment.model';
import { Program } from 'src/app/models/program.model';
import { catchError, Observable, throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ScheduleProfessorService {

  // period:Period={'periodId':'2022.2','state':'true'}
  // program:Program={program_id:'PIS','name':'Ingenieria de sistemas','department_id':''}
  // subject:Subject={'subjectCode':'1','name':'Programacion orientada a objetos','weeklyOverload':6,'timeBlock':true,'semester':2,'program':this.program}
  // person:Person={'personCode':'104618021314','fullName':'PPC','department':[]}
  // curso:Course={'courseId':1,'courseGroup':'A','courseCapacity':20,'periodId':this.period.periodId,'subjectCode':this.subject.subjectCode,'personCode':this.person.personCode}
  // course!: Course;
  // envi!:Environment;
  // schedule:Schedule[]=[
  //   {id:1,day:"martes",startingTime:'07:00',endingTime:'9:00',course:this.curso,environment:this.envi}
  // ]

  endPoint:String = 'api/person'

  constructor(
    private http : HttpClient
  ) { }

  getAvailableScheduleByProfessor(){
    // return this.schedule;
  }

  getAllProfessorsPage(page:number, pageSize:number):Observable<any>{
    return this.http.get<any>(this.endPoint+`?page=${page-1}&size=${pageSize}&sort=personCode&order=ASC`).pipe(
      catchError((e) => {
        // this.router.navigate(['/documentos']);

        console.log('Error obteniendo todos los ProfesoressasAS', e.error.mensaje, 'error');
        return throwError(e);

      })
    );
  }


}
