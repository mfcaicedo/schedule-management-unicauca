import { Component, OnInit } from '@angular/core';
import { Subject } from 'src/app/models/subject.model';
import { SubjectService } from 'src/app/services/subject/subject.service';

@Component({
  selector: 'app-subject-all',
  templateUrl: './subject-all.component.html',
  styleUrls: ['./subject-all.component.scss']
})
export class SubjectAllComponent implements OnInit {

  columns:string[]=['Código','Nombre','Semestre','Tiempo del bloque', 'Horas semanales'];
  subjects:Subject[]=[];
  programs:string[]=[]
  totalItems:number=1
  totalNumberPage:number=1;
  pageSize:number=0;
  paginadorResource:any
  isTypeSelected:boolean=false
  program_id!: string ;


  constructor(
    private SubjectService: SubjectService
  ){

  }
  ngOnInit(): void {
    //this.resources=this.SubjectService.getAllResources();

    this.SubjectService.getAllSubjectsPage(1,5).subscribe(response =>{
      console.log("Data Subject: ",response)
      this.subjects = response.elements as Subject[]
      this.totalItems = response.pagination.totalNumberElements as number
      this.totalNumberPage=response.pagination.totalNumberPage as number
      this.pageSize=response.pagination.size as number
    })

    this.programs=this.SubjectService.getAllPrograms();


    //this.totalItems=this.SubjectService.getTotalItems()
    this.totalItems=1
  }
  updateTableSubject(program_id:string){
    if(program_id == 'all'){
      this.isTypeSelected=false
    }else{
      this.isTypeSelected=true
      this.program_id=program_id
    }

    this.loadTableSubject([1,5])

  }

  loadTableSubject(args: number[]) {
    let pageSolicitud:number = args[0];
    let pageSize: number = args[1]
      if(!pageSolicitud){
        pageSolicitud = 0;
      }
      if(!pageSize){
        pageSize=10
      }
    if(!this.isTypeSelected){
      this.SubjectService.getAllSubjectsPage(pageSolicitud,pageSize).subscribe((response) =>{
        console.log("Data en load Type: ",response)
        this.subjects = response.elements as Subject[];
        this.totalItems = response.pagination.totalNumberElements as number
        this.paginadorResource=response;
        this.totalNumberPage=response.pagination.totalNumberPage as number
      });
    }else{
      this.SubjectService.getSubjectsByProgramId(this.program_id,pageSolicitud,pageSize).subscribe(response =>{
        console.log("Data en load Type: ",response)
        this.subjects = response.elements as Subject[];
        this.totalItems = response.pagination.totalNumberElements as number
        this.paginadorResource=response;
        this.totalNumberPage=response.pagination.totalNumberPage as number
      })
    }

  }
}
