import { Component, SimpleChanges } from '@angular/core';

import { navItems,authorityMapping } from './_nav';
import { AuthService } from 'src/app/services/auth/auth.service';
import { map } from 'rxjs';
import { INavData } from '@coreui/angular';
import { AuthorityMap } from 'src/app/models/AuthorityMap.model';

@Component({
  selector: 'app-dashboard',
  templateUrl: './default-layout.component.html',
})
export class DefaultLayoutComponent {

  // public navItems = [];
  navItems: INavData[] = [{
    name: 'Oferta académica',
    url: '/schedule',
    iconComponent: { name: 'cilBook' },

    children: [
      {
        name: 'Subir oferta',
        url: '/schedule/upload-oa',
        iconComponent: { name: 'cilCloudUpload' },

      },
      {
        name: 'Ver archivos',
        url: '/schedule/view-files-oa',
        iconComponent: { name: 'cil-file' }
      }
    ]
  },];

  public perfectScrollbarConfig = {
    suppressScrollX: true,
  };

  constructor(
    private authService : AuthService
  ) {}

  ngOnInit(){
    let userAuthority: string[] = [];
    this.authService.getUserAuthority().subscribe(userAuthority => {
      this.navItems = this.filterNavItems(navItems, authorityMapping, userAuthority);
      console.log(this.navItems);
    });

  }

 filterNavItems(navItems: INavData[], authorityMapping: AuthorityMap[], userAuthority: string[]): INavData[] {
    return navItems.filter(item => {
      const mapping = authorityMapping.find(map => map.url === item.url);

      if (mapping) {
        if (Array.isArray(mapping.authority)) {
          // Si el authority es un array, verifica si alguno de los roles coincide con el userAuthority
          if (mapping.authority.some(role => userAuthority.includes(role))) {
            return true;
          }
        } else {
          // Si el authority es un solo rol, verifica si coincide con el userAuthority
          if (userAuthority.includes(mapping.authority)) {
            return true;
          }
        }
      }

      if (item.children) {
        // Filtra los hijos recursivamente
        item.children = this.filterNavItems(item.children, authorityMapping, userAuthority);
        // Devuelve true si al menos uno de los hijos pasó el filtro
        return item.children.length > 0;
      }

      return false;
    });
  }



}
