import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-inicio',
  templateUrl: './inicio.component.html',
  styleUrls: ['./inicio.component.css']
})
export class InicioComponent implements OnInit {
  @Input() persona : any = {};
  @Output() cambiarSeccionEvent = new EventEmitter<string>();
  @Output() mostrarModalEvent = new EventEmitter<string>();
  @Output() cerrarModalEvent = new EventEmitter<string>();

  anterior : object = {};
  error : string = "";

  constructor(private http : HttpClient) { }

  ngOnInit(): void {
  }

  cambiarSeccion(nuevaSeccion : string) {
    this.cambiarSeccionEvent.emit(nuevaSeccion);
  }

  mostrarModalInicio() {
    this.anterior = structuredClone(this.persona);
    this.mostrarModalEvent.emit('modal_inicio');
  }

  cerrarModalInicio() {
    this.persona = structuredClone(this.anterior);
    this.cerrarModalEvent.emit('modal_inicio');
  }

  guardarInicio(persona : any) {
    this.http.put("http://localhost:8080/persona/update", persona).subscribe(
      a => {
        this.cerrarModalEvent.emit("modal_inicio");
        this.mostrarModalEvent.emit("modal_ok");
      },
      error => {
        this.error = error.error;
      }
    );
  }
}
