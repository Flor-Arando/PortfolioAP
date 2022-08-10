import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SkillCirculoComponent } from './skill-circulo.component';

describe('SkillCirculoComponent', () => {
  let component: SkillCirculoComponent;
  let fixture: ComponentFixture<SkillCirculoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SkillCirculoComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SkillCirculoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
