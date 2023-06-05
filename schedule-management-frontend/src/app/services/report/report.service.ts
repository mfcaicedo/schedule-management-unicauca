import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, throwError } from 'rxjs';
import { map } from 'rxjs/operators';
import { ReportRoom } from 'src/app/models/ReportRoom.model';
import { report } from 'src/report/report';

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
}