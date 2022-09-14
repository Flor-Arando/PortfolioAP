import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http'; // https://angular.io/api/common/http/HttpClientModule

import { AppComponent } from './app.component';
import { SkillCirculoComponent } from './skill-circulo/skill-circulo.component';
import { FormsModule } from '@angular/forms';
import { InicioComponent } from './inicio/inicio.component';
import { SobreMiComponent } from './sobre-mi/sobre-mi.component'; // <-- NgModel lives here

@NgModule({
  declarations: [
    AppComponent,
    SkillCirculoComponent,
    InicioComponent,
    SobreMiComponent,
    
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule
  ],
  exports : [
    SkillCirculoComponent
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
