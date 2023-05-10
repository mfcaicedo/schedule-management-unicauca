import { Component, ElementRef, EventEmitter, Input, OnInit, Output, QueryList, Renderer2, SimpleChanges, ViewChildren } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import{Professor} from 'src/app/models/professor.model'
import { Person } from 'src/app/models/person.model';
import { ScheduleProfessorService } from 'src/app/services/schedule-professor/schedule-professor.service';

@Component({
  selector: 'app-professor-all',
  templateUrl: './professor-all.component.html',
  styleUrls: ['./professor-all.component.scss']
})
export class ProfessorAllComponent implements OnInit{


  professors:Person[]=[];
  professor!:Person;
  isDisabled:boolean=false;
  showSelectedProfessor:boolean=false;
  //emitir el profesor al padre cuando haya checkeado en una casilla
  @Output()selectedProfessor= new EventEmitter<Professor|null>();
  @Output()isProfessorSelected = new EventEmitter<boolean>();
  @ViewChildren("checkboxes") checkboxes!: QueryList<ElementRef>;

  totalItems:number=0;
  totalNumberPage:number=1;
  pageSize:number=0;
  columns:string[]=['Id','Nombre','Seleccionar'];

  constructor(
    private professorService: ScheduleProfessorService,
    private render2:Renderer2,
    private route : ActivatedRoute
  ){}
  ngOnInit(): void{
    this.professorService.getAllProfessorsPage(1,5).subscribe((response)=>{
      console.log("Data : ",response)
      this.professors = response.elements as Person[]
      this.totalItems=response.pagination.totalNumberElements as number
      this.totalNumberPage=response.pagination.totalNumberPage as number
    })

  }

  changeSelectedProfessor(){
    this.isDisabled=false
    // this.render2.setAttribute(this.casilla.nativeElement,'checked','false')
    this.checkboxes.forEach((element) => {
      element.nativeElement.checked = false;
    });
    this.selectedProfessor.emit(null)
    this.isProfessorSelected.emit(false);
    this.showSelectedProfessor=false;
  }


  onSelectingProfessor(profesor:Professor, e:Event){

    const x = e.target as HTMLInputElement
    if(x.checked){
      this.professor = profesor;
      this.selectedProfessor.emit(profesor)
      this.isProfessorSelected.emit(true)
      this.isDisabled=true
      this.showSelectedProfessor=true;
    }
  }

  loadTableProfessorsSchedule(args: number[]){
    let pageSolicitud:number = args[0];
    let pageSize: number = args[1]
      if(!pageSolicitud){
        pageSolicitud = 0;
      }
      if(!pageSize){
        pageSize=10
      }
      console.log("Page profesor ", pageSolicitud, " ",pageSize)
    this.professorService.getAllProfessorsPage(pageSolicitud,pageSize).subscribe((response)=>{
      this.professors = response.elements as Professor[]
      this.totalItems=response.pagination.totalNumberElements as number
      this.totalNumberPage=response.pagination.totalNumberPage as number
    })
  }


}
