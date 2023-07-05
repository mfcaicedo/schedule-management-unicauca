import {
  Component,
  ElementRef,
  EventEmitter,
  Input,
  OnInit,
  Output,
  SimpleChanges,
  ViewChild,
} from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { academicOferFile } from 'src/app/models/academicOferFIle.model';
import { Program } from 'src/app/models/program.model';
import { OfertaAcademicaService } from 'src/app/services/oferta-academica/oferta-academica.service';
import { ProgramService } from 'src/app/services/program/program.service';

@Component({
  selector: 'app-schedule-before-create-form',
  templateUrl: './schedule-before-create-form.component.html',
  styleUrls: ['./schedule-before-create-form.component.scss'],
})
export class ScheduleBeforeCreateFormComponent implements OnInit {
  @Output() progress = new EventEmitter<number>();
  @Output() programa = new EventEmitter<Program>();
  @Output() semestre = new EventEmitter<number>();
  @Output() activateFunction = new EventEmitter();
  @Input('isEdit') isEdit!: boolean;
  @Input('changeSelected') changeSelected: boolean = false;
  @ViewChild('selectRefPrograma') selectRefPrograma!: ElementRef;
  @ViewChild('selectRefSemestre') selectRefSemestre!: ElementRef;
  selectedProgram: Program = {
    programId: '0',
    name: '',
    department_id: '',
    color: '',
  };
  selectedSemester!: number;
  progressMade: number = 0;
  form!: FormGroup;
  sumProgres: number = 50;
  aoFileSinIniciar: academicOferFile[] = [];
  aoFileEnProceso: academicOferFile[] = [];
  aoFilebyState!: academicOferFile;
  auxAoFileSinIniciar!: academicOferFile[];
  archivoId!: number;
  programId!: string;

  programs: Program[] = [];
  semesters: number[] = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10];
  opcionSeleccionado: string = '0';
  verSeleccion: string = '';
  constructor(
    private formBuilder: FormBuilder,
    private programService: ProgramService,
    private ofertaService: OfertaAcademicaService
  ) {}

  ngOnInit() {
    this.buildForm();

    this.ofertaService.findByStateFile('En proceso').subscribe((response) => {
      //console.log("response ",response)

      this.aoFileEnProceso = response.data as academicOferFile[];

      console.log('En Proceso', this.aoFileEnProceso);
    });

    this.ofertaService.findByStateFile('Sin iniciar').subscribe((response) => {
      //console.log("response ",response)

      this.aoFileSinIniciar = response.data as academicOferFile[];
      this.auxAoFileSinIniciar = [...this.aoFileSinIniciar];
      console.log('Sin Iniciar', this.aoFileSinIniciar);
    });
  }

  private buildForm() {
    console.log('entra a build form');
    this.form = this.formBuilder.group({
      program: ['', [Validators.required]],
      semester: ['', [Validators.required]],
    });
  }

  cleanSelect() {
    this.selectRefPrograma.nativeElement.value = '';
    this.selectRefSemestre.nativeElement.value = '';
  }
  onSelectedProgram(event: Event) {
    //TODO traer el numero de semestres de ese programa

    console.log('EVNT  ', event.target);
    this.form.controls['program'].setValue(
      (event.target as HTMLOptionElement).value.toString()
    );

    //Agarro El id del Archivo donde se encuentra el programa seleccionado
    console.log(
      'valor a emitir desde before create ',
      (event.target as HTMLOptionElement).value.toString()
    );
    let newArchivoId = (event.target as HTMLOptionElement).value.toString();
    this.archivoId = Number(newArchivoId);
    if (this.aoFileEnProceso.length > 0) {
      this.guardarArchivoEnProceso(this.archivoId);
    } else {
      this.guardarArchivoSinIniciar(this.archivoId);
      this.ofertaService
        .updateStateFile(this.archivoId, 'En proceso')
        .subscribe((resp) => {
          console.log('Archivo actualizado', resp);
        });

      alert(
        '  El Programa  : ' +
          this.aoFileSinIniciar[0].program.name +
          ' ha sido seleccionado y cambio su estado a " EN PROCESO " puede continuar con el proceso de creacion de horarios'
      );
    }

    this.programService.getProgramById(this.programId).subscribe((resp) => {
      this.selectedProgram = resp;

      console.log('Programa ', this.selectedProgram);
      this.programa.emit(this.selectedProgram);
      this.progress.emit(this.sumProgres);
    });

    this.aoFileSinIniciar.shift();
    console.log(this.aoFileSinIniciar);
  }

  //Crear Funcion para guardar el id del archivo en proceso
  guardarArchivoSinIniciar(archivoId: Number) {
    console.log('Entreo sin iniciar');
    let archivoEncontrado = this.aoFileSinIniciar.find(
      (archivo) => archivo.id == this.archivoId
    );

    if (archivoEncontrado != undefined) {
      this.programId = archivoEncontrado.program.programId.toString();
      console.log(this.programId);
    }
  }

  // Crear Funcion para guardar el id del archivo en proceso
  guardarArchivoEnProceso(archivoId: Number) {
    console.log('Entreo en proceso');
    let archivoEncontrado = this.aoFileEnProceso.find(
      (archivo) => archivo.id == this.archivoId
    );

    if (archivoEncontrado != undefined) {
      this.programId = archivoEncontrado.program.programId.toString();
      console.log(this.programId);
    }
  }

  onSelectedSemester(event: Event) {
    console.log(
      'valor a emitir desde before create ',
      (event.target as HTMLOptionElement).value
    );
    this.form.controls['semester'].setValue(
      (event.target as HTMLOptionElement).value
    );
    this.selectedSemester = Number((event.target as HTMLOptionElement).value);
    this.progress.emit(this.sumProgres);
    this.semestre.emit(this.selectedSemester);
    console.log('Semestre ', this.selectedSemester);
    if (this.selectedSemester == 10) {
      if (
        confirm(
          'El Semestre que usted eligio fue el 10 (Decimo) semestre. Si continua la creacion de horario para este programa finalizara, ¿Desea continuar?'
        )
      ) {
        this.ofertaService
          .updateStateFile(this.archivoId, 'Finalizado')
          .subscribe((resp) => {
            console.log('Archivo actualizado', resp);
          });
      } else {
        setTimeout(() => {
          window.location.reload();
        }, 1000);
      }
    }
  }
}
