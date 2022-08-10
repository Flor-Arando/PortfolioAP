/*
DOCUMENTACION
lo minimo indispensable y suficiente https://angular.io/start
configuracion y arranque: https://angular.io/guide/setup-local
crear componentes: https://angular.io/guide/component-overview
requests a apis: https://angular.io/guide/http

COMANDOS ANGULAR CLI
ng new my-app  -->  crea app
ng serve --open  -->  levanta servidor local
ng generate component <component-name>  -->  crea componente
*/

import { Component, OnInit} from '@angular/core';
import { HttpClient } from '@angular/common/http'; // Lo trae desde la declaracion global (?)
import { CommonModule } from '@angular/common'; // Para el ngFor
  
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit { // OnInit es para ejecutar algo automaticamente despues del constructor
  seccion : string;
  persona : any;
  skills : any[]

  constructor(private http: HttpClient) {
    // Todos los atributos deben inicializarse
    this.seccion = "sobre_mi"; // inicio, sobre_mi, educacion, portfolio, experiencia
    this.skills = [];
  }
  
  // OnInit es para ejecutar algo automaticamente despues del constructor
  ngOnInit() {
      this.cargarPersona();
      this.cargarSkills();
  }

  /////////////////////////////////////////// Metodos propios

  cambiarSeccion(seccion : string) {
    this.seccion = seccion;
  }

  cargarPersona() {
    this.http.get<any>("http://localhost:8080/persona-frontend").subscribe(
        resultado => {
          this.persona = resultado;
        }/*,
        error => this.error = error*/
      );
  }

  cargarSkills() {
    this.http.get<any>("http://localhost:8080/skill/list").subscribe(
        resultado => {
          this.skills = resultado;
        }
      );
  }

}
