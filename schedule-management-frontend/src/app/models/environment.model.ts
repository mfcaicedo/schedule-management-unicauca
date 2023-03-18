import { Faculty } from "./faculty.model";
import { Resource } from "./resource.model";

export interface Environment
{
  id:number;
  name: string;
  location:string;
  capacity:number;
  //environmentType:EnvironmentTypeEnumeration ;
  environmentType:string;
  // faculty:Faculty;
  facultyId:string;
  availableResources:Resource[];


}
// private Integer id;
// private String name;
// private String location;
// private Integer capacity;
// private EnvironmentTypeEnumeration environmentType;

// private String facultyId;

// private Set<ResourceDTO> availableResources;
export interface EnvironmentDTO extends Omit<Environment,'availableResources'>
{

}

export interface EnvironmentTypeEnumeration{
  type:string;
}


