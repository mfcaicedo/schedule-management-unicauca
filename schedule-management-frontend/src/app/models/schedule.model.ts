import { Course } from "./course.model";
import { Environment } from "./environment.model";

export interface Schedule{

  id:number;
  day:string;
  startingTime:string;
  endingTime:string;
  course:Course;
  environment:Environment;

}

export interface ScheduleColor extends  Schedule// Omit<Schedule,'course'|'environment'>
{
  color: string;
}

export interface ScheduleDTO extends  Omit<Schedule ,'course'|'environment'>{
  courseId:number;
  environmentId:number;
}
