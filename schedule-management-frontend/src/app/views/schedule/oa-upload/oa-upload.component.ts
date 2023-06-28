import { Component, OnInit } from '@angular/core';
import { OfertaAcademicaService } from 'src/app/services/oferta-academica/oferta-academica.service'
import { SpinnerService } from 'src/app/services/spinner/spinner.service';
import { NgxFileDropEntry, FileSystemFileEntry, FileSystemDirectoryEntry } from 'ngx-file-drop';
import { ResponseFile } from 'src/app/models/response-file.model';
import { ResponseFileExcel } from 'src/app/models/response-file-excel.model';
import { Program } from 'src/app/models/program.model';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-oa-upload',
  templateUrl: './oa-upload.component.html',
  styleUrls: ['./oa-upload.component.scss'],

})
export class OaUploadComponent implements OnInit {
  imgRta = '';
  public files1: File[] = [];
  responseFile: ResponseFile = {
    statusFile: '',
    contRows: 0,
    contErrorRows: 0,
    contSuccessRows: 0,
    contSaveRows: 0,
    logsType: [],
    logsEmptyFields: [],
    logsGeneric: [],
    logsSuccess: [],
  };
  responseFileExcel: ResponseFileExcel = {
    dataFile: '',
    status: 0,
    modified: false,
    message: '',
    nameFile: '',
  };
  programs: Program[] = [];
  constructor(
    private oaService: OfertaAcademicaService,
    private spinnerService: SpinnerService,
  ) { }

  ngOnInit(): void {
    //obtengo todos los programas
    this.oaService.getAllPrograms().subscribe(response => {
      this.programs = response;
    });
  }
  public dropped(files: NgxFileDropEntry[]) {
    //this.files = files;
    console.log(files)
    for (const droppedFile of files) {

      // Is it a file?
      if (droppedFile.fileEntry.isFile) {
        const fileEntry = droppedFile.fileEntry as FileSystemFileEntry;

        fileEntry.file((file: File) => {
          const nameFile = file.name
          let dateAct = new Date()
          if (file?.type === 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet'
            || file?.type === 'application/vnd.ms-excel') {
            console.log(droppedFile.relativePath, file);
            this.files1.push(file)

            this.oaService.uploadFile(file)
              .subscribe(data => {
                let response = Object.values(data);
                this.responseFile = {
                  statusFile: response[0],
                  contRows: response[1],
                  contErrorRows: response[2],
                  contSuccessRows: response[3],
                  contSaveRows: response[4],
                  logsType: response[5],
                  logsEmptyFields: response[6],
                  logsGeneric: response[7], 
                  logsSuccess: response[8],
                };
                if (this.responseFile.statusFile === 'ERROR') {
                  Swal.fire({
                    title: 'Hay errores en el archivo',
                    html: `
                    <div style="text-align:center">
                    <p>${this.responseFile.logsEmptyFields.length === 0 ? '' :
                        '<h5>Campos vacíos: </h5>' +
                        this.responseFile.logsEmptyFields.join('<br>')
                      }</p>
                      <p>${this.responseFile.logsType.length === 0 ? '' :
                        '<h5>Tipo de dato: </h5>' +
                        this.responseFile.logsType.join('<br>')
                      }</p>
                      <p>${this.responseFile.logsGeneric.length === 0 ? '' :
                        '<h5>Otros errores: </h5>' +
                        this.responseFile.logsGeneric.join('<br>')
                      }</p>
                    </div>
                `,
                    icon: 'error',
                    confirmButtonText: 'Aceptar',
                    confirmButtonColor: '#0A266F',
                  });
                } else {
                  Swal.fire({
                    title: 'Éxito!',
                    text: `Archivo subido correctamente`,
                    icon: 'success',
                    confirmButtonText: 'Aceptar',
                    confirmButtonColor: '#0A266F',
                  });
                }
                //borro el archivo cargado si se subio correctamente
                // element.value = '';
              })
          } else {
            Swal.fire({
              title: 'Error en el archivo ' + nameFile,
              html: `<p>El formato del archivo debe ser tipo Excel (.xlsx).<br>
              <b>Por favor verifica el formato y vuelve a intentarlo</p>`,
              icon: 'error',
              confirmButtonText: 'Aceptar',
              confirmButtonColor: '#0A266F',
            });
            //borro el archivo cargado porque no es de tipo excel
            //element.value = '';
          }

        });
      } else {
        // It was a directory (empty directories are added, otherwise only files)
        const fileEntry = droppedFile.fileEntry as FileSystemDirectoryEntry;
        console.log(droppedFile.relativePath, fileEntry);
      }
    }
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

  /**
   * Metodo que invica al servicio para descargar el template de carga de oferta academica
   * @param programCode Codigo del programa
   */
  downloadTemplate(programCode: string) {
    this.oaService.downloadTemplateService(programCode)
      .subscribe((response) => {
        this.responseFileExcel = response as ResponseFileExcel;
        // Si el archivo fue modificado se descarga la plantilla
        if (this.responseFileExcel.modified === true) {
          this.proccessDownloadFile(this.responseFileExcel);
        } else {
          Swal.fire({
            title: `${this.responseFileExcel.message}`,
            text: `¿Desea descargar la plantilla?`,
            showDenyButton: false,
            showCancelButton: true,
            confirmButtonText: 'Si, descargar',
            confirmButtonColor: '#0A266F',
            cancelButtonText: 'No, cancelar',
          }).then((result) => {
            /* Si acepta se descarga la plantilla vacía */
            if (result.isConfirmed) {
              this.proccessDownloadFile(this.responseFileExcel);
              Swal.fire({
                title: 'Éxito!',
                text: `Plantilla descargada correctamente`,
                icon: 'success',
                timer: 2000,
              });
            }
          })
        }

      });
  }

  /**
   * Método que me permite descargar el archivo de excel
   * @param responseFileExcel información del archivo de excel que llega del servicio
   */
  private proccessDownloadFile(responseFileExcel: ResponseFileExcel) {
    // Decodificar el archivo de Base64 a un array de bytes
    const byteCharacters = atob(this.responseFileExcel.dataFile);
    const byteNumbers = new Array(byteCharacters.length);
    for (let i = 0; i < byteCharacters.length; i++) {
      byteNumbers[i] = byteCharacters.charCodeAt(i);
    }
    const byteArray = new Uint8Array(byteNumbers);
    // Crear un archivo Blob a partir del array de bytes
    const blob = new Blob([byteArray], { type: 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet' });
    // Crear una URL para el archivo Blob
    const url = URL.createObjectURL(blob);
    const downloadLink = document.createElement('a');
    downloadLink.href = url;
    downloadLink.setAttribute('download', this.responseFileExcel.nameFile);
    document.body.appendChild(downloadLink);
    downloadLink.click();
  }

  public fileOver(event: Event) {
    console.log(event);
  }

  public fileLeave(event: Event) {
    console.log(event);
  }
}
