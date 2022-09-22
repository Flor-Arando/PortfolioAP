import { Component, Input, Output, EventEmitter } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-educacion',
  templateUrl: './educacion.component.html',
  styleUrls: ['./educacion.component.css']
})
export class EducacionComponent {
  @Input() educacion : any[] = [];
  @Output() cambiarSeccionEvent = new EventEmitter<string>();
  @Output() mostrarModalEvent = new EventEmitter<string>();
  @Output() cerrarModalEvent = new EventEmitter<string>();
  @Output() borrarEducacionEvent = new EventEmitter<number>();
  @Output() agregarEducacionEvent = new EventEmitter<any>();

  anterior : any = {};
  seleccionado : any = {};
  error : string = "";

  constructor(private http : HttpClient) { }

  mostrarModalEducacion(educacion : any) {
    this.seleccionado = educacion;
    this.anterior = structuredClone(educacion);
    this.mostrarModalEvent.emit('modal_educacion');
  }

  cerrarModalEducacion() {
    this.seleccionado.titulo = this.anterior.titulo;
    this.seleccionado.desde = this.anterior.desde;
    this.seleccionado.hasta = this.anterior.hasta;
    this.seleccionado.descripcion = this.anterior.descripcion;
    this.error = "";
    this.cerrarModalEvent.emit('modal_educacion');
  }

  mostrarModalBorrar(id : number) {
    if (window.confirm("¿Borrar?")) {
      this.http.delete("http://localhost:8080/educacion/delete/" + id).subscribe(
        respuesta => {
          this.borrarEducacionEvent.emit(id);
        }
      );
    }
  }

  guardarEducacion(educacion : any) {
    let url = educacion.id > 0 ? "http://localhost:8080/educacion/update/" + educacion.id : "http://localhost:8080/educacion/add";
    let solicitud = educacion.id > 0 ? this.http.put(url, educacion) : this.http.post(url, educacion);

    solicitud.subscribe(
      respuesta => {
        this.error = "";
        this.cerrarModalEvent.emit("modal_educacion");
        this.mostrarModalEvent.emit("modal_ok");

        // Es edicion
        if (educacion.id == 0) {
          educacion.id = respuesta;
          this.agregarEducacionEvent.emit(educacion);
        }
      },
      error => {
        this.error = error.error.message || error.error;
      }
    );
  }

}
