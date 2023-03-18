import{Program} from './program.model'
export interface Subject{
  subjectCode:string;
  name:string;
  weeklyOverload:number;
  timeBlock:boolean;
  semester:number;
  program:Program;
  // courses: Course[];
}
