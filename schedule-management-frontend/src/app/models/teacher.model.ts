import {Department} from './department.model'
export interface Teacher{
  teacherCode:string;
  fullName:string;
  department:Department[];
  // courses:Course[];
}
