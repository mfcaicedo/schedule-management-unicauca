import { Component, OnInit, Input } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { SlicePipe } from '@angular/common';

import { Person } from 'src/app/models/person.model'
import { TeacherService } from 'src/app/services/teacher/teacher.service'
import { HttpClient } from '@angular/common/http';
import { ignoreElements } from 'rxjs';

// import '@coreui/icons/css/coreui-icons.min.css';

@Component({
  selector: 'app-teachers',
  templateUrl: './teacher-detail.component.html',
  styleUrls: ['./teacher-detail.component.scss'],
  providers: [SlicePipe]
})
export class TeachersComponent {

  person: Person[] = [];
  columns: string[] = ['código', 'Nombre completo', 'Departamento'];
  //REVISAR
  environmentTypes: string[] = [];
  environmentType!: string;
  isTypeSelected: boolean = false
  //
  totalItems: number = 0;
  totalNumberPage: number = 1;
  pageSize: number = 0;
  departmentName: String = '';
  personType: string = 'TEACHER';


  @Input('fromResource') fromResource: boolean = false;
  @Input('resourceId') resourceId: number = 0;

  constructor(
    private teacherService: TeacherService,
    private route: ActivatedRoute
  ) { }
  ngOnInit(): void {
    //obteniendo el id de la url debe llamarse igual que en el app-routing
    // this.environments=this.environmentService.getAllEnvironments();
    this.teacherService.getAllPersonByPersonTypePage(this.personType, 1, 5).subscribe(response => {

      //this.teacherService.getAllTeachersPage(1, 5).subscribe(response =>

      console.log("Dataaadsddddda : ", response)
      // this.departmentName = response.elements[0].department.departmentName;
      console.log("la otroa linea ");
      // console.log("depa name: ", this.departmentName)
      this.person = response.elements as Person[] //esta estaba cuando llegué 
      this.person = response.data.elements as Person[] //esta fue la solucion que encontré
      console.log("que tiene person: ", this.person)
      // console.log("teacheeer", this.teacher)
      this.totalItems = response.pagination.totalNumberElements as number
      this.totalNumberPage = response.pagination.totalNumberPage as number
      this.pageSize = response.pagination.size as number
    })
  }

  // //REVISAR
  updateTableTeachers(type: string) {

    if (type == 'all') {
      this.isTypeSelected = false
    } else {
      this.isTypeSelected = true
      this.environmentType = type
    }
    this.loadTableTeachers([1, 5])
  }
  //

  // aqui viene el numero de pagina solicitada y el tamaño que debe tener
  loadTableTeachers(args: number[]) {
    console.log('entro a load table');
    //this.http.get(`http://localhost:8080/users?page=${page}&size=${this.paginationConfig.itemsPerPage}`)
    //this.http.get(this.endPoint+`?page=${page}&size=${this.itemsPerPage}`)
    let pageSolicitud: number = args[0];
    let pageSize: number = args[1]
    if (!pageSolicitud) {
      pageSolicitud = 0;
      console.log('entro a pageSolicitud');
    }
    if (!pageSize) {
      pageSize = 10
      console.log('entro a pageSize');
    }
    else {
      console.log('entro a else');
      this.teacherService.getAllPersonByPersonTypePage(this.personType, pageSolicitud, pageSize).subscribe((response) => {
        this.person = response.data.elements as Person[]
        console.log("que tiene person : ", this.person)
        this.totalItems = response.data.pagination.totalNumberElements as number
        this.totalNumberPage = response.data.pagination.totalNumberPage as number

      });
    }

  }
  onPageChange(event: any) {

  }
}
