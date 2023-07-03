import { Component } from '@angular/core';
import { Environment } from 'src/app/models/environment.model';
import {ReportRoom} from 'src/app/models/ReportRoom.model'
import {ReportService} from 'src/app/services/report/report.service'

import * as pdfMake from 'pdfmake/build/pdfmake';
import * as pdfFonts from 'pdfmake/build/vfs_fonts';
import { AuthService } from 'src/app/services/auth/auth.service';
import { User } from 'src/app/models/profile';
(pdfMake as any).vfs = pdfFonts.pdfMake.vfs;
import { Margins } from 'pdfmake/interfaces';

@Component({
  selector: 'app-report-environment',
  templateUrl: './report-environment.component.html',
  styleUrls: ['./report-environment.component.scss']
})
export class ReportEnvironmentComponent {

  ambiente!:Environment | null;
  reportes: ReportRoom[]= [ ]
  columns: string[] = ["Id","Dia", "Hora Inicio", "Hora Fin","Fecha Inicio", "Fecha Fin", "Nombre Ambiente" ," Materia" ,"Programa"]
  user: User | null = null
  currentDate: Date = new Date();
  constructor(
    private reportService: ReportService,
    private authService : AuthService
  ){

  }

  ngOnInit(){
    this.authService.user$.subscribe((user )=> this.user = user )
  }
  getSelectedEnvironment(environment:Environment | null ){
    this.ambiente = environment

    console.log(environment?.id.toString())

    if(environment){
      //consultar reprotes de ese environment
      this.reportService.getReportRoom(environment.id.toString()).subscribe((response)=>{
        console.log(response)
        this.reportes = response
      })
    }
  }

  generatePDF() {
    const reportesString: string = JSON.stringify(this.reportes.map(p => [
      `Id : ${p.id}
      Dia: ${p.day}
      Hora inicio : ${p.startingTime}
      Hora fin : ${p.endingTime}
      Fecha Inicio : ${p.startingDate}
      Fecha Fin: ${p.endingDate}
      Salon : ${p.environmentName}
      Materia : ${p.subjectName}
      Programa : ${p.programName}`


    ]));


    let docDefinition = {
      header: 'REPORTE AMBIENTE',

      content:
        {

        text:`Informacion del reporte
        Universidad del Cauca
        ${this.user?.username} | ${this.reportes.pop()?.environmentName}
        ---------------------------------------------------------------------------
        ---------------------------------------------------------------------------
         ${this.reportes}
        ${reportesString}
       `,
       fontSize: 15,
       color: 'skyblue',
       bold: true
      }




   };

    pdfMake.createPdf(docDefinition).open();


    // let docDefinition = {
    //   content: [
    //     {
    //       text: 'REPORTE AMBIENTE',
    //       fontSize: 16,
    //       alignment: 'center',
    //       color: '#047886'
    //     },
    //     {
    //       text: 'REPORTE',
    //       fontSize: 20,
    //       bold: true,
    //       alignment: 'center',
    //       decoration: 'underline',
    //       color: 'skyblue'
    //     },
    //     {
    //       text: 'Detalles de la persona ',
    //       style: 'sectionHeader'
    //     },
    //     {
    //       columns: [
    //         [
    //           {
    //             text: this.user?.username,
    //             bold:true
    //           },
    //           { text: this.user?.username },
    //           { text: this.currentDate},

    //         ],
    //         [
    //           {
    //             text: `Date: ${new Date().toLocaleString()}`,
    //             alignment: 'right'
    //           },
    //           {
    //             text: `Bill No : ${((Math.random() *1000).toFixed(0))}`,
    //             alignment: 'right'
    //           }
    //         ]
    //       ]
    //     },
    //     {
    //       text: 'Details',
    //       style: 'sectionHeader'
    //     },
    //     {
    //       table: {
    //         headerRows: 1,
    //         widths: ['auto', 'auto', 'auto', 'auto','auto','auto','auto','auto'],
    //         body: [
    //             ["Id","Dia", "Hora Inicio", "Hora Fin","Fecha Inicio", "Fecha Fin", "Nombre Ambiente" ," Materia" ,"Programa"],
    //             ...this.reportes.map(p => ([p.id, p.day, p.startingTime,p.endingTime ,p.startingDate ,p.endingDate ,p.environmentName ,p.subjectName,p.programName])),

    //         ]
    //       }
    //     }
    //   ],
    //   styles: {
    //     sectionHeader: {
    //       bold: true,
    //       decoration: 'underline',
    //       fontSize: 14,
    //       margin: [0, 15,0, 15]
    //     }
    //   }
    // };




    // pdfMake.createPdf(docDefinition).open();
  }
}
