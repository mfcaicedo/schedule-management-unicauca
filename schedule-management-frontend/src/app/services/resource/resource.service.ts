import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable, throwError } from 'rxjs';
import { Environment } from 'src/app/models/environment.model';
import { Resource } from 'src/app/models/resource.model';
import { HttpHeaders } from '@angular/common/http';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ResourceService {

  endPoint:String = environment.urlRsc
  //endPoint:String = 'api/resource'

  resourceTypes:string[]=['all','TECNOLOGICO','PEDAGOGICO'];
  resources:Resource[]=[
    {'id':1,'name':'Televisor','resourceType':this.resourceTypes[1]},
    {'id':2,'name':'Computador','resourceType':this.resourceTypes[1]},
    {'id':3,'name':'Video bean','resourceType':this.resourceTypes[1]}
  ]

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type':  'application/json',
      Authorization: 'my-auth-token'
    })
  };

  constructor(
    private http : HttpClient
  ) { }

  ngOnInit(){

  }


  getAllResources(){
    return this.resources;
  }
  getAllResourcesPage(page:number, pageSize:number):Observable<any>{
    // console.log("llegan page y size ",page, " ", pageSize)

    return this.http.get<any>(this.endPoint+`?page=${page-1}&size=${pageSize}&sort=id&order=asc`).pipe(
      catchError((e) => {

        console.log('Error obteniendo todos los RECURSOS', e.error.mensaje, 'error');
        return throwError(e);

      })
    );
  }

  getAllRemainingResources(){
    //obtener todos los recursos que no estan en un ambiente
  }
  getResourceByResourceId(idResource:number){

    return this.http.get<any>(this.endPoint+`/${idResource}`).pipe(
      catchError((e) => {

        console.log('Error obteniendo un recurso', e.error.mensaje, 'error');
        return throwError(e);

      })
    );

  }
  updateResource(idResource:number,resource:Resource){
    return this.http.put<any>(this.endPoint+`?id=${idResource}`,resource).pipe(
      catchError((e) => {

        console.log('Error actualizando todos los RECURSOS', e.error.mensaje, 'error');
        return throwError(e);

      })
    );
  }
  getResourcesByResourceType(resourceType:string,page:number, pageSize:number):Observable<any>{
    return this.http.get<any>(this.endPoint+'/byType'+`?resourceType=${resourceType}&page=${page-1}&size=${pageSize}&sort=id&order=ASC`).pipe(
      catchError((e) => {
        // this.router.navigate(['/documentos']);

        console.log('Error obteniendo todos los ambientes', e.error.mensaje, 'error');
        return throwError(e);

      })
    );
  }

  getAllResourceTypes(){
    return this.resourceTypes;
  }

  pushResourceIntoEnvironment(environment:Environment, resource:Resource){

    //environment.availableResources.push(resource)

  }

  // saveResource(resource:Resource): Observable<any>{
  saveResource(resource:Resource): Observable<any>{
    console.log("llego al servicio",resource);
    console.log("llego al servicio",this.endPoint);

    return this.http.post<Resource>(this.endPoint+'',resource,this.httpOptions)
    .pipe(
      catchError((e) => {

        console.log('Error GUARDANDO el RECURSO', e.error.mensaje, 'error');
        return throwError(e);

      })
    );
  }



}
