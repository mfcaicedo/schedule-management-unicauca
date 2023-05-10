import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, throwError } from 'rxjs';
import { Course } from 'src/app/models/course.model';
import { Period } from 'src/app/models/period.model';
import { Program } from 'src/app/models/program.model';
import { Subject } from 'src/app/models/subject.model';
import { Person } from 'src/app/models/person.model';
import { environment } from 'src/environments/environment';
@Injectable({
  providedIn: 'root'
})
export class CourseService {

  period:Period={'periodId':'2022.2','state':'true'}
  program:Program={programId:'PIS',name:'INGENIERIA DE SISTEMAS',department_id:'1'}
  subject:Subject={'subjectCode':'1','name':'Programacion orientada a objetos','weeklyOverload':6,'timeBlock':true,'semester':2,'program':this.program}
  person:Person={'personCode':'104618021314','fullName':'PPC','department':[]}
  // courses:Course[]=[
  //   {'courseId':1,'courseGroup':'A','courseCapacity':20,'periodId':this.period.periodId,'subjectCode':this.subject.subjectCode,'personCode':this.person.personCode},
  //   {'courseId':1,'courseGroup':'A','courseCapacity':20,'periodId':this.period.periodId,'subjectCode':this.subject.subjectCode,'personCode':this.person.personCode},
  //   {'courseId':1,'courseGroup':'A','courseCapacity':20,'periodId':this.period.periodId,'subjectCode':this.subject.subjectCode,'personCode':this.person.personCode},
  //   {'courseId':1,'courseGroup':'A','courseCapacity':20,'periodId':this.period.periodId,'subjectCode':this.subject.subjectCode,'personCode':this.person.personCode},
  //   {'courseId':1,'courseGroup':'A','courseCapacity':20,'periodId':this.period.periodId,'subjectCode':this.subject.subjectCode,'personCode':this.person.personCode},
  //   {'courseId':1,'courseGroup':'A','courseCapacity':20,'periodId':this.period.periodId,'subjectCode':this.subject.subjectCode,'personCode':this.person.personCode},
  //   {'courseId':1,'courseGroup':'A','courseCapacity':20,'periodId':this.period.periodId,'subjectCode':this.subject.subjectCode,'personCode':this.person.personCode},
  //   {'courseId':1,'courseGroup':'A','courseCapacity':20,'periodId':this.period.periodId,'subjectCode':this.subject.subjectCode,'personCode':this.person.personCode},
  //   {'courseId':1,'courseGroup':'A','courseCapacity':20,'periodId':this.period.periodId,'subjectCode':this.subject.subjectCode,'personCode':this.person.personCode},
  //   {'courseId':1,'courseGroup':'A','courseCapacity':20,'periodId':this.period.periodId,'subjectCode':this.subject.subjectCode,'personCode':this.person.personCode},

  // ]

  endPoint:String = environment.urlCrs
  // endPoint:String = 'api/course'
  constructor(
    private http : HttpClient

  ) {

  }
  getAllCoursesFromProgramAndSemester(programId:string,semester:number){

    //consumir servicio que develva todos los cursos del semestre y programa seleccionados
    // return this.courses
  }

  getAllCoursesFromProgramAndSemesterPage(page:number, pageSize:number,programId:string,semester:number){
    // http://localhost:8081/api/course/byProgramSemester?programId=PIS&semester=1&page=0&size=10&sort=id&order=asc
    // return this.http.get<any>(this.endPoint+`/byProgramSemester`+`?programId=${programId}&semester=${semester}`+`&page=${page-1}&size=${pageSize}&sort=id&order=ASC`).pipe(
    return this.http.get<any>(this.endPoint+`/available`+`?programId=${programId}&semester=${semester}`+`&page=${page-1}&size=${pageSize}&sort=id&order=ASC`).pipe(

      catchError((e) => {



        console.log('Error obteniendo todos los cursos', e.error.mensaje, 'error');
        return throwError(e);

      })
    );
  }
  getAllCoursesWithType(page:number, pageSize:number){
    // TODO consumir este servicio por tipo algun tipo de filtrar
    return this.http.get<any>(this.endPoint+`?page=${page-1}&size=${pageSize}&sort=id&order=ASC`).pipe(
      catchError((e) => {



        console.log('Error obteniendo todos los cursos', e.error.mensaje, 'error');
        return throwError(e);

      })
    );
  }
}
