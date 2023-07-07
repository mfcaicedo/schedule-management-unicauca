import { DatePipe } from "@angular/common";
import { Faculty } from "./faculty.model";
import { Resource } from "./resource.model";

export interface reserveEnvironment
{
  /*id:number;
  name: string;
  location:string;
  capacity:number;
  //environmentType:EnvironmentTypeEnumeration ;
  environmentType:string;
  // faculty:Faculty;
  facultyId:string;*/
  availableResources:Resource[];

  tipoEvento: string;
  nombreEvento: string;
  nombreEncargado: string;
  cedulaEncargado: number;
  recurrencia: string;
  descripcion: string;
  fechaInicio: string;
  horaInicio: string;
  horaFin: string;
  day:string;

}
// private Integer id;
// private String name;
// private String location;
// private Integer capacity;
// private EnvironmentTypeEnumeration environmentType;

// private String facultyId;

// private Set<ResourceDTO> availableResources;
export interface EnvironmentDTO extends Omit<reserveEnvironment,'availableResources'>
{

}

export interface EnvironmentTypeEnumeration{
  type:string;
}


