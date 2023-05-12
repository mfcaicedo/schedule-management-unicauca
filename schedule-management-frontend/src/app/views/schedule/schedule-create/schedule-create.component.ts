import { Component, ElementRef, ViewChild } from '@angular/core';
import Swal from 'sweetalert2';
// import { NgxBootstrapService } from '@coreui/angular';
import { Course } from 'src/app/models/course.model';
import { Environment } from 'src/app/models/environment.model';
import { Program } from 'src/app/models/program.model';
import { Schedule, ScheduleDTO } from 'src/app/models/schedule.model';
import { ScheduleService } from 'src/app/services/schedule/schedule.service';
import { ignoreElements } from 'rxjs';
import { Router } from '@angular/router';
import { error } from 'console';
import { ScheduleBeforeCreateFormComponent } from '../schedule-before-create-form/schedule-before-create-form.component';

@Component({
  selector: 'app-schedule-create',
  templateUrl: './schedule-create.component.html',
  styleUrls: ['./schedule-create.component.scss']
})
export class ScheduleCreateComponent {
  items = [1, 2, 3, 4];
  progressMadeProgramSemester:number=0;
  progressMadeForm:number=0;
  sumProgress:number=10;
  showForm:boolean=true;
  createFormIsValid=false
  program:Program={
    'programId':'0',
    'name':'',
    'department_id':''
  };
  // @ViewChild('beforeFormAccordion',{static:true}) beforeFormAccordion !:ElementRef ;
  @ViewChild('beforeForm', { static: false }) beforeForm!: ScheduleBeforeCreateFormComponent;
  showSelectedProgramAndSemester:boolean=false;
  showScheduleView:boolean=false;
  semester:number=0;
  course:Course={'courseId':1,'courseGroup':'A','courseCapacity':20,'periodId':'','subjectCode':'','personCode':'','remainingHours':0}
  environmentSelected!: Environment;
  scheduleToCreate!:Schedule;
  continueCreatingSchedule:boolean = false
  changeValue:boolean = true


  constructor(
    private scheduleService: ScheduleService,
    private router: Router,
    ) { }
  getSelectedProgram(program:Program){

    this.program=program;
  }
  getSelectedSemester(semestre:number){

    this.semester=semestre;
  }
  getSelectedCourse(course:Course){

    this.course=course

  }
  getProgressMadeProgramSemester(progress:number){
    this.progressMadeProgramSemester += progress
    if(this.progressMadeProgramSemester == 100){
      this.showSelectedProgramAndSemester =true;

    }else{
      this.showSelectedProgramAndSemester =false;
    }
    //this.progressMadeForm +=(progress/2)
  }
  getProgressMadeForm(progress:number){
    this.progressMadeForm += progress

    if(this.progressMadeForm >= 60){
      this.showScheduleView=true
    }else{
      this.showScheduleView=false
    }
  }
  changeShowForm(){
    this.progressMadeProgramSemester=0
    this.progressMadeForm=0
    this.showSelectedProgramAndSemester =false;
    this.changeValue=false
    this.beforeForm.cleanSelect()
  }

  change(){
    console.log("Entra aqui ",this.changeValue)
    if(this.changeValue ==false){
      this.changeValue=true
    }
  }
  getSelectedEnvironment(environment:Environment){
    this.environmentSelected = environment;
  }
  getCreateFormIsValid(result:boolean){
    this.createFormIsValid=result
  }
  getInfo(){

  }
  changeContinueCreating(value:boolean){
    this.scheduleService.updateContinueCreatingForCourse(value)
  }
  onSaveSchedule(scheduleToCreate:ScheduleDTO){
    console.log("entra a save envi")
    let scheduleresponse:Schedule;
    //llamar a recurso de save environment
    this.scheduleService.saveSchedule(scheduleToCreate).subscribe(
      response => {


        if(response != null){
          scheduleresponse = response as Schedule

          Swal.fire(`Franja creada, por asignar ${scheduleresponse.course.remainingHours} horas`,
          `La franja : ${scheduleresponse.startingTime} ${scheduleresponse.endingTime}\n Curso: ${scheduleresponse.course.courseId}  \nfue creado exitosamente`, 'success');
          this.router.navigate(['//schedule/detail']);

        }


      }, err=>{
        Swal.fire(`Error: ${err.message} `,
          `La franja : ${scheduleresponse.startingTime} ${scheduleresponse.endingTime}\n Curso: ${scheduleresponse.course.courseId}  \n`, 'warning');
          this.router.navigate(['//schedule/detail']);
      }

    );
  }
}
