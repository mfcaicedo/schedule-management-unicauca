import { Component, ElementRef, EventEmitter, Input, OnInit, Output, QueryList, Renderer2, SimpleChanges, ViewChildren } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Environment } from 'src/app/models/environment.model';
import { Faculty } from 'src/app/models/faculty.model';
import {FacultyService} from 'src/app/services/Faculty/faculty.service'
import { EnvironmentService } from 'src/app/services/environment/environment.service';

@Component({
  selector: 'app-room',
  templateUrl: './room.component.html',
  styleUrls: ['./room.component.scss']
})
export class RoomComponent implements OnInit {
  
  data: any = [];
  isDisabled:boolean=false;
  showSelectedEnvironment:boolean=false;
  ambiente!:Environment;
  
  totalItems:number=0;
  totalNumberPage:number=1;
  paginadorEnvironment: any;
  pageSize:number=0;
  @Input('continueCreatingSchedule')continueCreatingSchedule:boolean=false
  @Output()selectedEnvironment = new EventEmitter<Environment|null>();
  @Output()isEnvironmentSelected = new EventEmitter<boolean>();
  @ViewChildren("checkboxes") checkboxes!: QueryList<ElementRef>;

  //Constante
  readonly valDefecto:string="defecto";///valor por defecto que tiene una opcion del Formulario antes de ser seleccionada
  
  //listas
  environments:Environment[]=[];///TODO: cuando se tenga la consulta aqui guardar los ambientes filtrados
  columns:string[]=['Id','Tipo Ambiente','Nombre','Ubicacion','Capacidad','Facultad','Seleccionar'];
  
  environmentTypes:string[]=[];   ///lista que contiene los tipos de ambientes definidos en el servicio y mostrados en los radios
  environmentType!: string ;      ///indica que no puede ser nulo
  listafacultades:Faculty[]=[];   ///lista que contiene las facultades que se llenan en el desplegable html
  listaEdificios:Environment[]=[];///lista que contiene los edificios para llenar el desplegable en html
  
  //banderas
  isTypeSelected:boolean=false;  ///Variable bandera que indica si se ha seleccionado el tipo
  isFacSelected:boolean=false;   ///Variable bandera que indica si se ha seleccionado la facultad
  isBuildSelected:boolean=false; ///Variable bandera que indica si se ha seleccionado el edificio

  //-----------------------------CARGANDO LOS RECURSOS--------------------------------------------
  constructor(
    private render2:Renderer2,
    private route : ActivatedRoute,
    private  facservice:FacultyService,   ///servicio encargado de traer las facultades
    private envService:EnvironmentService ///servicio encargado de traer los edificios y ambientes filtrados
  ){}

/**
 * Metodo Inicializador que usa los servicios inyectados para llenar facultades
 * y ambientes
 */
  ngOnInit(){
    //llenamos las Facultades desde el servicio de facultad
    this.facservice.getAllFaculty().subscribe(
      (data: Faculty[]) => {
        this.listafacultades = data as Faculty[]; // Asignar los datos emitidos a la variable listafacultades
        //alert(this.listafacultades[0].facultyName);    
      },
      (error) => {
        console.log('Error obteniendo todas las facultades', error);
      }
    );
    //llena tipo Ambientes para cargarse desde el inicio del componente
    this.environmentTypes=this.envService.getAllEnvironmentTypes();
    this.lista();
  }
  //----------------------el siguiente es un metodo de PRUEBAS  se puede BORRAR---------------------------------------
  /**muestra un mensaaje para saber que dato se selecciono */
  alerta(msj:string){
    alert(msj);
  }
 //-----------------------------------LLENA DATOS--------------------------------------
  /**
   * Metodo que obtiene todos los edificios desde el id de una facultad 
   * y los almacena en la variable ${listaEdificios}
   * @param variableIdFac 
   */
  llenarEdificios(variableIdFac:string){
    //llenamos los Edificios lamando un metodo del servicio
    this.envService.getBuildingsByFac(variableIdFac).subscribe(
      (data: Environment[]) => {
        this.listaEdificios = data as Environment[]; // Asignar los datos emitidos a la variable listaEdificios
        //alert(this.listafacultades[0].facultyName);    
        // Otro código que depende de this.listafacultades.length
    
      },
      (error) => {
        console.log('Error obteniendo todos Edificios', error);
      }
    );
  }
  //----------------------METODOS DISPARADOS POR EVENTOS DE SELECCION----------------------------------------
  /**
   * Este metodo se lanza cuando se selecciona la facultad
   * llama al metodo parallena los edificios
   *  y altera la bandera de facultad para informar 
   * que se puede seguir al siguiente paso(seleccionar edificio)
   * @param event  contine el evento que disparo  la funcion
   */
  onSelectedFacultad(event:Event){
    //this.alerta(((event.target as HTMLInputElement).value));
    //comprueba que la facultad seleccionada no sea la agregada por defecto
    if (((event.target as HTMLInputElement).value)!=this.valDefecto){
      this.isFacSelected=true;//cambia el valor de esta variable para que se activen los otros campos
      this.llenarEdificios(((event.target as HTMLInputElement).value));//llena la lista del desplegable
    }else{this.isFacSelected=false;//al ser la por defecto opcion deshabilita
    }
  }
   /**
   * Este metodo se lanza cuando se selecciona el edificio
   *  altera la bandera  edificio seleccionado para informar 
   * que se puede seguir al siguiente paso(seleccionar los ambientes que el edificio Contiene)
   * @param event  contine el evento que disparo  la funcion
   */
  onSelectedEdificio(event:Event){    
    if (((event.target as HTMLInputElement).value)!=this.valDefecto){
      this.isBuildSelected=true;//cambia el valor de esta variable para que se activen los otros campos
       //TODO: falta llenar la tabla y comprobar si hay un tipo seleccionado
    }else{this.isBuildSelected=false;//al ser la por defecto opcion deshabilita
    }
  }
  /**
   * Informa que se selecciono un tipo
   * @param event 
   */
  onSelectedType(event:Event){    
    alert("se selecciono"+(event.target as HTMLInputElement).value);
    //TODO:se debe filtrar por tipo la tabla al lanzarse este evento
  }
//------------------------------OTROS METODOS (NO USADOS ecepto los de la tabla)---------------------------------------------------
 
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
