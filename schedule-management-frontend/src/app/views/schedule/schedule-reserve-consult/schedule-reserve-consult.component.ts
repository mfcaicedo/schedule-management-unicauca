import { Component } from '@angular/core';
import {EventScheduleDTOResponse} from '../../../models/EventScheduleDTOResponse';
import { ReserveEnvironmentService } from 'src/app/services/schedule-reserve/reserve-environment.service';
import Swal from 'sweetalert2';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
@Component({
  selector: 'app-schedule-reserve-consult',
  templateUrl: './schedule-reserve-consult.component.html',
  styleUrls: ['./schedule-reserve-consult.component.scss']
})
export class ScheduleReserveConsultComponent  {

  columns: string[] = ['id', 'Nombre Evento','Codigo Persona', 'Horario de la Reserva(F.Inicio/F.Fin/Dia/H.inicio/H.Fin)','Eliminar'];
  eventosHorarios: EventScheduleDTOResponse[] = [];
  personCode!:string;
  form!: FormGroup;
 


  constructor(private reserveService:ReserveEnvironmentService,private formBuilder: FormBuilder,
    private router:Router){
    this.buildForm()

  }

  deleteEvent(eventId:number){
    this.reserveService.deleteEvent(eventId).subscribe(response=>{
      if(response.data == null){
        //this.recargarComponente();
        Swal.fire('Eliminado','El evento fue eliminado','success');
      }
    })
  }
  recargarComponente() {
    const currentUrl = this.router.url;

    this.router.navigateByUrl('/', { skipLocationChange: true }).then(() => {
      this.router.navigate([currentUrl]);
    });
  }


  ngOnInit(): void {
    

  }

  sendPersonCode(){
    this.personCode = this.form.get('personCode')?.value;
    this.reserveService.getReserveScheduleList(this.personCode).subscribe(response=>{
      if(response.data == null){
        Swal.fire('Error','No fue posible cargar la lista de reservas','error');
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
