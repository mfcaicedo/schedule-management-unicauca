import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import{File} from 'src/app/models/file.model';
import { HttpHeaders } from '@angular/common/http';
import { environment } from 'src/environments/environment';
@Injectable({
  providedIn: 'root'
})
export class OfertaAcademicaService {

  endPoint:String = environment.urlAcadOffer
  // endPoint='api/'

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type':  'application/json',
      Authorization: 'my-auth-token'
    })
  };

  constructor(
    private http:HttpClient
  ) { }


  uploadFile(file:Blob){
    console.log("llego al servicio",file);
    console.log("llego al servicio endpoint ",this.endPoint);
    const dto =  new FormData();
    dto.append('file',file);
    return this.http.post<File>(this.endPoint+'/uploadFile',dto,{
      // headers:{
      //   'Content-type':"multipart/form-data"
      // }
    });
  }
}
