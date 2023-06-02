import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, map, throwError } from 'rxjs';
import { Program } from 'src/app/models/program.model';
import { program } from 'src/program/program';
@Injectable({
  providedIn: 'root'
})
export class ProgramService {

  endPoint:string='api/program';
  EndPoint:string=program.urlProgr;// este endpoint trabaja con http://localhost:8081/api/program lo utilizamos en todos los metodos hechos en reporte
  constructor(
    private http : HttpClient
  ) { }
    program !:Program
  programs:Program[]=[
    {programId:'PIS',name:'INGENIERIA DE SISTEMAS',department_id:'1'},
    {programId:'PIET',name:'INGENIERIA ELECTRONICA Y TELECOMUNICACIONES',department_id:'2'}

  ]

  getAllPrograms(){
    // return this.programs;
    return this.http.get<any>(this.endPoint+``).pipe(
      catchError((e) => {

        console.log('Error obteniendo todos los RECURSOS', e.error.mensaje, 'error');
        return throwError(e);

      })
    );


  }
  getProgramById(id:string){
    // const program: Program =this.programs.find(program=> program.programId==id)!;
    // return program;
    return this.http.get<any>(this.endPoint+`/${id}`).pipe(
      catchError((e) => {

        console.log('Error obteniendo todos los RECURSOS', e.error.mensaje, 'error');
        return throwError(e);

      })
    );


    // return this.program
  }
  getProgramByFacultyId(idFaculty:string): Observable<Program[]> {
    return this.http.get<any>(this.EndPoint+"/consultProgramsByFacultyId/"+idFaculty).pipe(
      map((response: any) => response.data), // Proporcionar un tipo explícito para 'response'
      catchError((e) => {
        console.log('Error obteniendo programas por Facultad', e.error.mensaje, 'error');
        return throwError(e);
      })
    );
  }
}
