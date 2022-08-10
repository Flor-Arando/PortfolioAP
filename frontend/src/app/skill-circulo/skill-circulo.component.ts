import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-skill-circulo',
  templateUrl: './skill-circulo.component.html',
  styleUrls: ['./skill-circulo.component.css']
})
export class SkillCirculoComponent implements OnInit {

  nivel : number;
  barra : string;

  constructor() {
    this.nivel = 40;
    this.barra = this.nivel + " " + (100 - this.nivel);
    console.log(this.barra);
  }

  ngOnInit(): void {
    
  }

}
