import { Component, OnInit} from '@angular/core';
import { SpinnerService } from 'src/app/services/spinner/spinner.service';
import { SubjectService } from 'src/app/services/subject/subject.service';
import { NgxFileDropEntry, FileSystemFileEntry, FileSystemDirectoryEntry } from 'ngx-file-drop';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-sub-upload',
  templateUrl: './subject-upload.component.html',
  styleUrls: ['./subject-upload.component.scss'],

})
export class SubjectUploadComponent implements OnInit {
  //public files: NgxFileDropEntry[] = [];
  public files1: File[] = [];

  imgRta = '';
  colaPendientes: string[] = ['OA2022.2-Licenciatura en ingles', 'OA2022.2-Historia', 'OA2022.2-Humanidades'];
  constructor(
    private subService: SubjectService,
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
        this.subService.uploadFile(file)
          .subscribe(rta => {
            //this.imgRta = rta.location;
            Swal.fire({
              title: 'Éxito!',
              text: `Archivo subido correctamente`,
              icon: 'success',
              confirmButtonText: 'Aceptar',
              confirmButtonColor: '#0A266F',
            });
            //borro el archivo cargado si se subio correctamente
            element.value = '';
          })
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
              
              this.subService.uploadFile(file)
                .subscribe(rta => {
                //this.imgRta = rta.location;
                Swal.fire({
                  title: 'Éxito!',
                  text: `Archivo subido correctamente`,
                  icon: 'success',
                  confirmButtonText: 'Aceptar',
                  confirmButtonColor: '#0A266F',
                });
                //borro el archivo cargado si se subio correctamente
                //element.value = '';
          })
          }else {
            Swal.fire({
              title: 'Error en el archivo '+nameFile,
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

  public fileOver(event: Event){
    console.log(event);
  }

  public fileLeave(event: Event){
    console.log(event);
  }
}
