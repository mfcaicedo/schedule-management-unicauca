import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable, throwError } from 'rxjs';
import { Environment } from 'src/app/models/environment.model';
import { Faculty } from 'src/app/models/faculty.model';
import { environment } from 'src/environments/environment';
import Swal from 'sweetalert2';

@Injectable({
  providedIn: 'root'
})
export class EnvironmentService {

  faculty:Faculty={facultyId:1,facultyName:'Ciencias humanas',departments:[],environments:[]}
  environments:Environment[]=[
    {id:1,name:'Salon fundador',location:'Bloque C',capacity:30,environmentType:'SALON',facultyId:"FIET",availableResources:[]},
    {id:2,name:'Salon prueba',location:'Bloque D',capacity:30,environmentType:'AUDITORIO',facultyId:"FIET",availableResources:[
      {'id':3,'name':'Video bean','resourceType':'TECNOLOGICO'}
    ]},
    {id:3,name:'Salon prueba',location:'Bloque D',capacity:30,environmentType:'SALON',facultyId:"FIET",availableResources:[
      {'id':2,'name':'Computador','resourceType':'TECNOLOGICO'},
      {'id':3,'name':'Video bean','resourceType':'TECNOLOGICO'}
    ]},
  ]

  environmentTypes=['all','AUDITORIO', 'LABORATORIO', 'SALON'];
  facultys=["FIET","Ciencias"];
  endPoint:String = environment.urlEnv
  // endPoint:String = 'api/environment'

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
    //TODO llamar metodo para cargar todos los tipos de ambientes
    //TODO llamar para obtener todas las facultades
    //this.loadEnvironmentTypes();
  }

  getAllEnvironmentsPage(page:number, pageSize:number):Observable<any>{
    //TODO agregar autorizacion
    //{ headers: this.userServie.agregarAuthorizationHeader() }
    return this.http.get<any>(this.endPoint+`?page=${page-1}&size=${pageSize}&sort=id&order=ASC`).pipe(
      catchError((e) => {
        // this.router.navigate(['/documentos']);

        console.log('Error obteniendo todos los ambientes', e.error.mensaje, 'error');
        return throwError(e);

      })
    );
  }
  getAllEnvironmentsByEnvironmentTypePage(type:string,page:number, pageSize:number):Observable<any>{
    //TODO agregar autorizacion
    // localhost:8081/api/environment/byEnvironmentType?page=0&size=10&sort=id&order=ASC&environmentType=LABORATORIO
    //{ headers: this.userServie.agregarAuthorizationHeader() }
    return this.http.get<any>(this.endPoint+'/byEnvironmentType'+`?page=${page-1}&size=${pageSize}&sort=id&order=ASC&environmentType=${type}`).pipe(
      catchError((e) => {
        // this.router.navigate(['/documentos']);

        console.log('Error obteniendo todos los ambientes', e.error.mensaje, 'error');
        return throwError(e);

      })
    );
  }
  saveEnvironment(environment:Environment){
    //TODO agregar autorizacion
    return this.http.post<any>(this.endPoint+'',environment,this.httpOptions)
    .pipe(
      catchError((e) => {

        console.log('Error GUARDANDO el ambiente', e.error.mensaje, 'error');
        return throwError(e);

      })
    );
  }
  addResourceToEnvironment(resourceId:number, environmentId:number){
    //Todo agregar autorizacion
    return this.http.post<any>(this.endPoint+'/addResource'+`?resourceId=${resourceId}&environmentId=${environmentId}`,this.httpOptions)
    .pipe(
      catchError((e) => {

        console.log('Error GUARDANDO el ambiente', e.error.mensaje, 'error');
        return throwError(e);

      })
    );
  }
  updateEnvironment(environment:Environment){
    //llamar a actualizar ambiente
    console.log(environment)
    return this.http.post<any>(this.endPoint+'',environment,this.httpOptions)
    .pipe(
      catchError((e) => {

        console.log('Error Actualizando el ambiente', e.error.mensaje, 'error');
        return throwError(e);

      })
    );
  }
  updateResourceOfEnviroment(resourceId:number, environmentId:number){
    return this.http.post<any>(this.endPoint+'/updateResource'+`?resourceId=${resourceId}&environmentId=${environmentId}`,this.httpOptions)
    .pipe(
      catchError((e) => {

        console.log('Error GUARDANDO el ambiente', e.error.mensaje, 'error');
        return throwError(e);

      })
    );
  }
  getEnvironmentsByEnvironmentId(environmentId:number){

    console.log("consumiendo ",this.endPoint+`/${environmentId}`)
    return this.http.get<any>(this.endPoint+`/${environmentId}`,this.httpOptions)
    .pipe(
      catchError((e) => {

        console.log('Error obteniendo  el ambiente', e.error.mensaje, 'error');
        return throwError(e);

      })
    )
  }
  getEnvironmentsFromResourcePaged(resourceId:number,page:number, pageSize:number):Observable<any>{
    // localhost:8081/api/environment/byResource?page=0&size=10&sort=id&order=ASC&resourceId=1
    return this.http.get<any>(this.endPoint+'/byResource'+`?page=${page-1}&size=${pageSize}&sort=id&order=ASC&resourceId=${resourceId}`)
    .pipe(
      catchError((e) => {
        // this.router.navigate(['/documentos']);
        Swal.fire('Recurso en ningun ambiente',
          `Este recurso no se encuentra en ningun ambiente`, 'warning');
        console.log('Error obteniendo todos los ambientes del recurso ', e.error.mensaje, 'error');
        return e;

      })
    );
  }
  


  getAllEnvironments(){
    return this.environments;
  }
  getEnvironmentsByEnvironmentType(type:string){

    //consultar servicio para obtener los ambientes por tipos
    return this.environments.filter(ambiente=>ambiente.environmentType == type);
  }


  getAllEnvironmentTypes(){
    return this.environmentTypes;
  }

  getAllFacultys(){
    return this.facultys;
  }

  getEnvironmentsFromResource(resourceId:number){
    const enviroments:Environment[]=[];
    for (let index = 0; index < this.environments.length; index++) {
      for (let j = 0; j < this.environments[index].availableResources.length; j++) {
        if(this.environments[index].availableResources[j].id==resourceId){
          enviroments.push(this.environments[index])
        }

      }

    }
    //obtener todos los ambientes que contengan al recurso que llega
    //return this.environments.filter(ambiente => ambiente.availableResources.filter(x=> x.id ==resourceId))
    return enviroments
  }
  getEnvironmentsFromResourceAndResourceType(resourceId:number,resourceType:string){
    //llamado desde resources
    //traer los ambientes que contienen un tipo de recurso especifico y ademas contienen el recurso especifico
    const environments=this.getEnvironmentsFromResource(resourceId) ;
    return environments.filter(ambiente=>ambiente.environmentType == resourceType)
  }

  getEmptyEnvironment(){
    return {
    'id':0,
    'name':'',
    'location':'',
    'capacity':0,
    'environmentType':'',
    'facultyId':'',
    'availableResources':[]

    }
  }
}

