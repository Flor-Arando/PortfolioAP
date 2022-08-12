package com.grupo.backend.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Experiencia {
    
    @Id
    private int id;
    private String empresa;
    private String puesto;
    private String periodo;
    private String descripcion;

    public String getEmpresa() {
        return empresa;
    }
    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }
    
    public String getPuesto() {
        return puesto;
    }
    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }
    public String getPeriodo() {
        return periodo;
    }
    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }
    
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
