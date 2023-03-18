import { Component } from '@angular/core';
import { Professor } from 'src/app/models/professor.model';

@Component({
  selector: 'app-schedule-professor-detail',
  templateUrl: './schedule-professor-detail.component.html',
  styleUrls: ['./schedule-professor-detail.component.scss']
})
export class ScheduleProfessorDetailComponent {

  showScheduleView:boolean = false;// no mostrar el horario hasta que se haya seleccionado un profesor
  profesor!:Professor;
  // TODO all

  getSelectedProfessor(profesor:Professor |null){
    //obtener al profesor seleccionado que le llega como parametro
    if(profesor != null){
      this.profesor=profesor;
      this.showScheduleView=true;
      console.log("el ambiente seleccionado ",this.profesor)
    }else{
      this.showScheduleView = false;
    }
  }
}
