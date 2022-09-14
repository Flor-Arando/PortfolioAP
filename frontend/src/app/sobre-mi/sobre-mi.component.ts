import { Component, Input, Output, EventEmitter } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-sobre-mi',
  templateUrl: './sobre-mi.component.html',
  styleUrls: ['./sobre-mi.component.css']
})
export class SobreMiComponent {
  @Input() persona : any = {};
  @Input() skills : any[] = [];
  @Output() cambiarSeccionEvent = new EventEmitter<string>();
  @Output() mostrarModalEvent = new EventEmitter<string>();
  @Output() cerrarModalEvent = new EventEmitter<string>();
  @Output() borrarSkillEvent = new EventEmitter<string>();

  anterior : object = {};
  error : string = "";

  constructor(private http : HttpClient) { }

  mostrarModalSobreMi() {
    this.anterior = structuredClone(this.persona);
    this.mostrarModalEvent.emit('modal_sobremi');
  }

  cerrarModalSobreMi() {
    this.persona = structuredClone(this.anterior);
    this.error = "";
    this.cerrarModalEvent.emit('modal_sobremi');
  }

  guardarSobreMi(persona : any) {
    this.http.put("http://localhost:8080/persona/update", persona).subscribe(
      a => {
        this.cerrarModalEvent.emit("modal_sobremi");
        this.mostrarModalEvent.emit("modal_ok");
      },
      error => {
        this.error = error.error.message || error.error;
      }
    );
  }

  mostrarModalSkill() {
    //this.anterior = structuredClone(this.persona);
    this.mostrarModalEvent.emit('modal_skill');
  }

  cerrarModalSkill() {
    //this.persona = structuredClone(this.anterior);
    this.error = "";
    this.cerrarModalEvent.emit('modal_skill');
  }

  mostrarModalBorrar(id : string) {
    //this.anterior = structuredClone(this.persona);
    //this.mostrarModalEvent.emit('modal_skill');


    /*if (window.confirm("¿Està seguro de borrar?")) {
      this.http.delete("http://localhost:8080/skill/delete/" + id).subscribe(
        a => {
          console.log("mostrarModalBorrar, response " + id);
          this.borrarSkillEvent.emit(id);
        }
      );
    }*/

    console.log("qwertyuiop " + id);
    this.borrarSkillEvent.emit(id);
  }
  
}
