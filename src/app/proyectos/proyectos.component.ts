import { Component, Input, Output, EventEmitter } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { environment } from '../../environments/environment';

@Component({
  selector: 'app-proyectos',
  templateUrl: './proyectos.component.html',
  styleUrls: ['./proyectos.component.css']
})
export class ProyectosComponent {
  @Input() proyecto : any[]= [];
  @Input() token : any;
  @Output() mostrarModalEvent = new EventEmitter<string>();
  @Output() cerrarModalEvent = new EventEmitter<string>();
  @Output() borrarProyectoEvent = new EventEmitter<number>();
  @Output() agregarProyectoEvent = new EventEmitter<any>();
  @Output() actualizarProyectoEvent = new EventEmitter<any>();

  anterior : any = {};
  seleccionado : any = {};
  error : string = "";
  api_base_url : string;

  constructor(private http : HttpClient) {
    this.api_base_url = environment.api_base_url;
  }

  mostrarModalProyecto(proyecto : any) {
    this.seleccionado = proyecto;
    this.anterior = structuredClone(proyecto);
    this.mostrarModalEvent.emit('modal_proyecto');
  }

  cerrarModalProyecto() {
    this.seleccionado.titulo = this.anterior.titulo;
    this.seleccionado.descripcion = this.anterior.descripcion;
    this.seleccionado.desde = this.anterior.desde;
    this.seleccionado.hasta = this.anterior.hasta;
    this.seleccionado.link = this.anterior.link;
    this.seleccionado.foto = this.anterior.foto;
    this.error = "";
    this.cerrarModalEvent.emit('modal_proyecto'); 
  }

  mostrarModalBorrar(id : number) { 
    if (window.confirm("¿Borrar?")) {
      let encabezado = new HttpHeaders().set('AUTHORIZATION', this.token);
      this.http.delete(this.api_base_url + "/proyecto/delete/" + id, { headers : encabezado }).subscribe(
        respuesta => {
          this.borrarProyectoEvent.emit(id);
        }
      );
    }
  }

  guardarProyecto(proyecto : any) {
    let encabezado = new HttpHeaders().set('AUTHORIZATION', this.token);
    let url = this.api_base_url + (proyecto.id > 0 ? "/proyecto/update/" + proyecto.id : "/proyecto/add");
    let solicitud = proyecto.id > 0 ? this.http.put(url, proyecto, { headers : encabezado }) : this.http.post(url, proyecto, { headers : encabezado });

    solicitud.subscribe(
      respuesta => {
        this.error = "";
        this.cerrarModalEvent.emit("modal_proyecto");
        this.mostrarModalEvent.emit("modal_ok");

        // Es edicion
        if (proyecto.id == 0) {
          proyecto.id = respuesta;
          this.agregarProyectoEvent.emit(proyecto);
        } else {
          this.actualizarProyectoEvent.emit();
        }
      },
      error => {
        this.error = error.error.message || error.error;
      }
    );
  }
}
