/*import { inject } from '@angular/core';
import { Router, CanActivateFn } from '@angular/router';
import { KeycloakService } from '../keycloak/keycloak.service';

export const authGuard: CanActivateFn = () => {
  const keycloakService = inject(KeycloakService);
  const router = inject(Router);

  if (!keycloakService.Keycloak.authenticated) {
    keycloakService.Keycloak.login();
    return false;
  }
  return true;
};
*/