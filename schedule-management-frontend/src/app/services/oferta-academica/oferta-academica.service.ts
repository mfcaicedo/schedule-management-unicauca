import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import{File} from 'src/app/models/file.model'
@Injectable({
  providedIn: 'root'
})
export class OfertaAcademicaService {

  constructor(
    private http:HttpClient
  ) { }

  endPoint='api/'

  uploadFile(file:Blob){
    const dto =  new FormData();
    dto.append('file',file);
    return this.http.post<File>(this.endPoint+'file/upload',dto,{
      // headers:{
      //   'Content-type':"multipart/form-data"
      // }
    });
  }
}
