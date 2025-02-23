import { Injectable } from '@angular/core';
import Keycloak from 'keycloak-js';
import { UserProfile } from './user-profile';

@Injectable({
  providedIn: 'root'
})
export class KeycloakService {

  private _keycloak : Keycloak | undefined;
  private _profile : UserProfile | undefined;
  get profile():UserProfile |undefined {
    return this._profile
  }
  get Keycloak(){
    if (!this._keycloak){
      this._keycloak = new Keycloak({
        url : 'http://localhost:9090',
        realm :'tensai',
        clientId : 'User_API'
      });
    }
    return this._keycloak
  }

  constructor() { }

  async init(){
    console.log("user authentification")
    const authenticated = await this.Keycloak.init({
      onLoad:'login-required'
    });
    if (authenticated){
      this._profile = (await this.Keycloak.loadUserProfile()) as UserProfile;
      this._profile.token = this.Keycloak.token || '';
    }

  }
  login() {
    return this.Keycloak.login();
  }

  logout() {
    // this.keycloak.accountManagement();
    return this.Keycloak.logout({redirectUri: 'http://localhost:4200'});
  }
  
}
