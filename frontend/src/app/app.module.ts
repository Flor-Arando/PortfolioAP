import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http'; // https://angular.io/api/common/http/HttpClientModule

import { AppComponent } from './app.component';
import { SkillCirculoComponent } from './skill-circulo/skill-circulo.component';
//import { SkillCirculoComponent } from './skill-circulo.component';

@NgModule({
  declarations: [
    AppComponent,
    SkillCirculoComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule
  ],
  exports : [
    SkillCirculoComponent
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
