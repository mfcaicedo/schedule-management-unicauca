import {Period} from './period.model'
import {Subject} from './subject.model'
import {Teacher} from './teacher.model';
export interface Course{

  courseId: number;
  courseGroup:string;
  courseCapacity:number;
  periodId:string;
  subjectCode:string;
  teacherCode:string;
  remainingHours:number;
  // period:Period;
  // subject:Subject;
  // teacher:Teacher;
}
// INSERT INTO `course` (`course_id`, `course_capacity`, `course_group`, `period_periodId`, `subject_code`, `teacher_code`, `remaining_hours`) VALUES ('1', '13', 'A', '2022_02', 'POO', '1', '2');
