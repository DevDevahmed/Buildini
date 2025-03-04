import { Component, OnInit } from '@angular/core';
import { Router, RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  imports: [RouterOutlet], // Add RouterOutlet to the imports array
  standalone: true, // Mark the component as standalone
})
export class AppComponent implements OnInit {
  title = 'Buldini'; // Add the title property

  constructor(private router: Router) {}

  ngOnInit() {
    // Vérifier si l'URL contient un code d'authentification
    if (window.location.href.includes('code=')) {
      // Supprimer les paramètres en rechargeant la page sans eux
      window.history.replaceState({}, document.title, window.location.origin + '/dashboard');
    }
  }
}