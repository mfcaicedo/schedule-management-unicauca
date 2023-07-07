import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, throwError } from 'rxjs';
import { map } from 'rxjs/operators';

import { Person } from 'src/app/models/person.model';
import { report } from 'src/report/report';
import {ReportRoom} from 'src/app/models/ReportRoom.model'

@Injectable({
  providedIn: 'root'
})
export class ReportService {
  report: ReportRoom[] = [];
  endPoint: string = report.urlRep;
  // endPoint:String = 'api/environment'

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
      Authorization: 'my-auth-token'
    })
  };

  constructor(
    private http: HttpClient
  ) { }
  ngOnInit() {
    //TODO llamar metodo para cargar todos los tipos de ambientes
    //TODO llamar para obtener todas las facultades
    //this.loadEnvironmentTypes();
  }

/**
 * metodo usado para generar el reporte enciando una peticion get  con el id del ambiente
 * y resiviendo como respuesta los datos necesarios para el horario de ese
 * @param idEnviroment  el id de ambientes
 * @returns un objeto de tipo reportRum que contiene los datos del horario asociado a  el id
 */
getReportRoom(idEnviroment:string): Observable<ReportRoom[]> {
  return this.http.get<any>(this.endPoint+"/byEnvironmentId/"+idEnviroment).pipe(
    map((response: any) => response.data), // Proporcionar un tipo explícito para 'response'
    catchError((e) => {
      console.log('Error obteniendo el horario del ambiente :' +idEnviroment, e.error.mensaje, 'error');
      return throwError(e);
    })
  );
}
getReportProgram(idProgram:string): Observable<ReportRoom[]> {
  return this.http.get<any>(this.endPoint+"/byprogramId/"+idProgram).pipe(
    map((response: any) => response.data), // Proporcionar un tipo explícito para 'response'
    catchError((e) => {
      console.log('Error obteniendo el horario del idProgram :' +idProgram, e.error.mensaje, 'error');
      return throwError(e);
    })
  );
}
getReportProgramSemester(idProgram:string,semestre:string): Observable<ReportRoom[]> {
  return this.http.get<any>(this.endPoint+"/byprogramIdSemester/"+idProgram+"/"+semestre).pipe(
    map((response: any) => response.data), // Proporcionar un tipo explícito para 'response'
    catchError((e) => {
      console.log('Error obteniendo el horario del idProgram :' +idProgram, e.error.mensaje, 'error');
      return throwError(e);
    })
  );
}

getReportPerson(idPperson:string): Observable<ReportRoom[]> {
  return this.http.get<any>(this.endPoint+"/byPersonCode/"+idPperson).pipe(
    map((response: any) => response.data), // Proporcionar un tipo explícito para 'response'
    catchError((e) => {
      console.log('Error obteniendo el horario de Persona :' +idPperson, e.error.mensaje, 'error');
      return throwError(e);
    })
  );
}
getProfesorPorDeptoId(idDepto:string): Observable<Person[]>{
  return this.http.get<any>(this.endPoint+"/teacherByDeptId/"+idDepto).pipe(
    map((response: any) => response.data), // Proporcionar un tipo explícito para 'response'
    catchError((e) => {
      console.log('Error obteniendo docentes del departamento :' +idDepto, e.error.mensaje, 'error');
      return throwError(e);
    })
  );
}

getProfesoresPorNombre(filtroNombre:string): Observable<Person[]>{
  return this.http.get<any>(this.endPoint+"/teacherByName/"+filtroNombre).pipe(
    map((response: any) => response.data), // Proporcionar un tipo explícito para 'response'
    catchError((e) => {
      console.log('Error obteniendo docentes con nombre :' +filtroNombre, e.error.mensaje, 'error');
      return throwError(e);
    })
  );
}
}
