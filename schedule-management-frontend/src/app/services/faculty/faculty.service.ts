import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable, throwError } from 'rxjs';
import { Environment } from 'src/app/models/environment.model';
import { faculty } from 'src/faculty/faculty';
import { Faculty } from 'src/app/models/faculty.model'; 
import Swal from 'sweetalert2';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class FacultyService {
  environments: Environment[] = [];
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
  }/** 
  getEnvironmentsFromResource(resourceId: number) {
    const enviroments: Environment[] = [];
    for (let index = 0; index < this.environments.length; index++) {
      for (let j = 0; j < this.environments[index].availableResources.length; j++) {
        if (this.environments[index].availableResources[j].id == resourceId) {
          enviroments.push(this.environments[index])
        }

      }

    }
    //obtener todos los ambientes que contengan al recurso que llega
    //return this.environments.filter(ambiente => ambiente.availableResources.filter(x=> x.id ==resourceId))
    return enviroments
  }
  getEnvironmentsFromResourcePaged(resourceId: number, page: number, pageSize: number): Observable<any> {
    // localhost:8081/api/environment/byResource?page=0&size=10&sort=id&order=ASC&resourceId=1
    return this.http.get<any>(this.endPoint + '/byResource' + `?page=${page - 1}&size=${pageSize}&sort=id&order=ASC&resourceId=${resourceId}`)
      .pipe(
        catchError((e) => {
          Swal.fire('Recurso en ningun ambiente',
            `Este recurso no se encuentra en ningun ambiente`, 'warning');
          console.log('Error obteniendo todos los ambientes del recurso ', e.error.mensaje, 'error');
          return e;

        })
      );
  }



  getEmptyEnvironment() {
    return {
      'id': 0,
      'name': '',
      'location': '',
      'capacity': 0,
      'environmentType': '',
      'facultyId': '',
      'availableResources': []

    }
  }*/


}

