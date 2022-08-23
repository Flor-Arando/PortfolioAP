package com.grupo.backend.Model;


import javax.persistence.Entity;
import javax.persistence.Id;





@Entity

public class Educacion {
    
    @Id
    private int id;
    private String titulo;
    private String descripcion;
    private String desde;
    private String hasta;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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

}