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
  personTypes: string[] = [];
  // personType!: string;
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
    //llamado del servicio para obtener los docentes
    this.teacherService.getAllPersonByPersonTypePage(this.personType, 1, 5).subscribe(response => {

      //guardo los datos de la respuesta en el arreglo de docentes
      this.person = response.data.elements as Person[]
      this.totalItems = response.pagination.totalNumberElements as number
      this.totalNumberPage = response.pagination.totalNumberPage as number
      this.pageSize = response.pagination.size as number

    })
  }

  //Aquí se debe modificar para poder hacer el filtro por departamento ----> REVISAR
  /**
   * Metodo que actualiza la tabla de docentes cuando se selecciona un departamento
   * @param departmentId id del departamento seleccionado (PUEDES USAR EL ID PARA HACER EL FILTRO O
   *                     PUEDES USAR EL NOMBRE DEL DEPARTAMENTO)--> Lo que te sea mas facil
   */
  updateTableTeachers(departmentId: string) {

    if (departmentId == 'all') { //esta validación es para cuando no se filtra por departamento, lo lo tanto muestra todos los docentes
      this.isTypeSelected = false //Esta variable debe llamarse por ejemplo isDepartmentSelected
    } else {
      this.isTypeSelected = true  //Esto es necesario porque establece que se está haciendo un filtro por departamento
      // this.environmentType = type // dedes asignar el id del departamento o el nombre del departamento aquí
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

    else {

      this.teacherService.getAllPersonByPersonTypePage(this.personType, pageSolicitud, pageSize).subscribe((response) => {
        this.person = response.data.elements as Person[]
        this.totalItems = response.data.pagination.totalNumberElements as number
        this.totalNumberPage = response.data.pagination.totalNumberPage as number

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
