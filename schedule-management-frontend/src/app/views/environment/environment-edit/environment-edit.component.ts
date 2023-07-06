import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { switchMap } from 'rxjs';
import { Environment } from 'src/app/models/environment.model';
import { Resource } from 'src/app/models/resource.model';
import { EnvironmentService } from 'src/app/services/environment/environment.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-environment-edit',
  templateUrl: './environment-edit.component.html',
  styleUrls: ['./environment-edit.component.scss']
})
export class EnvironmentEditComponent {

  updateEnvironment!: Environment;
  constructor(
    private route: ActivatedRoute,
    private routeNav: Router,
    private environmentService: EnvironmentService
  ) { }
  show: boolean = false;
  environmentId: number | null = null;
  showEnvironment: string = ' ';
  isSent: boolean = false;
  public visible = false;
  environmentParent: Environment = {
    'id': 0,
    'name': '',
    'location': '',
    'capacity': 0,
    'environmentType': '',
    'facultyId': '',
    'availableResources': []

  }
  ngOnInit(): void {
    //recursos correspondientes a un ambiente
    //this.recursos= this.recursoService.getRecursosByAmbienteId(this.ambiente.idAmbiente);
  }
  ngAfterViewInit(): void {
    //Called after ngAfterContentInit when the component's view has been initialized. Applies to components only.
    //Add 'implements AfterViewInit' to the class.
    //obteniendo el id de la url debe llamarse igual que en el app-routing
    this.route.paramMap.subscribe(
      params => {
        this.environmentId = Number(params.get('environmentId'));
        //Busco el ambiente para cargarlo en el formulario de edicion
        if (this.environmentId != null) {
          this.environmentService.getEnvironmentsByEnvironmentId(this.environmentId).subscribe(response => {
            console.log("Data en edit environment: ", response)
            this.environmentParent = response.data as Environment
          })
        }
      })
  }

  getEnvironmentForm(environment:Environment){
    console.log("entra a get environment form")
    console.log("que tiene: ", environment);
    this.updateEnvironment = environment;
  }
  onUpdateEnvironment() {
    console.log("entra a update environment")
    let status: number = 0
    //llamar a recurso de save environment
    console.log("que va a acutualizar ", this.updateEnvironment);
    const id: number = this.updateEnvironment.id
    // return null;
    this.environmentService.updateEnvironment(this.updateEnvironment,id).subscribe(
      response => {
        console.log("response", response);
        status = response.status
        if (response.status == 200) {
          this.updateEnvironment.id = response.data.id
          Swal.fire('Ambiente actualizado',
            `El ambiente : ${this.updateEnvironment.id.toString()} | ${this.updateEnvironment.facultyId} \nfue actualizado exitosamente`, 'success');

          this.isSent = true //enviar señal al formulario hijo de que puede limpiarse
          this.show = false
          // navego hata lista de ambientes
          // this.router.navigate(['/ambientes']);
          if (status == 200) {
            console.log("entraaa");
            this.callingAddResourcesToEnvironment();
          }
          this.routeNav.navigate(['//environment/all']);
        }

      }
    );
  }
  async callingAddResourcesToEnvironment() {
    const id: number = this.environmentParent.id
    //iterar por cada recurso que se agrego al add Resource llamar al metodo de on Add Resoruce por cada uno
    // this.environmentParent.availableResources.forEach(recurso => this.onAddResourceToEnvironment(recurso.id, id))
    this.onAddResourceToEnvironment(this.environmentParent.availableResources,id);

  }

  onAddResourceToEnvironment(resource: Resource[], environmentId: number) {
    console.log("Legan recursos para agregar ", resource, " envi ", environmentId)
    this.environmentService.addResourceToEnvironment(resource, environmentId).subscribe(
      response => {
        console.log("Data", response)
      }
    )
  }

  showResource() {
    this.show = !this.show
  }

  onAddedResource(resource: Resource) {
    //agregar resource a la lista del environment que ya esta aqui
    this.environmentParent.availableResources.push(resource)
  }

  onremoveResource(resource: Resource) {
    this.environmentParent.availableResources = this.environmentParent.availableResources.filter(item => item.id != resource.id)
  }

  getInfo() {
    console.log("Environment : ", this.environmentParent)
    console.log("recursos ", this.environmentParent.availableResources)
    this.showEnvironment = JSON.stringify(this.environmentParent)
    this.visible = true
  }



  toggleLiveDemo() {
    this.visible = !this.visible;
  }

  handleLiveDemoChange(event: any) {
    this.visible = event;
  }

}
