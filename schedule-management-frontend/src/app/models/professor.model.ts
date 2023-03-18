import { Department } from "./department.model";

export interface Professor{
  // TODO atributos de teacher
  teacherCode:string;
  fullName: string;
  department:Department[];

}
  export interface ProfesorDTO extends Omit<Professor,'availableResources'>
  {

  }

  export interface EnvironmentTypeEnumeration{
    type:string;
  }
