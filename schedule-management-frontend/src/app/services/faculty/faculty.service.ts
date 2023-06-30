import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, throwError } from 'rxjs';
import { map } from 'rxjs/operators';
import { Faculty } from 'src/app/models/faculty.model';
import { faculty } from 'src/faculty/faculty';

@Injectable({
  providedIn: 'root'
})
export class FacultyService {
  //environments: Environment[] = [];
  endPoint: string = faculty.urlAllFac;
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


  getAllFaculty(): Observable<Faculty[]> {
    return this.http.get<any>(this.endPoint).pipe(
      map((response: any) => response.data), // Proporcionar un tipo explícito para 'response'
      catchError((e) => {
        console.log('Error obteniendo todos lasFac', e.error.mensaje, 'error');
        return throwError(e);
      })
    );
  }
}

