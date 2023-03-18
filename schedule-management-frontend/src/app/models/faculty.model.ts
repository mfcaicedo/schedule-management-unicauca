import { Environment } from "./environment.model";
import {Department } from "./department.model"
export interface Faculty{

  facultyId:number;
  facultyName:string;
  departments:Department[];
  environments:Environment[];
}
