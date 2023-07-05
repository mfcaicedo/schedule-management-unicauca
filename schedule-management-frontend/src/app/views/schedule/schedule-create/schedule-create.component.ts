import { Component, ElementRef, ViewChild } from '@angular/core';
import Swal from 'sweetalert2';
import { Course } from 'src/app/models/course.model';
import { Subject } from 'src/app/models/subject.model';
import { Environment } from 'src/app/models/environment.model';
import { Program } from 'src/app/models/program.model';
import { Schedule, ScheduleDTO } from 'src/app/models/schedule.model';
import { ScheduleService } from 'src/app/services/schedule/schedule.service';
import { Router } from '@angular/router';
import { ScheduleBeforeCreateFormComponent } from '../schedule-before-create-form/schedule-before-create-form.component';

@Component({
  selector: 'app-schedule-create',
  templateUrl: './schedule-create.component.html',
  styleUrls: ['./schedule-create.component.scss']
})
export class ScheduleCreateComponent {
  items = [1, 2, 3, 4];
  progressMadeProgramSemester: number = 0;
  progressMadeForm: number = 0;
  @ViewChild('beforeFormAccordion', { static: false }) beforeFormAccordion!: ElementRef<HTMLDivElement>;

  sumProgress: number = 10;
  showForm: boolean = true;
  createFormIsValid = false;
  program: Program = {
    'programId': '0',
    'name': '',
    'department_id': '',
    'color':''
  };
  @ViewChild('beforeForm', { static: false }) beforeForm!: ScheduleBeforeCreateFormComponent;
  showSelectedProgramAndSemester: boolean = false;
  showScheduleView: boolean = false;
  semester: number = 0;
  course: Course = { 'courseId': 1, 'courseGroup': 'A', 'courseCapacity': 20, 'periodId': '', 'subject': {} as Subject, 'personCode': '', 'remainingHours': 0, 'typeEnvironmentRequired': '' };
  environmentSelected!: Environment;
  scheduleToCreate!: Schedule;
  continueCreatingSchedule: boolean = false;
  changeValue: boolean = true;
  draggingCourse: boolean = false; // Variable para rastrear si se está arrastrando un curso

  constructor(
    private scheduleService: ScheduleService,
    private router: Router,
  ) { }

  getSelectedProgram(program: Program) {
    this.program = program;
  }

  getSelectedSemester(semestre: number) {
    this.semester = semestre;
  }

  getSelectedCourse(course: Course) {
    this.course = course;
  }

  getProgressMadeProgramSemester(progress: number) {
    this.progressMadeProgramSemester += progress;
    if (this.progressMadeProgramSemester == 100) {
      this.showSelectedProgramAndSemester = true;
    } else {
      this.showSelectedProgramAndSemester = false;
    }
  }

  getProgressMadeForm(progress: number) {
    this.progressMadeForm += progress;
    if (this.progressMadeForm >= 60) {
      this.showScheduleView = true;
    } else {
      this.showScheduleView = false;
    }
  }

  changeShowForm() {
    if (this.draggingCourse) { // Cerrar el acordeón si se está arrastrando un curso
      this.draggingCourse = false;
      return;
    }
    this.progressMadeProgramSemester = 0;
    this.progressMadeForm = 0;
    this.showSelectedProgramAndSemester = false;
    this.changeValue = false;
    this.beforeForm.cleanSelect();
  }

  change() {
    if (this.changeValue == false) {
      this.changeValue = true;
    }
  }

  getSelectedEnvironment(environment: Environment) {
    this.environmentSelected = environment;
  }

  getCreateFormIsValid(result: boolean) {
    this.createFormIsValid = result;
  }

  onSaveSchedule(scheduleToCreate: ScheduleDTO) {
    let scheduleresponse: Schedule;

    this.scheduleService.saveSchedule(scheduleToCreate).subscribe(
      response => {
        if (response != null) {
          scheduleresponse = response as Schedule;
          Swal.fire(`Franja creada, por asignar ${scheduleresponse.course.remainingHours} horas`,
            `La franja : ${scheduleresponse.startingTime} ${scheduleresponse.endingTime}\n Curso: ${scheduleresponse.course.courseId}  \nfue creado exitosamente`, 'success');
        }
      },
      err => {
        Swal.fire(`Error: ${err.message} `,
          `La franja : ${scheduleresponse.startingTime} ${scheduleresponse.endingTime}\n Curso: ${scheduleresponse.course.courseId}  \n`, 'warning');
        this.router.navigate(['//schedule/detail']);
      }
    );
  }

  closeAccordion() {
    const accordionElement = this.beforeFormAccordion.nativeElement;
    accordionElement.classList.add('closed');
  }

  onDragStart() {
    this.draggingCourse = true; // Establecer la variable draggingCourse en true al comenzar a arrastrar un curso
  }

  onDragEnd() {
    this.draggingCourse = false; // Establecer la variable draggingCourse en false al finalizar el arrastre del curso
  }
  

  toggleFormAccordion() {
    this.showSelectedProgramAndSemester = !this.showSelectedProgramAndSemester;
  }
  
}
