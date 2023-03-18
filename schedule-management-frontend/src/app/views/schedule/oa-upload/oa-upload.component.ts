import { Component, OnInit } from '@angular/core';
import { OfertaAcademicaService } from 'src/app/services/oferta-academica/oferta-academica.service'
import Swal from 'sweetalert2';
@Component({
  selector: 'app-oa-upload',
  templateUrl: './oa-upload.component.html',
  styleUrls: ['./oa-upload.component.scss']
})
export class OaUploadComponent implements OnInit{
  imgRta= '';
  colaPendientes: string[] = ['OA2022.2-Licenciatura en ingles','OA2022.2-Historia','OA2022.2-Humanidades'];
  constructor(
    private oaService:OfertaAcademicaService
  ) { }

  ngOnInit(): void {

  }

  onUpload(event:Event){
    const element = event.target as HTMLInputElement;
    const file = element.files?.item(0);
    if(file){
      this.oaService.uploadFile(file)
      .subscribe(rta => {
        this.imgRta=rta.location;
        Swal.fire('Archivo creado',
          `Archivo subido correctamente`, 'success');
          // this.router.navigate(['//schedule/detail']);

      })
    }

  }
}
