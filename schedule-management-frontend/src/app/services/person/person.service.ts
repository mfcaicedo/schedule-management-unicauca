import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, throwError } from 'rxjs';
import { map } from 'rxjs/operators';
import { Person } from 'src/app/models/person.model';
@Injectable({
  providedIn: 'root'
})
export class PersonService {
  person: Person[] = [];
  endPoint: string = 'http://localhost:8081/api/person'
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
  
getnameByCode(idPerson:string): Observable<string> {
  return this.http.get<any>(this.endPoint+"/nameByCode/"+idPerson).pipe(
    map((response: any) => response.data), // Proporcionar un tipo explícito para 'response'
    catchError((e) => {
      console.log('Error obteniendo el nombre de la persona' +idPerson, e.error.mensaje, 'error');
      return throwError(e);
    })
  );
}
}
