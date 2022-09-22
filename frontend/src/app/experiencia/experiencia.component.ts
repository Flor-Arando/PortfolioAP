import { Component, Input, Output, EventEmitter } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-experiencia',
  templateUrl: './experiencia.component.html',
  styleUrls: ['./experiencia.component.css']
})
export class ExperienciaComponent {
  
  @Input() experiencia : any[] = [];
  @Output() cambiarSeccionEvent = new EventEmitter<string>();
  @Output() mostrarModalEvent = new EventEmitter<string>();
  @Output() cerrarModalEvent = new EventEmitter<string>();
  @Output() borrarExperienciaEvent = new EventEmitter<number>();
  @Output() agregarExperienciaEvent = new EventEmitter<any>();

  anterior : any = {};
  seleccionado : any = {};
  error : string = "";

  constructor(private http : HttpClient) { }

  mostrarModalExperiencia(experiencia : any) {
    this.seleccionado = experiencia;
    this.anterior = structuredClone(experiencia);
    this.mostrarModalEvent.emit('modal_experiencia');
  }

  cerrarModalExperiencia() {
    this.seleccionado.empresa = this.anterior.empresa;
    this.seleccionado.puesto = this.anterior.puesto;
    this.seleccionado.descripcion = this.anterior.descripcion;
    this.seleccionado.desde = this.anterior.desde;
    this.seleccionado.hasta = this.anterior.hasta;
    this.error = "";
    this.cerrarModalEvent.emit('modal_experiencia');
  }

  mostrarModalBorrar(id : number) {
    if (window.confirm("¿Borrar?")) {
      this.http.delete("http://localhost:8080/experiencia/delete/" + id).subscribe(
        respuesta => {
          this.borrarExperienciaEvent.emit(id);
        }
      );
    }
  }

  guardarExperiencia(experiencia : any) {
    let url = experiencia.id > 0 ? "http://localhost:8080/experiencia/update/" + experiencia.id : "http://localhost:8080/experiencia/add";
    let solicitud = experiencia.id > 0 ? this.http.put(url, experiencia) : this.http.post(url, experiencia);

    solicitud.subscribe(
      respuesta => {
        this.error = "";
        this.cerrarModalEvent.emit("modal_experiencia");
        this.mostrarModalEvent.emit("modal_ok");

        // Es edicion
        if (experiencia.id == 0) {
          experiencia.id = respuesta;
          this.agregarExperienciaEvent.emit(experiencia);
        }
      },
      error => {
        this.error = error.error.message || error.error;
      }
    );
  }
}
