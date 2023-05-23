import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable, throwError } from 'rxjs';
import { Environment } from 'src/app/models/environment.model';
import { environment } from 'src/environments/environment';

import { Program } from 'src/app/models/program.model';
import { Subject } from 'src/app/models/subject.model';
import Swal from 'sweetalert2';

@Injectable({
  providedIn: 'root'
})
export class SubjectService {

  program: Program = { programId: '1', name: 'Ingeniería de sistemas', department_id: '123' }
  subjects: Subject[] = [
    { subjectCode: '1', name: 'Cálculo 1', weeklyOverload: 4, timeBlock: true, semester: 1, program:this.program },
    {
      subjectCode: '2', name: 'Redes', weeklyOverload: 6, timeBlock: true, semester: 8, program:this.program},
    {
      subjectCode: '3', name: 'Sistemas distribuidos', weeklyOverload: 4, timeBlock: true, semester: 7, program:this.program
    },
  ]
  //Pruebaa
  programs:string[]=['TODOS','PIS','PIET'];
  //programs = ["Ingeniería de sistemas", "Ingeniería electrónica"];
  endPoint: String = environment.urlSub
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
  }

  getEmptySubject() {
    return {
      'subjectCode': '',
      'name': '',
      'weeklyOverload': 0,
      'timeBlock': false,
      'semester': 0,
      'program': '',
    }
  }
  getAllPrograms(){
    return this.programs;
  }
  uploadFile(file: Blob) {
    console.log("llego al servicio de subject", file);
    console.log("llego al servicio endpoint ", this.endPoint);
    const dto = new FormData();
    dto.append('file', file);
    return this.http.post<File>(this.endPoint + '/uploadFile', dto, {
      // headers:{
      //   'Content-type':"multipart/form-data"
      // }
    });
  }

  getAllSubjectsPage(page:number, pageSize:number):Observable<any>{
    console.log("llegan page y size ",page, " ", pageSize)

    return this.http.get<any>(this.endPoint+`?page=${page-1}&size=${pageSize}&sort=subjectCode&order=asc`).pipe(
      catchError((e) => {

        console.log('Error obteniendo todas las Asignaturas', e.error.mensaje, 'error');
        return throwError(e);

      })
    );
  }

  getSubjectsByProgramId(programId:string,page:number, pageSize:number):Observable<any>{
    return this.http.get<any>(this.endPoint+'/byProgramId'+`?program_id=${programId}&page=${page-1}&size=${pageSize}&sort=subjectCode&order=ASC`).pipe(
      catchError((e) => {
        // this.router.navigate(['/documentos']);

        console.log('Error obteniendo todas las asignaturas por programa', e.error.mensaje, 'error');
        return throwError(e);

      })
    );
  }
}

