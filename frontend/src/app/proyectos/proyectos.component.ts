import { Component, Input, Output, EventEmitter } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-proyectos',
  templateUrl: './proyectos.component.html',
  styleUrls: ['./proyectos.component.css']
})
export class ProyectosComponent {
  @Input() proyecto : any[]= [];
  @Output() cambiarSeccionEvent = new EventEmitter<string>();
  @Output() mostrarModalEvent = new EventEmitter<string>();
  @Output() cerrarModalEvent = new EventEmitter<string>();
  @Output() borrarSkillEvent = new EventEmitter<string>();

  anterior : object = {};
  error : string = "";

  constructor(private http : HttpClient) { }

  mostrarModalProyecto() {
    this.anterior = structuredClone(this.proyecto);
    this.mostrarModalEvent.emit('modal_proyecto');
  }

  cerrarModalProyecto() {
   // this.proyecto = structuredClone(this.anterior);
    this.error = "";
    this.cerrarModalEvent.emit('modal_proyecto');
  }

  guardarProyecto(proyecto : any) {
    this.http.put("http://localhost:8080/proyecto/update", proyecto).subscribe(
      a => {
        this.cerrarModalEvent.emit("modal_proyecto");
        this.mostrarModalEvent.emit("modal_ok");
      },
      error => {
        this.error = error.error.message || error.error;
      }
    );
  }

}