import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, throwError } from 'rxjs';
import { map } from 'rxjs/operators';
import { Department } from 'src/app/models/department.model';
import { department } from 'src/department/department';

@Injectable({
  providedIn: 'root'
})
export class DepartmentService {

  UrlReporteDepartmentByFac: string = department.urlDepartmentbyFac;

  
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
      Authorization: 'my-auth-token'
    })
  };
  constructor(private http: HttpClient) {
    
  }
  /**
   * este es un metodo que recibe la ID de una facultad y retorna todos los departamentos 
   * que se encuentan dentro de la facultad
   * 
   * @param idFaculty id de la Facultad desde la que buscara los departamentos
   * @returns 
   */
  getByFaculty(idFaculty:string): Observable<Department[]> {
    return this.http.get<any>(this.UrlReporteDepartmentByFac+"/"+idFaculty).pipe(
      map((response: any) => response.data), // Proporcionar un tipo explícito para 'response'
      catchError((e) => {
        console.log('Error obteniendo todos los departamentos por medio de Fac ID', e.error.mensaje, 'error');
        return throwError(e);
      })
    );
  }
  
}
