import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { File } from 'src/app/models/file.model';
import { HttpHeaders } from '@angular/common/http';
import { catchError, Observable, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';
import { blob } from 'stream/consumers';

@Injectable({
  providedIn: 'root'
})
export class OfertaAcademicaService {

  endPoint: String = environment.urlAcadOffer
  endPointProgram: String = environment.urlProgram;
  offers:string="/findAllByStatefile";
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
      Authorization: 'my-auth-token'
    })
  };

  constructor(
    private http: HttpClient
  ) { }

  /**
   * Metodo que invoca al servicio para cargar el archivo de oferta academica en la db 
   * @param file archivo de oferta academica tipo excel
   * @returns objeto con la respuesta del servicio
   */
  uploadFile(file: Blob) {
    console.log("llego al servicio", file);
    console.log("llego al servicio endpoint ", this.endPoint);
    const dto = new FormData();
    dto.append('file', file);
    return this.http.post<File>(this.endPoint + '/uploadFile', dto, {
    });
  }
  /**
   * Metodo que invoca al servicio para descargar el archivo de plantilla de oferta academica
   * @returns Archivo de plantilla de oferta academica (Excel)
   */
  downloadTemplateService(programCode: String): Observable<any> {
    console.log("llega al metodo al servicio ", this.endPoint);
    const headers = new HttpHeaders().set('Content-Type', 'application/json');
    // return this.http.get(this.endPoint + `/downloadTemplate/${programCode}`, { responseType: 'blob' });
    // return this.http.get<any>(this.endPoint + `/downloadTemplate/${programCode}`, {headers, responseType: 'blob' as 'json'})
    return this.http.get<any>(this.endPoint + `/downloadTemplate/${programCode}`, { responseType: 'json' })

      .pipe(
        catchError((e) => {
          console.log('Error obteniendo la plantilla de la oferta académica', e.error.mensaje, 'error');
          return throwError(e);
        })
      );
  }

  /**
   * Metodo que invoca al servicio para descargar los archivos que se han cargado para la oferta academica
   * @returns objeto con la respuesta del servicio
   */
  findAllFiles(page: number, pageSize: number): Observable<any> {
    return this.http.get<any>(
      this.endPoint +`?page=${page - 1}&size=${pageSize}&sort=dateRegisterFile&order=ASC`)
      .pipe(
        catchError((e) => {
          console.log('Error obteniendo todos los archivos de la oferta académica', e.error.mensaje, 'error');
          return throwError(e);
        })
      );
  }

  /**
   * Metodo que invoca al servicio para obtener los archivos dependiendo del estado
   * @returns lista con la respuesta del servicio
   */
  findByStateFile(statefile:string): Observable<any> {
    return this.http.get<any>(
      this.endPoint +this.offers + `?stateFile=${statefile}`)
      .pipe(
        catchError((e) => {
          console.log('Error obteniendo los archivos de la oferta académica por estado ', e.error.mensaje, 'error');
          return throwError(e);
        })
      );
  }


  /**
  * Método que invoca al servicio para obtener todos los programas
  * @returns Observable con la respuesta del servicio con todos los programas
  */
  getAllPrograms(): Observable<any> {
    return this.http.get<any>(this.endPointProgram + '', { responseType: 'json' })
      .pipe(
        catchError((e) => {
          console.log('Error obteniendo todos los programas', e.error.mensaje, 'error');
          return throwError(e);
        }));
  }
}
