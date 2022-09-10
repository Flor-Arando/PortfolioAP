import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-skill-circulo',
  templateUrl: './skill-circulo.component.html',
  styleUrls: ['./skill-circulo.component.css']
})
export class SkillCirculoComponent {
  @Input() nombre : string = '';
  @Input() nivel : number = 0;

  barra : string;
  radio : number;
  //partes : string[];

  constructor() {
    this.radio = 27;
    this.barra = "";
    //this.partes = [''];
  }

  ngAfterViewChecked() {
    let perimetro = Math.round(this.radio * 2 * 3.14);
    let valor = Math.round(perimetro * (this.nivel / 100));
    this.barra =  valor + " " + (perimetro - valor);
    //this.partes = this.nombre.split(" ");
  }
}
