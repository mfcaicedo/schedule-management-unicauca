import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import {  Observable, throwError } from 'rxjs';
import {catchError } from 'rxjs/operators';
import { Environment } from 'src/app/models/environment.model';
import { Faculty } from 'src/app/models/faculty.model';
import { environment } from 'src/environments/environment';
import Swal from 'sweetalert2';
import { reserveEnvironment } from '../../models/reserve-environment.model';
import { availabilityEnvironment } from '../../models/availabilityEnvironment.model';
import { finalEventSchedule } from '../../models/FinalEventSchedule.model';

@Injectable({
  providedIn: 'root'
})
export class ReserveEnvironmentService {

  constructor(
    private http:HttpClient
  ) {}
/*
  GetInfoReserve(){
    return this.http.post<any>(Ruta del back)
  }
*/

httpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json',
    Authorization: 'my-auth-token'
  })
};

endPoint: String = environment.urlReserve;
endPointSchedule: String = environment.urlReserveInSchedule;
endPointScheduleGetReserves: String = environment.urlGetReserve;

ngOnInit() {
  //TODO llamar metodo para cargar todos los tipos de ambientes
  //TODO llamar para obtener todas las facultades
  //this.loadEnvironmentTypes();
}

//cambiarle el nombre por all en vez de type
postAllEnvironmentsByEnvironmentTypePage(availabilityEnvironmentDTO : availabilityEnvironment): Observable<any> {
  //TODO agregar autorizacion
  console.log(availabilityEnvironmentDTO);
  return this.http.post<any>(` ${this.endPoint}`,availabilityEnvironmentDTO).pipe(
    catchError((e) => {
      
      console.log('Error obteniendo todos los ambientes', e.error.mensaje, 'error');
      return throwError(e);

    })
  );
}
postSaveEventInSchedule(FinalEventScheduleDTO:finalEventSchedule){
  console.log(FinalEventScheduleDTO+"dto")
  console.log(FinalEventScheduleDTO.event+"dto")
  console.log(FinalEventScheduleDTO.eventToScheduleList[0].startingDate+"dto")
  console.log(FinalEventScheduleDTO.eventToScheduleList.length+"size")
  return this.http.post<any>(` ${this.endPointSchedule}`,FinalEventScheduleDTO).pipe(
    catchError((e) => {
      
      console.log('Error obteniendo todos los ambientes', e.error.mensaje, 'error');
      return throwError(e);

    })
  );
}

postAllEnvironmentsByEnvironmentALL(page: number, pageSize: number,availabilityEnvironmentDTO : availabilityEnvironment): Observable<any> {
  //TODO agregar autorizacion
  
  return this.http.post<any>(` ${this.endPoint}`+ `?page=${page - 1}&size=${pageSize} ` ,availabilityEnvironmentDTO).pipe(
    
    catchError((error:HttpErrorResponse) => {
      console.log(error+"error")
      console.log('Error obteniendo todos los ambientes', error.error.mensaje, 'error');
      return throwError(error);

    })
  );
}


getReserveScheduleList(personCode:string){
  
  return this.http.get<any>(` ${this.endPointScheduleGetReserves}${personCode}`).pipe(
    catchError((e) => {
      Swal.fire('Error','El encargado no tiene reservas','error');
      console.log('Error obteniendo todos lista reservas', e.error.mensaje, 'error');
      return throwError(e);

    })
  );
}

}
