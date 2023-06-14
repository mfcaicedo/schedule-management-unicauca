import { Component, OnInit } from '@angular/core';
import { OfertaAcademicaService } from 'src/app/services/oferta-academica/oferta-academica.service'
import { SpinnerService } from 'src/app/services/spinner/spinner.service';
import { NgxFileDropEntry, FileSystemFileEntry, FileSystemDirectoryEntry } from 'ngx-file-drop';
import { ResponseFile } from 'src/app/models/response-file.model';
import Swal from 'sweetalert2';
import { academicOferFile } from 'src/app/models/academicOferFIle.model'

@Component({
  selector: 'app-oa-view-files',
  templateUrl: './oa-view-files.component.html',
  styleUrls: ['./oa-view-files.component.scss'],

})
export class OaViewFilesComponent implements OnInit {
  aoFile: academicOferFile[] = [];
  columns: string[] = ['Nombre del archivo', 'Fecha de registro', 'Periodo', 'Id programa'];
  isTypeSelected: boolean = false
  totalItems: number = 0;
  totalNumberPage: number = 1;
  pageSize: number = 0;
  constructor(
    private oaFileService: OfertaAcademicaService,
    private spinnerService: SpinnerService,
  ) { }

  ngOnInit(): void {

    this.oaFileService.findAllFiles(1, 5).subscribe(response => {
      console.log("Data : ", response)
      this.aoFile = response.data.elements as academicOferFile[]
      this.totalItems = response.data.pagination.totalNumberElements as number
      this.totalNumberPage = response.data.pagination.totalNumberPage as number
      this.pageSize = response.data.pagination.size as number

    })
  }
  // aqui viene el numero de pagina solicitada y el tamaño que debe tener
  loadTableAcademicOffer(args: number[]) {
    let pageSolicitud: number = args[0];
    let pageSize: number = args[1]
    if (!pageSolicitud) {
      pageSolicitud = 0;
    }
    if (!pageSize) {
      pageSize = 10
    }
    if (!this.isTypeSelected) {
      this.oaFileService.findAllFiles(pageSolicitud, pageSize).subscribe((response) => {
        this.aoFile = response.data.elements as academicOferFile[]
        this.totalItems = response.data.pagination.totalNumberElements as number
        this.totalNumberPage = response.data.pagination.totalNumberPage as number

      });
    }

  }


  onPageChange(event: any) {

  }
}
