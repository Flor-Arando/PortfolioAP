import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { environment } from '../../environments/environment';

@Component({
  selector: 'app-inicio',
  templateUrl: './inicio.component.html',
  styleUrls: ['./inicio.component.css']
})
export class InicioComponent implements OnInit {
  @Input() persona : any = {};
  @Input() token : any;
  @Output() cambiarSeccionEvent = new EventEmitter<string>();
  @Output() mostrarModalEvent = new EventEmitter<string>();
  @Output() cerrarModalEvent = new EventEmitter<string>();

  anterior : object = {};
  error : string = "";
  api_base_url : string;

  constructor(private http : HttpClient) {
    this.api_base_url = environment.api_base_url;
  }

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
    this.error = "";
    this.cerrarModalEvent.emit('modal_inicio');
  }

  guardarInicio(persona : any) {
    let encabezado = new HttpHeaders().set('AUTHORIZATION', this.token);

    this.http.put(this.api_base_url + "/persona/update", persona, { headers : encabezado }).subscribe(
      a => {
        this.cerrarModalEvent.emit("modal_inicio");
        this.mostrarModalEvent.emit("modal_ok");
      },
      error => {
        this.error = error.error.message || error.error;
      }
    );
  }
}
