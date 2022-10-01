import { Component, Input, Output, EventEmitter } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Component({
  selector: 'app-sobre-mi',
  templateUrl: './sobre-mi.component.html',
  styleUrls: ['./sobre-mi.component.css']
})
export class SobreMiComponent {
  @Input() persona : any = {};
  @Input() skills : any[] = [];
  @Input() token : any;
  @Output() mostrarModalEvent = new EventEmitter<string>();
  @Output() cerrarModalEvent = new EventEmitter<string>();
  @Output() borrarSkillEvent = new EventEmitter<number>();
  @Output() agregarSkillEvent = new EventEmitter<any>();

  anterior : any = {};
  seleccionado : any = {};
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
    let encabezado = new HttpHeaders().set('AUTHORIZATION', this.token);
    
    this.http.put("http://localhost:8080/persona/update", persona,{ headers : encabezado }).subscribe(
      respuesta => {
        this.error = "";
        this.cerrarModalEvent.emit("modal_sobremi");
        this.mostrarModalEvent.emit("modal_ok");
      },
      error => {
        this.error = error.error.message || error.error;
      }
    );
  }

  mostrarModalSkill(skill : any) {
    this.seleccionado = skill;
    this.anterior = structuredClone(skill);
    this.mostrarModalEvent.emit('modal_skill');
  }

  cerrarModalSkill() {
    this.seleccionado.nombre = this.anterior.nombre;
    this.seleccionado.nivel = this.anterior.nivel;
    this.error = "";
    this.cerrarModalEvent.emit('modal_skill');
  }

  mostrarModalBorrar(id : number) {
    let encabezado = new HttpHeaders().set('AUTHORIZATION', this.token);

    if (window.confirm("¿Borrar?")) {
      this.http.delete("http://localhost:8080/skill/delete/" + id, { headers : encabezado }).subscribe(
        respuesta => {
          this.borrarSkillEvent.emit(id);
        }
      );
    }
  }

  guardarSkill(skill : any) {
    let encabezado = new HttpHeaders().set('AUTHORIZATION', this.token);
    let url = skill.id > 0 ? "http://localhost:8080/skill/update/" + skill.id : "http://localhost:8080/skill/add";
    let solicitud = skill.id > 0 ? this.http.put(url, skill, { headers : encabezado }) : this.http.post(url, skill,{ headers : encabezado });
  
    solicitud.subscribe(
      respuesta => {
        this.error = "";
        this.cerrarModalEvent.emit("modal_skill");
        this.mostrarModalEvent.emit("modal_ok");

        // Es edicion
        if (skill.id == 0) {
          skill.id = respuesta;
          this.agregarSkillEvent.emit(skill);
        }
      },
      error => {
        this.error = error.error.message || error.error;
      }
    );
  }

}
