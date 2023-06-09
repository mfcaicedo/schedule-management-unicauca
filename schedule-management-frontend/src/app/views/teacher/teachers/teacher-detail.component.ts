import { Component, OnInit, Input } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { SlicePipe } from '@angular/common';

import { Person } from 'src/app/models/person.model'
import { TeacherService } from 'src/app/services/teacher/teacher.service'
import { HttpClient } from '@angular/common/http';
import { ignoreElements } from 'rxjs';
import { Department } from 'src/app/models/department.model';

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
  paginadorResource:any;
  //REVISAR
  personTypes: string[] = [];
  personType: string = 'TEACHER';
  departmentIds: string[] = [];
  departmentId!: string;
  isTypeSelected: boolean = false

  // personType!: string;
  department: Department[] = [];

  totalItems: number = 0;
  totalNumberPage: number = 1;
  pageSize: number = 0;
  // departmentName: String = '';

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
      this.totalItems = response.data.pagination.totalNumberElements as number
      this.totalNumberPage = response.data.pagination.totalNumberPage as number
      this.pageSize = response.data.pagination.size as number

    })
    //TODO todos  los departamentos
    //this.department = this.teacherService.getDepartmentsName();
  }

  //Aquí se debe modificar para poder hacer el filtro por departamento ----> REVISAR
  /**
   * Metodo que actualiza la tabla de docentes cuando se selecciona un departamento
   *  id del departamento seleccionado (PUEDES USAR EL ID PARA HACER EL FILTRO O
   *                     PUEDES USAR EL NOMBRE DEL DEPARTAMENTO)--> Lo que te sea mas facil
   */
  updateTableTeachers(departmentId: string) {

    if (departmentId == 'all') { //esta validación es para cuando no se filtra por departamento, lo lo tanto muestra todos los docentes
      this.isTypeSelected = false //Esta variable debe llamarse por ejemplo isDepartmentSelected
    } else {
      this.isTypeSelected = true  //Esto es necesario porque establece que se está haciendo un filtro por departamento
      this.departmentId = departmentId // this.environmentType = type // dedes asignar el id del departamento o el nombre del departamento aquí
    }
    this.loadTableTeachers([1, 5]) //este metodo carga la tabla de docentes, se le está pasando los argumentos de paginación
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

      this.teacherService.findAllByDepartmetId(this.departmentId, pageSolicitud, pageSize).subscribe((response) => {
        this.person = response.data.elements as Person[]
        this.totalItems = response.data.pagination.totalNumberElements as number
        this.totalNumberPage = response.data.pagination.totalNumberPage as number
        this.paginadorResource = response;
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
