import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Course } from 'src/app/models/course.model';
import { Environment } from 'src/app/models/environment.model';
import { Schedule } from 'src/app/models/schedule.model';
import { CourseService } from 'src/app/services/course/course.service';
import { EnvironmentService } from 'src/app/services/environment/environment.service';
import { ScheduleService } from 'src/app/services/schedule/schedule.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-schedule-update',
  templateUrl: './schedule-update.component.html',
  styleUrls: ['./schedule-update.component.scss']
})
export class ScheduleUpdateComponent {

  takenEnvironmentSchedules:Schedule[]=[];
  takenProfessorSchedules:Schedule[]=[];
  environmentId=0
  scheduleId=0
  showInfo=false
  showAvailable=false
  scheduleSelectedUpdate !:Schedule;
  updateIsSelected=false;
  curso:Course={
    'courseId':1,'courseGroup':'A','courseCapacity':20,'periodId':'',
    'subjectCode':'','personCode':'','remainingHours':2}
  environment:Environment={id:1,name:'Salon fundador',location:'Bloque C',capacity:30,environmentType:'SALON',facultyId:"FIET",availableResources:[]};
  schedule: Schedule ={id:1,day:"LUNES",startingTime:'07:00:00',endingTime:'09:00:00',course:this.curso,environment:this.environment};
  constructor(

    private scheduleService:ScheduleService,
    private router: Router,
    private route:ActivatedRoute,
    private courseService: CourseService,
    private environmentService:EnvironmentService

  ){

  }
  ngOnInit(){
    this.route.paramMap.subscribe(
      params=>{
        this.environmentId= Number(params.get('ambienteId'));
        this.scheduleId= Number(params.get('scheduleData'))

        console.log("Por parametro ",this.environmentId," y ",this.scheduleId)
        if(this.environmentId!= null ){
          this.environmentService.getEnvironmentsByEnvironmentId(this.environmentId).subscribe(response=>{
            console.log("response de environment  id ",response)
            this.environment = response.data as Environment
            this.fillTakenEnvironmentSchedule(this.environmentId)
          })


        }
        if(this.scheduleId!= null ){
          this.scheduleService.getScheduleById(this.scheduleId).subscribe(response=>{
            console.log("response de sheduleby id ",response)
            this.schedule = response as Schedule
            this.fillTakenProfessorSchedule(this.schedule.course.personCode);
          })


        }
      }
    )
    this.showInfo=true
  }
  ngAfterViewInit(){

  }

  getSelectedUpdateSchedule(schedule:Schedule | null ){

    if(schedule != null){
      this.scheduleSelectedUpdate=schedule;
      this.scheduleSelectedUpdate.course=this.schedule.course
      this.scheduleSelectedUpdate.id=this.schedule.id
      this.scheduleSelectedUpdate.environment=this.environment
      console.log("Updated schedule es ",schedule)
      this.updateIsSelected=true
      //this.fillTakenProfessorSchedule(this.scheduleSelectedUpdate.course.personCode);
    }else{



    }
  }
  onClickUpdateSchedule(){
    let scheduleresponse:Schedule;
    //consumir servicio de update shcedule
    this.scheduleService.updateSchedule(this.scheduleService.mapScheduleToDTO(this.scheduleSelectedUpdate)).subscribe( response => {
      if(response != null){
        scheduleresponse = response

        Swal.fire('Franja Actualizada',
        `La franja : ${scheduleresponse.startingTime} ${scheduleresponse.endingTime}\n Curso: ${scheduleresponse.course.courseId}  \nfue actualizada exitosamente`, 'success');
        this.router.navigate(['/schedule/detail']);
      }
    })
  }


  fillTakenProfessorSchedule(personCode:string){

    console.log("llega el person code ",personCode)
    this.scheduleService.getTakenProfessorSchedule(personCode).subscribe((response) =>{
    console.log("Response de takenprofesor detail Envi" ,response)
      this.takenProfessorSchedules = response as Schedule[]
      this.showAvailable=true

    });
}

  fillTakenEnvironmentSchedule(environmentId:number){
    // console.log("En fill Taken de detail ")
    this.scheduleService.getTakenEnvironmentSchedule(environmentId).subscribe((response) =>{
      // console.log("Response de taken Envi  " ,response)
      this.takenEnvironmentSchedules = response as Schedule[]

    });
  }
}
