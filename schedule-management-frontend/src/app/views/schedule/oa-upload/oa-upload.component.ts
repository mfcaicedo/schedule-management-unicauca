import { Component, OnInit } from '@angular/core';
import { OfertaAcademicaService } from 'src/app/services/oferta-academica/oferta-academica.service'
import { SpinnerService } from 'src/app/services/spinner/spinner.service';
import Swal from 'sweetalert2';
@Component({
  selector: 'app-oa-upload',
  templateUrl: './oa-upload.component.html',
  styleUrls: ['./oa-upload.component.scss'],

})
export class OaUploadComponent implements OnInit {
  imgRta = '';
  colaPendientes: string[] = ['OA2022.2-Licenciatura en ingles', 'OA2022.2-Historia', 'OA2022.2-Humanidades'];
  constructor(
    private oaService: OfertaAcademicaService,
    private spinnerService: SpinnerService,
  ) { }

  ngOnInit(): void {
  }

  onUpload(event: Event) {
    const element = event.target as HTMLInputElement;
    const file = element.files?.item(0);
    //verificamos que se haya seleccionado un archivo de tipo excel.
    if (file?.type === 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet'
      || file?.type === 'application/vnd.ms-excel') {

      if (file) {
        this.oaService.uploadFile(file)
          .subscribe(rta => {
            this.imgRta = rta.location;
            Swal.fire({
              title: 'Éxito!',
              text: `Archivo subido correctamente`,
              icon: 'success',
              confirmButtonText: 'Aceptar',
              confirmButtonColor: '#0A266F',
            });
            //borro el archivo cargado si se subio correctamente
            element.value = '';
          },
            error => {
              Swal.fire({
                title: 'Error!',
                text: `Error subiendo el archivo`,
                icon: 'error',
                confirmButtonText: 'Aceptar',
                confirmButtonColor: '#0A266F',
                }
              );
              //borro el archivo cargado así no se haya cargado
              element.value = '';
            });

      }
    } else {
      Swal.fire({
        title: 'Error en el archivo!',
        html: `<p>El formato del archivo debe ser tipo Excel (.xlsx).<br>
        <b>Por favor verifica el formato y vuelve a intentarlo</p>`,
        icon: 'error',
        confirmButtonText: 'Aceptar',
        confirmButtonColor: '#0A266F',
      });
      //borro el archivo cargado porque no es de tipo excel
      element.value = '';
    }

  }
}
