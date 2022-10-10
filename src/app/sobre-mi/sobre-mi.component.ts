import { Component, Input, Output, EventEmitter,  OnInit } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { environment } from '../../environments/environment';
import { DragDropModule } from '@angular/cdk/drag-drop';

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
  api_base_url : string;
  orden : any;

  constructor(private http : HttpClient) {
    this.api_base_url = environment.api_base_url;
  }

  ngOnInit() {
    this.orden = {
      nacimiento: this.persona.ordenNacimiento,
      direccion: this.persona.ordenDireccion,
      correo: this.persona.ordenCorreo
    };
  }

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
    
    this.http.put(this.api_base_url + "/persona/update", persona, { headers : encabezado }).subscribe(
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
      this.http.delete(this.api_base_url + "/skill/delete/" + id, { headers : encabezado }).subscribe(
        respuesta => {
          this.borrarSkillEvent.emit(id);
        }
      );
    }
  }

  guardarSkill(skill : any) {
    let encabezado = new HttpHeaders().set('AUTHORIZATION', this.token);
    let url = this.api_base_url + (skill.id > 0 ? "/skill/update/" + skill.id : "/skill/add");
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

  drop(event : any) {
    // Se mueve hacia abajo
    if (event.currentIndex > event.previousIndex) {
      for (const item in this.orden) {
        if (this.orden[item] == event.previousIndex) {
          this.orden[item] = event.currentIndex;
        } else if (this.orden[item] <= event.currentIndex) {
          this.orden[item] -= 1;
        }
      }
    } else if (event.currentIndex < event.previousIndex) { // Se mueve hacia arriba
      for (const item in this.orden) {
        if (this.orden[item] == event.previousIndex) {
          this.orden[item] = event.currentIndex;
        } else if (this.orden[item] >= event.currentIndex) {
          this.orden[item] += 1;
        }
      }
    }

    this.persona.ordenNacimiento = this.orden.nacimiento;
    this.persona.ordenDireccion = this.orden.direccion;
    this.persona.ordenCorreo = this.orden.correo;

    let encabezado = new HttpHeaders().set('AUTHORIZATION', this.token);
    
    this.http.put(this.api_base_url + "/persona/update", this.persona, { headers : encabezado }).subscribe(
      respuesta => {
      }
    );
  }
}
