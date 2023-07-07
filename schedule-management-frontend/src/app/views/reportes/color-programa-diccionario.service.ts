import { Injectable } from '@angular/core';
import { ProgramBD } from 'src/app/models/ProgramBD.model';
import { ProgramService } from 'src/app/services/program/program.service';

@Injectable({
  providedIn: 'root'
})
export class ColorProgramaDiccionarioService {
  listasProgramas:ProgramBD[]=[];   ///lista que contiene los programas desde el back
  diccionario: { [name: string]: string } = {};
  constructor(
    private programService:ProgramService) { 
    this._obtenerTodosLosProgramas();
  }
  /**
   * obtiene los programas con su color para crear el cuadro de convenciones
   */
 _obtenerTodosLosProgramas() {
  /**aqui quede TODO */
    //alert("entro");
    this.programService.getPrograms().subscribe(
      (data: ProgramBD[]) => {
        this.listasProgramas = data as ProgramBD[]; // Asignar los datos emitidos a la variable listafacultades
        //alert(this.listasProgramas[0].name);    
        //alert("longitud Programas:"+this.listasProgramas.length);
      },
      (error) => {
        console.log('Error cargando los programas', error);
      }
    );
  }
  
  _crearDiccionario(){
    for (let i = 0; i < this.listasProgramas.length; i++) {
      const programa = this.listasProgramas[i];
      this.diccionario[programa.name] = programa.color;
    } 
  }
  getDiccionario():ProgramBD[]{
    //alert("entrando servicio nuevo")
    this._crearDiccionario();
    return this.listasProgramas;
  }
}

