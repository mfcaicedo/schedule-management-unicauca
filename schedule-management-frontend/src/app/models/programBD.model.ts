import { Department } from "./department.model";
import { Person } from "./person.model";

/**EL OTRO PROGRAM PARSE QUE QUEMA LOS DATOS ENTONCES NO CORRESPONDE A LA BD , ESTE FUE NECESARIO PARA QUE COORESPONDA */
export interface ProgramBD{
  
  programId:string;
  name:string;
  color:string;
  department:Department;
  person:Person;

  
}
