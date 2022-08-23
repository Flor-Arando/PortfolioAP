package com.grupo.backend.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Proyecto {  
    @Id
    private int id;
    private String titulo;
    private String descripcion;
    private String desde;
    private String hasta;
    private String link;
    private String foto;
    

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
    public String getLink() {
        return link;
    }
    public void setLink(String link) {
        this.link = link;
    }
    public String getFoto() {
        return foto;
    }
    public void setFoto(String foto) {
        this.foto = foto;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }



}
