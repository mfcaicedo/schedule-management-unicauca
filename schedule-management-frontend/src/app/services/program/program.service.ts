import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, map, throwError } from 'rxjs';
import { ProgramBD } from 'src/app/models/ProgramBD.model';
import { Program } from 'src/app/models/program.model';
import { environment } from 'src/environments/environment';
@Injectable({
  providedIn: 'root'
})
export class ProgramService {

  endPoint:String=environment.urlProgram
  constructor(
    private http : HttpClient
  ) { }
    program !:Program
 programs:Program[]=[
    {programId:'PIS',name:'INGENIERIA DE SISTEMAS',department_id:'1',color:'bg-orange'},
    {programId:'PIET',name:'INGENIERIA ELECTRONICA Y TELECOMUNICACIONES',department_id:'2',color:'bg-blue'}

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
  getProgramIds() {
    return this.programs.map((program) => program.programId);
  }
  getProgramById(id:string){
    // const program: Program =this.programs.find(program=> program.programId==id)!;
    // return program;
    return this.http.get<Program>(this.endPoint+`/${id}`).pipe(
      catchError((e) => {

        console.log('Error obteniendo todos los RECURSOS', e.error.mensaje, 'error');
        return throwError(e);

      })
    );


    // return this.program
  }
  getProgramByFacultyId(idFaculty:string): Observable<Program[]> {
    return this.http.get<any>(this.endPoint+"/consultProgramsByFacultyId/"+idFaculty).pipe(
      map((response: any) => response.data), // Proporcionar un tipo explícito para 'response'
      catchError((e) => {
        console.log('Error obteniendo programas por Facultad', e.error.mensaje, 'error');
        return throwError(e);
      })
    );
  }
  /**
   * obtiene todos los programas, a diferencia de get all programs los obtiene de un Data de la respesta del data
   */
  getPrograms(): Observable<ProgramBD[]> {
    //alert("ruta "+(this.endPoint+"/consultarProgramas/"))
    return this.http.get<any>(this.endPoint+"/consultarProgramas/").pipe(
      map((response: any) => response.data), // Proporcionar un tipo explícito para 'response'
      catchError((e) => {
        //console.log('Error obteniendo programas por Facultad', e.error.mensaje, 'error');
        return throwError(e);
      })
    );
  }
}
