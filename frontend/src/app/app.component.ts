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
  skills : any[];
  proyecto: string;
  educacion : any[];

  constructor(private http: HttpClient) {
    // Todos los atributos deben inicializarse
    this.seccion = "inicio"; // inicio, sobre_mi, educacion, portfolio, 
    this.persona = {};
    this.skills = [];
    this.proyecto = "";
    //this.experiencia = " ";
    this.educacion = [];
  }
  
  // OnInit es para ejecutar algo automaticamente despues del constructor
  ngOnInit() {
      this.cargarPersona();
      this.cargarSkills();
      this.cargarProyecto();
      this.cargarEducacion();
  }

  /////////////////////////////////////////// Metodos propios

  cambiarSeccion(seccion : string) {
    this.seccion = seccion;
  }

  cargarPersona() {
    this.http.get<any>("http://localhost:8080/persona").subscribe(
        resultado => {
          this.persona = resultado;
        }/*,
        error => this.error = error*/
      )  
  }

  cargarSkills() {
    this.http.get<any>("http://localhost:8080/skill/list").subscribe(
        resultado => {
          this.skills = resultado;
        }
      );
  }

  cargarProyecto() {
    this.http.get<any>("http://localhost:8080/proyecto/list").subscribe(
        resultado => {
            this.proyecto = resultado;
        }
    );
  }

  cargarEducacion() {
    this.http.get<any>("http://localhost:8080/educacion/list").subscribe(
        resultado => {
          this.educacion = resultado;
        }
    );
  }

  guardarSobreMi(persona : any) {
    //console.log("actualizar");
    //console.log(persona);
    this.http.put("http://localhost:8080/persona/update", persona).subscribe(
      a => {}
    );
  }

  guardarInicio(persona : any) {
    this.guardarSobreMi(persona);
  }
}
