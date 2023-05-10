import { Component, EventEmitter, Input, Output, SimpleChanges } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Course } from 'src/app/models/course.model';
import { Environment } from 'src/app/models/environment.model';
import { Program } from 'src/app/models/program.model';
import { Schedule, ScheduleDTO } from 'src/app/models/schedule.model';
import { EnvironmentService } from 'src/app/services/environment/environment.service';


import { ScheduleService } from 'src/app/services/schedule/schedule.service';
@Component({
  selector: 'app-schedule-create-form',
  templateUrl: './schedule-create-form.component.html',
  styleUrls: ['./schedule-create-form.component.scss']
})
export class ScheduleCreateFormComponent {

  form!: FormGroup;
  @Output()isFormValid = new EventEmitter<boolean>;
  @Output() progress = new EventEmitter<number>()
  @Output() selectedEnvironment = new EventEmitter<Environment>();
  @Output() scheduleCreated = new EventEmitter<ScheduleDTO>();
  @Input('selectedProgram')  program!:Program;
  @Input('selectedSemester')  semester!:number;
  @Input('isEdit')isEdit!:boolean;

  constructor(
    private formBuilder:FormBuilder,
    private scheduleService:ScheduleService,
    private environmentService : EnvironmentService

  ){

  }
  courseSelected: Course = {'courseId':1,'courseGroup':'A','courseCapacity':20,'periodId':'','subjectCode':'','personCode':'','remainingHours':0}
  environmentSelected: Environment  = {'id':0,
  'name':'',
  'location':'',
  'capacity':0,
  'environmentType':'',
  'facultyId':'',
  'availableResources':[]
}
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

  takenEnvironmentSchedules:Schedule[]=[];
  takenProfessorSchedules:Schedule[]=[];


  sumProgres:number=30;
  showEnvironments:boolean=false
  showSelectedEnvironment:boolean=false;
  showBtnCreate=false;
  isEnvironmentSelected : boolean = false
  ngOnInit(){

    this.buildForm();

    this.isFormValid.emit(false)
    if(this.scheduleService.getValueContinueCreatingForCourse()==true){
      this.clearForContinueCreating()
    }


  }


  private buildForm(){
    this.form = this.formBuilder.group({

    });
  }
  clearForContinueCreating(){
    this.environmentSelected=this.environmentService.getEmptyEnvironment()
    this.scheduleSelected= this.scheduleService.getEmptySchedule()
  }
  onSelectedProgram(event:Event){
    //traer el numero de semestres de ese programa
    this.progress.emit(this.sumProgres)
  }
  onSelectedSemester(event:Event){
    //actualizar la tabla de
    this.progress.emit(this.sumProgres)
  }

  getSelectedCourse(course:Course|null){
    if(course != null){
        //recibe el curso desde courses
      this.courseSelected=course
      //consumir servicio para obtener el horario ocupado del profesor
      this.fillTakenProfessorSchedule();
      this.progress.emit(this.sumProgres) //emitir progreso curso hasta componente create
      this.showEnvironments=true
    }else{
      this.progress.emit(-this.sumProgres)
      this.isEnvironmentSelected=false
      this.takenProfessorSchedules=[]
    }

  }

  getSelectedEnvironment(environment:Environment | null){
    if(environment != null){
      this.environmentSelected=environment
      this.showSelectedEnvironment=true
      // consumir servicio que trae el horario ocupado del amibiente
      this.fillTakenEnvironmentSchedule()
      this.progress.emit(this.sumProgres)
      this.isEnvironmentSelected=false
      this.selectedEnvironment.emit(this.environmentSelected)

    }else{
      this.progress.emit(-this.sumProgres)
      this.isEnvironmentSelected=false
      //limpiar horario ocupado del ambiente
      this.takenEnvironmentSchedules=[]
    }

  }
  getSelectedSchedule(schedule:Schedule | null ){
    if(schedule != null){
      this.scheduleSelected=schedule;
      this.isFormValid.emit(true)
      this.progress.emit(this.sumProgres)
      this.showBtnCreate=true
    }else{
      this.progress.emit(-this.sumProgres)
      this.showBtnCreate=false

    }
  }
  // getIfEnvironmentSelected(value:boolean){
  //   this.isEnvironmentSelected=value
  // }
  getInfo(){
    let scheduleCreated :ScheduleDTO= {id:0, day:'',startingTime:'',endingTime:'',courseId:0,environmentId:0};
    scheduleCreated.day=this.scheduleSelected.day.toUpperCase()
    scheduleCreated.startingTime=this.scheduleSelected.startingTime
    scheduleCreated.endingTime=this.scheduleSelected.endingTime
    scheduleCreated.courseId=this.courseSelected.courseId
    scheduleCreated.environmentId=this.environmentSelected.id
    // console.log("Emitiendo schedule ",scheduleCreated)
    this.scheduleCreated.emit(scheduleCreated)
  }
  fillTakenProfessorSchedule(){

    this.scheduleService.getTakenProfessorSchedule(this.courseSelected.personCode).subscribe((response) =>{
      // console.log("Response de takenprofesor create form" ,response)
        this.takenProfessorSchedules = response as Schedule[]


      });
  }
  fillTakenEnvironmentSchedule(){

    this.scheduleService.getTakenEnvironmentSchedule(this.environmentSelected.id).subscribe((response) =>{
      console.log("Response de taken Envi  " ,response)
      this.takenEnvironmentSchedules = response as Schedule[]
      this.isEnvironmentSelected=true
     });
  }
}
