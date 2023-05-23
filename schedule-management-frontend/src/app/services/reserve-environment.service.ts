import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable, throwError } from 'rxjs';
import { Environment } from 'src/app/models/environment.model';
import { Faculty } from 'src/app/models/faculty.model';
import { environment } from 'src/environments/environment';
import Swal from 'sweetalert2';
import { reserveEnvironment } from '../models/reserve-environment.model';
import { availabilityEnvironment } from '../models/availabilityEnvironment.model';

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

ngOnInit() {
  //TODO llamar metodo para cargar todos los tipos de ambientes
  //TODO llamar para obtener todas las facultades
  //this.loadEnvironmentTypes();
}

postAllEnvironmentsByEnvironmentTypePage(availabilityEnvironmentDTO : availabilityEnvironment): Observable<any> {
  //TODO agregar autorizacion
  // localhost:8081/api/environment/byEnvironmentType?page=0&size=10&sort=id&order=ASC&environmentType=LABORATORIO
  //{ headers: this.userServie.agregarAuthorizationHeader() }
  return this.http.post<any>(` ${this.endPoint}` ,availabilityEnvironmentDTO).pipe(
    catchError((e) => {
      // this.router.navigate(['/documentos']);

      console.log('Error obteniendo todos los ambientes', e.error.mensaje, 'error');
      return throwError(e);

    })
  );
}

}
