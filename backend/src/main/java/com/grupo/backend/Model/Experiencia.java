package com.grupo.backend.Model;


import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Experiencia { //poner desde y hasta como la base, con get y set
    
    @Id
    private int id;
    private String empresa;
    private String puesto;
    private String descripcion;
    private String desde;
    private String hasta;
   

    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
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
    public String getDesde() {
        return desde;
    }
    public void setDesde(String desde) {
        this.desde = desde;
    }
    public String getHasta() {
        return hasta;
    }
    public void setHasta(String hasta) {
        this.hasta = hasta;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }


   
}
