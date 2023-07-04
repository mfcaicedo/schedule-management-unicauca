import { Component, OnInit, Input } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { SlicePipe } from '@angular/common';
import { Person } from 'src/app/models/person.model'
import { TeacherService } from 'src/app/services/teacher/teacher.service'
import { HttpClient } from '@angular/common/http';
import { ignoreElements } from 'rxjs';
import { Department } from 'src/app/models/department.model';
import Swal from 'sweetalert2';
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
  paginadorResource: any;
  personTypes: string[] = [];
  personType: string = 'TEACHER';
  departmentId!: string;
  isTypeSelected: boolean = false
  departments: Department[] = [];

  totalItems: number = 0;
  totalNumberPage: number = 1;
  pageSize: number = 0;

  @Input('fromResource') fromResource: boolean = false;
  @Input('resourceId') resourceId: number = 0;

  constructor(
    private teacherService: TeacherService,
    private route: ActivatedRoute
  ) { }
  ngOnInit(): void {
    //llamado del servicio para obtener los docentes
    this.teacherService.getAllPersonByPersonTypePage(this.personType, 1, 5).subscribe(response => {
      //guardo los datos de la respuesta en el arreglo de docentes
      this.person = response.data.elements as Person[]
      console.log("verrrrr33333 ", this.person);
      this.totalItems = response.data.pagination.totalNumberElements as number
      this.totalNumberPage = response.data.pagination.totalNumberPage as number
      this.pageSize = response.data.pagination.size as number
    })
    //TODO Obtengo todos los departamentos
    this.teacherService.getAllDepartments().subscribe(
      response => {
        this.departments = response as Department[]
        this.departments.unshift({ departmentId: 'TODOS', departmentName: 'TODOS'})
      }
    );
  }

  /**
   * Metodo que actualiza la tabla de docentes cuando se selecciona un departamento
   * @param departmentId id del departamento seleccionado
   */
  updateTableTeachers(departmentId: string) {

    if (departmentId == 'TODOS') {
      this.isTypeSelected = false
    } else {
      this.isTypeSelected = true
      this.departmentId = departmentId
    }
    this.loadTableTeachers([1, 5])

  }

  /**
   * Metodo que carga la tabla de docentes
   * @param args argumentos para la paginacion
   */
  loadTableTeachers(args: number[]) {

    let pageSolicitud: number = args[0];
    let pageSize: number = args[1]

    if (!pageSolicitud) {
      pageSolicitud = 0;
    }

    if (!pageSize) {
      pageSize = 10
    }

    if (!this.isTypeSelected) {
      this.teacherService.getAllPersonByPersonTypePage(this.personType, pageSolicitud, pageSize).subscribe((response) => {
        this.person = response.data.elements as Person[]
        this.totalItems = response.data.pagination.totalNumberElements as number
        this.totalNumberPage = response.data.pagination.totalNumberPage as number
        this.paginadorResource = response;
      });
    } else {

      this.teacherService.findAllByDepartmetName(this.departmentId, this.personType, pageSolicitud, pageSize).subscribe((response) => {
        this.person = response.data.elements as Person[]
        console.log("persona ", response);
        console.log("persona ", this.person);
        this.totalItems = response.data.pagination.totalNumberElements as number
        this.totalNumberPage = response.data.pagination.totalNumberPage as number
        this.paginadorResource = response;
        if (this.person.length == 0) {
          Swal.fire({
            title: 'Filtro Departamento ',
            text: `No existe ningún docente asociado al departamento seleccionado`,
            icon: 'warning',
            showConfirmButton: false,
            timer: 3000,
          });
        }
      });
    }

  }
  /**
   * Metodo que se ejecuta cuando se cambia de pagina
   * @param event evento de cambio de pagina
   */
  onPageChange(event: any) {

  }
}
