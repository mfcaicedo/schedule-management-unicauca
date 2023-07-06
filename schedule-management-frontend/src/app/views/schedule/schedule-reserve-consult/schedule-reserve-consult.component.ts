import { Component } from '@angular/core';
import {EventScheduleDTOResponse} from '../../../models/EventScheduleDTOResponse';
import { ReserveEnvironmentService } from 'src/app/services/schedule-reserve/reserve-environment.service';
import Swal from 'sweetalert2';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
@Component({
  selector: 'app-schedule-reserve-consult',
  templateUrl: './schedule-reserve-consult.component.html',
  styleUrls: ['./schedule-reserve-consult.component.scss']
})
export class ScheduleReserveConsultComponent  {

  columns: string[] = ['id', 'Nombre Evento','Codigo Persona', 'Horario de la Reserva(F.Inicio/F.Fin/Dia/H.inicio/H.Fin)'];
  eventosHorarios: EventScheduleDTOResponse[] = [];
  personCode!:string;
  form!: FormGroup;
 


  constructor(private reserveService:ReserveEnvironmentService,private formBuilder: FormBuilder){
    this.buildForm()

  }


  ngOnInit(): void {
    

  }

  sendPersonCode(){
    this.personCode = this.form.get('personCode')?.value;
    this.reserveService.getReserveScheduleList(this.personCode).subscribe(response=>{
      console.log("response ", response);
      if(response.data == null){
        Swal.fire('Error','El código ingresado no existe','error');
      }else if(response.data == undefined){
        Swal.fire('Error','El encargado no tiene reservas','error');
        
      }else{
        
        //Swal.fire('Exito','El evento y la reserva fueron creados Correctamente','success');
        //console.log(response+"evento creado")
        console.log(response.data +"data")
        this.eventosHorarios=response.data;
      }
    })

  }

  private buildForm() {
    this.form = this.formBuilder.group({
      personCode: ['', [Validators.required]],
      
    });
  }

  get personaCode() {
    return this.form.get("personCode")
  }

  get ispersonaCodeInvalid() {
    return this.personaCode?.touched && this.personaCode?.invalid
  }


}
