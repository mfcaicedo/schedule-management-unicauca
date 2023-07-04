import { Component, SimpleChanges } from '@angular/core';

import { navItems,authorityMapping } from './_nav';
import {navItemsAdmin }from './_nav-admin';
import {navItemsManager} from './_nav-manager';
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
  navItems!: INavData[] ;

  public perfectScrollbarConfig = {
    suppressScrollX: true,
  };

  constructor(
    private authService : AuthService
  ) {}

  ngOnInit(){
    // this.navItems = navItems
    this.authService.getUserAuthority().subscribe(userAuthority => {

      if(userAuthority.includes("ROLE_SCHEDULE_MANAGER")){
        this.navItems = navItemsManager;
      }else if (userAuthority.includes("ROLE_ACADEMIC_MANAGER")){
        this.navItems = navItemsAdmin;
      }
     
    });

  }




}
