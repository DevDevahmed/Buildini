import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { KeycloakService } from '../../../services/keycloak/keycloak.service';

@Component({
  selector: 'app-side-login',
  templateUrl: './side-login.component.html',
})
export class AppSideLoginComponent implements OnInit {
  
  constructor(
    private ss: KeycloakService
  ) {
  }

  async ngOnInit(): Promise<void> {
    await this.ss.init();
    await this.ss.login();
  }
}