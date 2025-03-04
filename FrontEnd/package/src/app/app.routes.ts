import { Routes } from '@angular/router';
import { KeycloakGuard } from './guard/keycloak.guard'; // Import the KeycloakGuard

export const routes: Routes = [
  {
    path: '',
    redirectTo: 'dashboard', // Default redirect to dashboard
    pathMatch: 'full',
  },
  {
    path: 'login',
    loadChildren: () =>
      import('./pages/authentication/authentication.routes').then(
        (m) => m.AuthenticationRoutes
      ),
  },
  {
    path: 'register',
    loadChildren: () =>
      import('./pages/authentication/authentication.routes').then(
        (m) => m.AuthenticationRoutes
      ),
  },
  {
    path: 'activate-account',
    loadChildren: () =>
      import('./pages/authentication/authentication.routes').then(
        (m) => m.AuthenticationRoutes
      ),
  },
  {
    path: 'dashboard',
    loadChildren: () =>
      import('./pages/pages.routes').then((m) => m.PagesRoutes),
    canActivate: [KeycloakGuard], // Protect this route
  },
  {
    path: 'ui-components',
    loadChildren: () =>
      import('./pages/ui-components/ui-components.routes').then(
        (m) => m.UiComponentsRoutes
      ),
    canActivate: [KeycloakGuard], // Protect this route
  },
  {
    path: 'extra',
    loadChildren: () =>
      import('./pages/extra/extra.routes').then((m) => m.ExtraRoutes),
    canActivate: [KeycloakGuard], // Protect this route
  },
  {
    path: '**',
    redirectTo: 'authentication/error', // Wildcard route for 404
  },
];
 /*
      {
        path: 'projects',
        children: [
          { path: '', component: ProjectComponent, canActivate: [KeycloakGuard] }, // List projects
          { path: 'new', component: AddProjectComponent, canActivate: [KeycloakGuard] }, // Create new
          { path: ':id', component: ShowProjectComponent, canActivate: [KeycloakGuard] }, // Show details
          { path: 'edit/:id', component: EditProjectComponent, canActivate: [KeycloakGuard] } // Edit project
        ]
      },
      */