import { Component } from '@angular/core';
import { Environment } from 'src/app/models/environment.model';
import { Schedule, ScheduleDTO } from 'src/app/models/schedule.model';
import { ScheduleService } from 'src/app/services/schedule/schedule.service';
import Swal from 'sweetalert2';
import { Router } from '@angular/router';
@Component({
  selector: 'app-schedule-detail',
  templateUrl: './schedule-detail.component.html',
  styleUrls: ['./schedule-detail.component.scss']
})
export class ScheduleDetailComponent {

  showScheduleView:boolean=false;
  ambiente!:Environment;
  isEnvironmentSelected : boolean = false
  takenEnvironmentSchedules:Schedule[]=[];
  takenProfessorSchedules:Schedule[]=[];
  showBtnAccion=false
  updateIsSelected:boolean=false
  scheduleSelected:Schedule   =  {
    id:0,
    day:'',
    startingTime:'',
    endingTime:'',
    course:{'courseId':1,'courseGroup':'A','courseCapacity':20,'periodId':'','subjectCode':'','personCode':'','remainingHours':0},
    environment: {
      id: 0,
      name: '',
      location: '',
      capacity: 0,
      environmentType: '',
      facultyId: '',
      availableResources: []
    }
  }
  scheduleSelectedUpdate !:Schedule;
  constructor(

    private scheduleService:ScheduleService,
    private router: Router


  ){

  }
  ngOnInit(){
    this.showBtnAccion=false
  }
  getSelectedEnvironment(environment:Environment | null){
    if(environment != null){
      this.ambiente=environment
      console.log("ambiente seleccionaod ",this.ambiente)
      // consumir servicio que trae el horario ocupado del amibiente
      this.fillTakenEnvironmentSchedule()
      this.isEnvironmentSelected=false


    }else{

      this.isEnvironmentSelected=false
      this.showBtnAccion=false
      //limpiar horario ocupado del ambiente
      this.takenEnvironmentSchedules=[]
    }

  }
  getSelectedSchedule(schedule:Schedule | null ){
    if(schedule != null){
      this.scheduleSelected=schedule;
      console.log("schedule seleccionaod ",this.scheduleSelected)
      this.showBtnAccion=true
      // this.fillTakenProfessorSchedule(this.scheduleSelected.course.personCode);
    }else{

      this.showBtnAccion=false

    }
  }
  getSelectedUpdateSchedule(schedule:Schedule | null ){
    if(schedule != null){

      this.scheduleSelectedUpdate=schedule;
      this.scheduleSelectedUpdate.course=this.scheduleSelected.course
      this.scheduleSelectedUpdate.id=this.scheduleSelected.id
      this.scheduleSelectedUpdate.environment=this.ambiente
      console.log("Updated schedule es ",schedule)
      this.showBtnAccion=true
      // this.fillTakenProfessorSchedule(this.scheduleSelectedUpdate.course.personCode);
    }else{

      this.showBtnAccion=false

    }
  }
  getIfEnvironmentSelected(value:boolean){
    this.isEnvironmentSelected=value
  }
  onUpdateSchedule(){
    this.updateIsSelected=true

   this.router.navigate([ `/schedule/update/${this.ambiente.id}/${this.scheduleSelected.id}`]);
  }
  // onClickUpdateSchedule(){
  //   let scheduleresponse:Schedule;
  //   //consumir servicio de update shcedule
  //   this.scheduleService.updateSchedule(this.getInfo(this.scheduleSelectedUpdate)).subscribe( response => {
  //     if(response != null){
  //       scheduleresponse = response

  //       Swal.fire('Franja Actualizada',
  //       `La franja : ${scheduleresponse.startingTime} ${scheduleresponse.endingTime}\n Curso: ${scheduleresponse.course.courseId}  \nfue actualizada exitosamente`, 'success');
  //       this.router.navigate(['./']);
  //     }
  //   })
  // }
  onDeleteSchedule(){
    this.scheduleService.deleteSchedule(this.scheduleSelected.id).subscribe(response=>{
      if(response.status==500){

      }else if(response){
        Swal.fire('Franja Eliminada',
        `La franja : fue eliminada exitosamente`, 'success');
        this.router.navigate(['/schedule/create']);
      }
    })
  }
  // getInfo(scheduleSelected:Schedule){
  //   let scheduleCreated :ScheduleDTO= {id:0, day:'',startingTime:'',endingTime:'',courseId:0,environmentId:0};
  //   scheduleCreated.id=scheduleSelected.id
  //   scheduleCreated.day=scheduleSelected.day.toUpperCase()
  //   scheduleCreated.startingTime=scheduleSelected.startingTime
  //   scheduleCreated.endingTime=scheduleSelected.endingTime
  //   scheduleCreated.courseId=scheduleSelected.course.courseId
  //   scheduleCreated.environmentId=scheduleSelected.environment.id
  //   // console.log("Emitiendo schedule ",scheduleCreated)
  //   return scheduleCreated
  // }
  // fillTakenProfessorSchedule(personCode:string){


  //     this.scheduleService.getTakenProfessorSchedule(personCode).subscribe((response) =>{
  //     console.log("Response de takenprofesor detail Envi" ,response)
  //       this.takenProfessorSchedules = response as Schedule[]


  //     });
  // }
  fillTakenEnvironmentSchedule(){
    // console.log("En fill Taken de detail ")
    this.scheduleService.getTakenEnvironmentSchedule(this.ambiente.id).subscribe((response) =>{
      // console.log("Response de taken Envi  " ,response)
      this.takenEnvironmentSchedules = response as Schedule[]

     });
  }
}
