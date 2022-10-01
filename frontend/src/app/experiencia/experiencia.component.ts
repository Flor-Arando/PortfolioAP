import { Component, Input, Output, EventEmitter } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Component({
  selector: 'app-experiencia',
  templateUrl: './experiencia.component.html',
  styleUrls: ['./experiencia.component.css']
})
export class ExperienciaComponent {
  @Input() experiencia : any[] = [];
  @Input() token : any;
  @Output() mostrarModalEvent = new EventEmitter<string>();
  @Output() cerrarModalEvent = new EventEmitter<string>();
  @Output() borrarExperienciaEvent = new EventEmitter<number>();
  @Output() agregarExperienciaEvent = new EventEmitter<any>();
  @Output() actualizarExperienciaEvent = new EventEmitter<any>();

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
      let encabezado = new HttpHeaders().set('AUTHORIZATION', this.token);
      this.http.delete("http://localhost:8080/experiencia/delete/" + id, { headers : encabezado }).subscribe(
        respuesta => {
          this.borrarExperienciaEvent.emit(id);
        }
      );
    }
  }

  guardarExperiencia(experiencia : any) {
    let encabezado = new HttpHeaders().set('AUTHORIZATION', this.token);
    let url = experiencia.id > 0 ? "http://localhost:8080/experiencia/update/" + experiencia.id : "http://localhost:8080/experiencia/add";
    let solicitud = experiencia.id > 0 ? this.http.put(url, experiencia, { headers : encabezado }) : this.http.post(url, experiencia, { headers : encabezado });

    solicitud.subscribe(
      respuesta => {
        this.error = "";
        this.cerrarModalEvent.emit("modal_experiencia");
        this.mostrarModalEvent.emit("modal_ok");

        // Es edicion
        if (experiencia.id == 0) {
          experiencia.id = respuesta;
          this.agregarExperienciaEvent.emit(experiencia);
        } else {
          this.actualizarExperienciaEvent.emit();
        }
      },
      error => {
        this.error = error.error.message || error.error;
      }
    );
  }
}
