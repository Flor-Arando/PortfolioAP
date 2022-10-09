import { Component, OnInit} from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { environment } from '../environments/environment';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  seccion : string;
  persona : any = {};
  skills : any[] = [];
  proyecto: any[] = [];
  educacion : any[] = [];
  experiencia : any[] = [];
  credenciales : any = {};
  errorLogin : string = "";
  token : any = null;
  api_base_url : string;

  constructor(private http: HttpClient) {
    this.api_base_url = environment.api_base_url;
    this.seccion = "inicio"; // inicio, sobre_mi, educacion, portfolio, 
  }
  
  ngOnInit() {
    this.cargarPersona();
    this.cargarSkills();
    this.cargarProyecto();
    this.cargarEducacion();
    this.cargarExperiencia();
  }

  cambiarSeccion(seccion : string) {
    this.seccion = seccion;
  }

  cargarPersona() {
    this.http.get<any>(this.api_base_url + "/persona").subscribe(
        resultado => {
          this.persona = resultado;
        }
      )  
  }

  cargarSkills() {
    this.http.get<any>(this.api_base_url + "/skill/list").subscribe(
        resultado => {
          this.skills = resultado;
        }
      );
  }

  cargarProyecto() {
    this.http.get<any>(this.api_base_url + "/proyecto/list").subscribe(
        resultado => {
            this.proyecto = resultado;
            this.ordenarProyecto();
        }
    );
  }

  cargarEducacion() {
    this.http.get<any>(this.api_base_url + "/educacion/list").subscribe(
        resultado => {
          this.educacion = resultado;
          this.ordenarEducacion();
        }
    );
  }

  cargarExperiencia() {
    this.http.get<any>(this.api_base_url + "/experiencia/list").subscribe(
        resultado => {
          this.experiencia = resultado;
          this.ordenarExperiencia();
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
    this.ordenarProyecto();
  }

  agregarExperiencia(experiencia : any) {
    this.experiencia.push(experiencia);
    this.ordenarExperiencia();
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

  ordenarProyecto() {
    this.proyecto = this.proyecto.sort(function(a, b) {
      // Ordena descendente
      return a.desde > b.desde ? -1 : 1;
    });
  }

  ordenarExperiencia() {
    this.experiencia = this.experiencia.sort(function(a, b) {
      // Ordena descendente
      return a.desde > b.desde ? -1 : 1;
    });
  }

  login() {
    this.http.post(this.api_base_url + "/login", this.credenciales).subscribe(
      respuesta => {
        this.errorLogin = "";
        this.credenciales = {};
        this.cerrarModal('modal_login');
        this.token = respuesta;
      },
      error => {
        this.errorLogin = error.error.message || error.error;
      }
    );
  }

  logout() {
    this.credenciales = {};
    this.token = null;
  }
}
