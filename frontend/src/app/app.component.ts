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
  persona : any = {};
  skills : any[] = [];
  proyecto: any[] = [];
  educacion : any[] = [];
  experiencia : any[] = [];
  

  constructor(private http: HttpClient) {
    // Todos los atributos deben inicializarse
    this.seccion = "inicio"; // inicio, sobre_mi, educacion, portfolio, 
  }
  
  // OnInit es para ejecutar algo automaticamente despues del constructor
  ngOnInit() {
      this.cargarPersona();
      this.cargarSkills();
      this.cargarProyecto();
      this.cargarEducacion();
      this.cargarExperiencia();
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
          this.ordenarEducacion();
        }
    );
  }

  cargarExperiencia() {
    this.http.get<any>("http://localhost:8080/experiencia/list").subscribe(
        resultado => {
          this.experiencia = resultado;
        }
    );
  }

  mostrarModal(id : string) {
    let fondo = document.getElementById("fondo_modal");
    if (fondo) {
      fondo.style.visibility = "visible";
      fondo.style.minHeight = "300%";
    }

    let e = document.getElementById(id);
    if (e) {
      e.style.visibility = "visible";
      e.style.display = "inline";
    }
  }

  cerrarModal(id : string) {
    let fondo = document.getElementById("fondo_modal");
    if (fondo) {
      fondo.style.visibility = "hidden";
      fondo.style.minHeight = "100%";
    }

    let e = document.getElementById(id);
    if (e) {
      e.style.visibility = "hidden";
      e.style.display = "none";
    }
  }

  borrarSkill(id : number) {
    this.skills = this.skills.filter(function(value, index, arr) {
      return value.id != id;
    });
  }
  
  borrarEducacion(id : number) {
    this.educacion = this.educacion.filter(function(value, index, arr) {
      return value.id != id;
    });
  }
   
  borrarProyecto(id : number) {
    this.proyecto = this.proyecto.filter(function(value, index, arr) { 
      return value.id != id;
    });
  }

  agregarSkill(skill : any) {
    this.skills.push(skill);
  }

  agregarEducacion(educacion : any) {
    this.educacion.push(educacion);
    this.ordenarEducacion();    
  }

  agregarProyecto(proyecto : any) {
    this.proyecto.push(proyecto);
  }

  agregarExperiencia(experiencia : any) {
    this.experiencia.push(experiencia);
  }

  borrarExperiencia(id : number) {
    this.experiencia = this.experiencia.filter(function(value, index, arr) { 
      return value.id != id;
    });
  }

  ordenarEducacion() {
    this.educacion = this.educacion.sort(function(a, b) {
      // Ordena descendente
      return a.desde > b.desde ? -1 : 1;
    });
  }
  
}
