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

  //Pruebaa
  programs:string[]=['TODOS','PIS','PIET', 'TTM'];
  //programs = ["Ingeniería de sistemas", "Ingeniería electrónica"];
  endPoint: String = environment.urlSub
  endPointProgram: String = environment.urlProgram;
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
  /**
   * Método que invoca al servicio para obtener todos los programas
   * @returns Observable con la respuesta del servicio con todos los programas
   */
  getAllPrograms():Observable<any>{
    return this.http.get<any>(this.endPointProgram + '', { responseType: 'json' })
    .pipe(
      catchError((e) => {
        console.log('Error obteniendo todos los programas', e.error.mensaje, 'error');
        return throwError(e);
      }));
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

  downloadTemplateService(programCode: string) {
    console.log("llega al metodo al servicio ", this.endPoint);
    return this.http.get(this.endPoint + '/downloadTemplate/' + programCode, { responseType: 'blob' });
  }

  /**
   * Método para invocar al servicio para consultar todas las asignaturas
   * @param page número de página
   * @param pageSize tamaño de la página, cantidad de elementos por página
   * @returns Observable con la respuesta del servicio
   */
  getAllSubjectsPage(page:number, pageSize:number):Observable<any>{
    console.log("llegan page y size ",page, " ", pageSize)

    return this.http.get<any>(this.endPoint + '/allSubject' + `?page=${page-1}&size=${pageSize}&sort=subjectCode&order=ASC`).pipe(
      catchError((e) => {

        console.log('Error obteniendo todas las Asignaturas', e.error.mensaje, 'error');
        return throwError(e);

      })
    );
  }

  /**
   * Método para invocar al servicio  para consultar todos los programas pertenecientes a un programa
   * @param programId id del programa
   * @param page número de página
   * @param pageSize tamaño de la página, cantidad de elementos por página
   * @returns Observable con la respuesta del servicio
   */
  getSubjectsByProgramId(programId:string,page:number, pageSize:number):Observable<any>{
    console.log("llega al metodo buscar por programa ", programId);
    return this.http.get<any>(this.endPoint+'/byProgramId'+`?programId=${programId}&page=${page-1}&size=${pageSize}&sort=subjectCode&order=ASC`).pipe(
      catchError((e) => {
        console.log('Error obteniendo todas las asignaturas por programa', e.error.mensaje, 'error');
        return throwError(e);

      })
    );
  }
}

