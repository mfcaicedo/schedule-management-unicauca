import { Component, OnInit,Input, Output, EventEmitter, ViewChildren, QueryList, ElementRef } from '@angular/core';
import { timeStamp } from 'console';
import {Course} from 'src/app/models/course.model'
import { Program } from 'src/app/models/program.model';
import {CourseService} from 'src/app/services/course/course.service'

@Component({
  selector: 'app-courses',
  templateUrl: './courses.component.html',
  styleUrls: ['./courses.component.scss']
})
export class CoursesComponent implements OnInit{

  columns:string[]=['Id curso','Grupo','Capacidad','Periodo','Materia','Profesor','Horas por asignar','Seleccionar'];
  courses:Course[]=[]; //todos los cursos
  curso!:Course;  // curso seleccionado
  isCourseSelected:boolean=false;
  isCheckboxDisabled:boolean=false;
  showSelectedCourse:boolean=false;
  isTypeSelected:boolean=false

  totalItems:number=0;
  totalNumberPage:number=1;
  paginadorEnvironment: any;
  pageSize:number=0;

  @Input('selectedProgram')  program!:Program;
  @Input('selectedSemester')  semester!:number;
  @Output() selectedCourse = new EventEmitter<Course| null>()
  @ViewChildren("checkboxes") checkboxes!: QueryList<ElementRef>;
  constructor(
    private courseService: CourseService,
  ){

  }
  ngOnInit(): void {

    // Traer los cursos del programa y semestre seleccionados
    //this.courses= this.courseService.getAllCoursesFromProgramAndSemester(this.program.id,this.semester)
    this.loadTableCourses([1,5])
  }
  onSelectingCourse(course:Course , e:Event){
    // console.log("el curso seleccionado en courses es ",course)
    const x = e.target as HTMLInputElement
    if(x.checked){
      // Seleccionaron el curso
      this.curso=course;
      this.selectedCourse.emit(course)
      this.isCourseSelected=true //ya hay un curso seleccionado esto le da paso a escoger un ambiente
      this.isCheckboxDisabled=true //deshabilitar que peuda seleccionar otros cursos
      this.showSelectedCourse=true;
    }

  }
  changeSelectedCourse(){

    this.isCheckboxDisabled=false
    this.isCourseSelected=false
    this.showSelectedCourse=false
    this.checkboxes.forEach((element) => {
      element.nativeElement.checked = false;
    });
    this.selectedCourse.emit(null)
  }

  loadTableCourses(args: number[]){
    let pageSolicitud:number = args[0];
    let pageSize: number = args[1]
      if(!pageSolicitud){
        pageSolicitud = 0;
      }
      if(!pageSize){
        pageSize=10
      }
      const idPrograma = this.program.programId
      const semestre = this.semester

    if(!this.isTypeSelected){
        this.courseService.getAllCoursesFromProgramAndSemesterPage(pageSolicitud,pageSize,idPrograma,semestre).subscribe((response) =>{

        this.courses = response.elements as Course[]
        this.totalItems=response.pagination.totalNumberElements as number
        this.totalNumberPage=response.pagination.totalNumberPage as number

      });
    }else{
      this.courseService.getAllCoursesWithType(pageSolicitud,pageSize).subscribe(response =>{
        console.log("Data en load Type: ",response)
        this.courses=response.elements as Course[]
        this.totalItems=response.pagination.totalNumberElements as number
        this.totalNumberPage=response.pagination.totalNumberPage as number


      })
    }
  }
}
