import {Department} from './department.model'
export interface Person{
  personCode:string;
  fullName:string;
  department:Department[];
  // courses:Course[];
}
