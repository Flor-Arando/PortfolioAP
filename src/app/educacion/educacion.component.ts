import { Component, Input, Output, EventEmitter } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { environment } from '../../environments/environment';

@Component({
  selector: 'app-educacion',
  templateUrl: './educacion.component.html',
  styleUrls: ['./educacion.component.css']
})
export class EducacionComponent {
  @Input() educacion : any[] = [];
  @Input() token : any;
  @Output() mostrarModalEvent = new EventEmitter<string>();
  @Output() cerrarModalEvent = new EventEmitter<string>();
  @Output() borrarEducacionEvent = new EventEmitter<number>();
  @Output() agregarEducacionEvent = new EventEmitter<any>();
  @Output() actualizarEducacionEvent = new EventEmitter<any>();

  anterior : any = {};
  seleccionado : any = {};
  error : string = "";
  api_base_url : string;

  constructor(private http : HttpClient) {
    this.api_base_url = environment.api_base_url;
  }

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
      let encabezado = new HttpHeaders().set('AUTHORIZATION', this.token);
      this.http.delete(this.api_base_url + "/educacion/delete/" + id, { headers : encabezado }).subscribe(
        respuesta => {
          this.borrarEducacionEvent.emit(id);
        }
      );
    }
  }

  guardarEducacion(educacion : any) {
    let encabezado = new HttpHeaders().set('AUTHORIZATION', this.token);
    let url = this.api_base_url + (educacion.id > 0 ? "/educacion/update/" + educacion.id : "/educacion/add");
    let solicitud = educacion.id > 0 ? this.http.put(url, educacion, { headers : encabezado }) : this.http.post(url, educacion, { headers : encabezado });

    solicitud.subscribe(
      respuesta => {
        this.error = "";
        this.cerrarModalEvent.emit("modal_educacion");
        this.mostrarModalEvent.emit("modal_ok");

        // Es edicion
        if (educacion.id == 0) {
          educacion.id = respuesta;
          this.agregarEducacionEvent.emit(educacion);
        } else {
          this.actualizarEducacionEvent.emit();
        }
      },
      error => {
        this.error = error.error.message || error.error;
      }
    );
  }
}
