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

  anterior : object = {};
  error : string = "";

  constructor(private http : HttpClient) { }

  mostrarModalEducacion() {
    this.anterior = structuredClone(this.educacion);
    this.mostrarModalEvent.emit('modal_educacion');
  }

  cerrarModalEducacion() {
    this.educacion = structuredClone(this.anterior);
    this.error = "";
    this.cerrarModalEvent.emit('modal_educacion');
  }

  guardarEducacion(id : string) {
    this.http.put("http://localhost:8080/educacion/update", this.educacion).subscribe(
      a => {
        this.cerrarModalEvent.emit("modal_educacion");
        this.mostrarModalEvent.emit("modal_ok");
      },
      error => {
        this.error = error.error.message || error.error;
      }
    );
  }
}
