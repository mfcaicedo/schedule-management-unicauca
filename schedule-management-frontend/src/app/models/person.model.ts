import { Department } from './department.model'
export interface Person {
  personCode: string;
  fullName: string;
  personType: string;
  department: Department[];
  // courses:Course[];
}
