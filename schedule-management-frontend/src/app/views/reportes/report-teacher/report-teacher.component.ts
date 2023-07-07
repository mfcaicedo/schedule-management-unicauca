import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';

@Component({
  selector: 'app-report-teacher',
  templateUrl: './report-teacher.component.html',
  styleUrls: ['../HojaEstilosReportesSCSS/reportes.component.scss']
})
export class ReportTeacherComponent  implements OnInit {
  title="Reporte Docente";

  @ViewChild('datosPersonalesRadio', { static: false }) datosPersonalesRadio!: ElementRef;
  @ViewChild('departamentoRadio', { static: false }) departamentoRadio!: ElementRef;
  
  banderaActivarComponenteDatosPersonales=false;
  banderaActivarComponenteDepartamento=false;

  ngOnInit(){
    
  }
  constructor(){
    this.aplicarBusqueda();
  }
  aplicarBusqueda(){
    setInterval(() => {
    if (this.datosPersonalesRadio.nativeElement.checked) {
        // Realizar búsqueda por datos personales      
      this.banderaActivarComponenteDatosPersonales=true;
      this.banderaActivarComponenteDepartamento=false;

    } else if (this.departamentoRadio.nativeElement.checked) {
      // Realizar búsqueda por departamento
      this.banderaActivarComponenteDatosPersonales=false;
      this.banderaActivarComponenteDepartamento=true;
    }
  }, 1000);
  }
}
