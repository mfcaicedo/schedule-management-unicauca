import { Component, OnInit } from '@angular/core';
import { Router, NavigationEnd } from '@angular/router';

import { cilEnvelopeOpen, flagSet } from '@coreui/icons';
import { IconSetService } from '@coreui/icons-angular';
import { iconSubset } from './icons/icon-subset';
import { Title } from '@angular/platform-browser';

import { AuthService } from './services/auth/auth.service';
import { TokenService } from './services/token/token.service';

@Component({
  selector: 'app-root',
  template: '<router-outlet></router-outlet>',
})
export class AppComponent implements OnInit {
  title = 'Gestion Horaria Unicauca';

  constructor(
    private router: Router,
    private titleService: Title,
    private iconSetService: IconSetService,
    private authService: AuthService,
    private tokenService:TokenService
  ) {
    titleService.setTitle(this.title);
    // iconSet singleton
    iconSetService.icons = { ...iconSubset };
    // iconSetService.icons = { cilEnvelopeOpen, ...flagSet };
  }

  ngOnInit(): void {
    // this.router.events.subscribe((evt) => {
    //   if (!(evt instanceof NavigationEnd)) {
    //     return;
    //   }
    // });

    const token = this.tokenService.getToken();
    if(token){
      this.authService.guardarUsuario(token).subscribe()
    }
  }
}
