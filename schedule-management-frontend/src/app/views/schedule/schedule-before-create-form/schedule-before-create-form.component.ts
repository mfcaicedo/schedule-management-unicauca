import { Component, ElementRef, EventEmitter, Input, OnInit, Output, SimpleChanges, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Program } from 'src/app/models/program.model';
import { ProgramService } from 'src/app/services/program/program.service';

@Component({
  selector: 'app-schedule-before-create-form',
  templateUrl: './schedule-before-create-form.component.html',
  styleUrls: ['./schedule-before-create-form.component.scss']
})
export class ScheduleBeforeCreateFormComponent implements OnInit{

  @Output() progress = new EventEmitter<number>()
  @Output() programa= new EventEmitter<Program>()
  @Output() semestre= new EventEmitter<number>()
  @Output() activateFunction = new EventEmitter();
  @Input('isEdit')isEdit!:boolean;
  @Input('changeSelected') changeSelected:boolean=false;
  @ViewChild('selectRefPrograma') selectRefPrograma !:ElementRef;
  @ViewChild('selectRefSemestre') selectRefSemestre !:ElementRef;
  selectedProgram!:Program;
  selectedSemester!:number;
  progressMade:number=0;
  form!: FormGroup;
  sumProgres:number=50;

  programs:Program[]=[];
  semesters:number[]=[1,2,3,4,5,6,7,8,9,10];
  opcionSeleccionado: string  = '0';
  verSeleccion: string        = '';
  constructor(
    private formBuilder:FormBuilder,
    private programService:ProgramService,

  ){

  }
  ngOnInit(){

    this.buildForm();
    // this.programs= this.programService.getAllPrograms()
    this.programService.getAllPrograms().subscribe(x=>{
      this.programs = x
      console.log("Programas cargados ",this.programs)
    })


  }

  private buildForm(){
    console.log("entra a build form")
    this.form = this.formBuilder.group({
      program:['', [Validators.required]],
      semester:['', [Validators.required]]
    });
  }

  cleanSelect(){
    this.selectRefPrograma.nativeElement.value = '';
    this.selectRefSemestre.nativeElement.value = '';
  }
  onSelectedProgram(event:Event){
    //TODO traer el numero de semestres de ese programa

    console.log("EVNT  ",event.target)
    this.form.controls['program'].setValue((event.target as HTMLOptionElement).value);
    //emitir el programa
    console.log("valor a emitir desde before create ",(event.target as HTMLOptionElement).value )
    // this.selectedProgram=this.programService.getProgramById( (event.target as HTMLOptionElement).value)
    this.programService.getProgramById((event.target as HTMLOptionElement).value).subscribe(resp =>{
      this.selectedProgram= resp
      console.log("Programa ", this.selectedProgram )
    this.programa.emit(this.selectedProgram)
    this.progress.emit(this.sumProgres)
    })

  }
  onSelectedSemester(event:Event){
    console.log("valor a emitir desde before create ",(event.target as HTMLOptionElement).value )
    this.form.controls['semester'].setValue((event.target as HTMLOptionElement).value);
    this.selectedSemester = Number((event.target as HTMLOptionElement).value)
    this.progress.emit(this.sumProgres)
    this.semestre.emit(this.selectedSemester)
  }
}

