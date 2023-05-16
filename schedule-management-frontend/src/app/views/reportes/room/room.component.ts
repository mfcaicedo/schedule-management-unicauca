import { Component, ElementRef, EventEmitter, Input, OnInit, Output, QueryList, Renderer2, SimpleChanges, ViewChildren } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Environment } from 'src/app/models/environment.model';

@Component({
  selector: 'app-room',
  templateUrl: './room.component.html',
  styleUrls: ['./room.component.scss']
})
export class RoomComponent implements OnInit {
  data: any = [];
  environments:Environment[]=[];
  columns:string[]=['Id','Tipo Ambiente','Nombre','Ubicacion','Capacidad','Facultad','Seleccionar'];
  environmentTypes:string[]=[];
  environmentType!: string ;
  isDisabled:boolean=false;

  showSelectedEnvironment:boolean=false;
  ambiente!:Environment;


  isTypeSelected:boolean=false
  totalItems:number=0;
  totalNumberPage:number=1;
  paginadorEnvironment: any;
  pageSize:number=0;
  @Input('continueCreatingSchedule')continueCreatingSchedule:boolean=false
  @Output()selectedEnvironment = new EventEmitter<Environment|null>();
  @Output()isEnvironmentSelected = new EventEmitter<boolean>();
  @ViewChildren("checkboxes") checkboxes!: QueryList<ElementRef>;

  constructor(
    private render2:Renderer2,
    private route : ActivatedRoute
  ){}

  ngOnInit(){
    
    this.lista();
  }

  ngOnChanges(changes: SimpleChanges): void {
    if(changes['continueCreatingSchedule']){
      if(changes['continueCreatingSchedule'].currentValue == true){
        this.changeSelectedEnvironment()
      }

    }

  }
  onSelectingEnvironment(environment:Environment, e:Event){

    const x = e.target as HTMLInputElement
    if(x.checked){
      this.ambiente=environment;
      this.selectedEnvironment.emit(environment)
      this.isEnvironmentSelected.emit(true)
      this.isDisabled=true
      this.showSelectedEnvironment=true;
    }
  }
  changeSelectedEnvironment(){
    this.isDisabled=false
    // this.render2.setAttribute(this.casilla.nativeElement,'checked','false')
    this.checkboxes.forEach((element) => {
      element.nativeElement.checked = false;
    });
    this.selectedEnvironment.emit(null)
    this.isEnvironmentSelected.emit(false)
    this.showSelectedEnvironment=false;
  }

  lista(){
    this.data = [
      {
        id: 1,
        tipo: "salon",
        numero: 532,
        ubicacion: "1 piso",
        capacidad: 50,
        facultad: "civil",
        estado: "libre",
      },
      {
        id: 2,
        tipo: "salon",
        numero: 202,
        ubicacion: "1 piso",
        capacidad: 50,
        facultad: "civil",
        estado: "libre",
      },
      {
        id: 3,
        tipo: "salon",
        numero: 322,
        ubicacion: "1 piso",
        capacidad: 50,
        facultad: "civil",
        estado: "libre",
      },
      {
        id: 4,
        tipo: "salon",
        numero: 109,
        ubicacion: "1 piso",
        capacidad: 50,
        facultad: "civil",
        estado: "libre",
      },
    ]
    this.data.map((re: { checked: boolean; }) => {
      re.checked = false;
    });
    
  }
}
