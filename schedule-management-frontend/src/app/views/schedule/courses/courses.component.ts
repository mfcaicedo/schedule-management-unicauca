import { Component, OnInit,Input, Output, EventEmitter, ViewChildren, QueryList, ElementRef } from '@angular/core';
import { timeStamp } from 'console';
import {Course} from 'src/app/models/course.model'
import { Program } from 'src/app/models/program.model';
import {CourseService} from 'src/app/services/course/course.service'
import {  Renderer2 } from '@angular/core';
import { interval } from 'rxjs';
@Component({
  selector: 'app-courses',
  templateUrl: './courses.component.html',
  styleUrls: ['./courses.component.scss']
})
export class CoursesComponent implements OnInit{

  columns:string[]=['Curso'];
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
    private renderer: Renderer2,
  ){

  }
  ngOnInit(): void {
    this.selectedCourse.subscribe(() => {
      this.loadTableCourses([1, 5]);
    });

    
  
    interval(1000) // Cambia el valor para ajustar el intervalo de tiempo (en milisegundos)
      .subscribe(() => {
        this.loadTableCourses([1, 5]);
      });
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
  private isDragOverTop = false;
private readonly SCROLL_THRESHOLD = 200; // Umbral de desplazamiento ajustable

onDragEnter(event: DragEvent) {
  event.preventDefault();

  const mouseY = event.clientY;

  this.isDragOverTop = mouseY < this.SCROLL_THRESHOLD;
}

onDragLeave() {
  this.isDragOverTop = false;
}

dragStart(event: DragEvent, course: Course) {
  const courseElement = event.target as HTMLElement;
  const courseOffsetTop = courseElement.offsetTop;

  let pageOffsetTop = 0;
  if (this.isDragOverTop) {
    pageOffsetTop = courseOffsetTop - this.SCROLL_THRESHOLD;
  }

  this.renderer.setProperty(window, 'scrollTo', {
    left: 0,
    top: pageOffsetTop,
    behavior: 'smooth'
  });

  const courseData = `${course.courseId} ${course.subject.subjectCode} ${course.courseGroup}`;
  event.dataTransfer?.setData('application/json', JSON.stringify(courseData));

  this.curso = course;
  this.selectedCourse.emit(course);
  this.isCourseSelected = true;
  this.isCheckboxDisabled = true;
  this.showSelectedCourse = true;

  window.addEventListener('wheel', this.onMouseWheel, { passive: false });
}

onMouseWheel = (event: WheelEvent) => {
  event.preventDefault();

  if (event.deltaY < 0) {
    // Rueda hacia arriba
    window.scrollBy({ top: -100, behavior: 'smooth' }); // Ajusta el valor de desplazamiento según tus necesidades
  } else {
    // Rueda hacia abajo
    window.scrollBy({ top: 100, behavior: 'smooth' }); // Ajusta el valor de desplazamiento según tus necesidades
  }
}

dragEnd() {
  window.removeEventListener('wheel', this.onMouseWheel);
}

  
  
  
  selectCourse(course: Course) {
    const courseData = `${course.courseId} ${course.subject.subjectCode} ${course.courseGroup}`;
    console.log("el curso id es= ", courseData);
    this.curso = course;
    this.selectedCourse.emit(course);
    this.isCourseSelected = true;
    this.isCheckboxDisabled = true;
    this.showSelectedCourse = true;
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
