import { Component, OnInit } from '@angular/core';
import { Subject } from 'src/app/models/subject.model';
import { SubjectService } from 'src/app/services/subject/subject.service';
import { Program } from 'src/app/models/program.model';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-subject-all',
  templateUrl: './subject-all.component.html',
  styleUrls: ['./subject-all.component.scss']
})
export class SubjectAllComponent implements OnInit {

  columns:string[]=['Código','Nombre','Semestre','En Bloque', 'Horas semanales','Programa'];
  subjects:Subject[]=[];
  programs: Program[] = []
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

    this.SubjectService.getAllSubjectsPage(1,5).subscribe(response =>{
      console.log("Data Subject: ",response)
      this.subjects = response.elements as Subject[]
      this.totalItems = response.pagination.totalNumberElements as number
      this.totalNumberPage=response.pagination.totalNumberPage as number
      this.pageSize=response.pagination.size as number
    })


    this.SubjectService.getAllPrograms().subscribe(response =>{
      this.programs = response as Program[]
      this.programs.unshift({programId:'TODOS',name:'TODOS',department_id:'',color:''})
    });

    this.totalItems = 1
  }
  updateTableSubject(program_id:string){
    if(program_id == 'TODOS'){
      this.isTypeSelected = false
    }else{
      this.isTypeSelected = true
      this.program_id = program_id
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
        this.subjects = response.elements as Subject[];
        this.totalItems = response.pagination.totalNumberElements as number
        this.paginadorResource = response;
        this.totalNumberPage = response.pagination.totalNumberPage as number
      });
    }else{
      this.SubjectService.getSubjectsByProgramId(this.program_id,pageSolicitud,pageSize).subscribe(response =>{
        this.subjects = response.elements as Subject[];
        console.log("subjest",response);
        console.log("subjest",this.subjects);

        this.totalItems = response.pagination.totalNumberElements as number
        this.paginadorResource = response;
        this.totalNumberPage = response.pagination.totalNumberPage as number
        //Alerta de que no hay datos
        if(this.subjects.length == 0){
          Swal.fire({
            title: 'Filtro por programa',
            text: `No existen asignaturas asociados al programa ${this.program_id}`,
            icon: 'warning',
            showConfirmButton: false,
            timer: 3000,
          });
        }
      })
    }
  }

}
