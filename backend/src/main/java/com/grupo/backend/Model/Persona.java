package com.grupo.backend.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

//import java.time.LocalDate;
//import java.time.format.DateTimeFormatter;

@Entity
public class Persona {
    @Id
    private int id;
    private String usuario;
    private String clave;
    private String nombre;
    private String apellido;
    //private LocalDate nacimiento;

    /*public Persona(int id, String usuario, String clave, String nombre, String apellido//, LocalDate nacimiento/)
    {
        this.id = id;
        this.usuario = usuario;
        this.clave = clave;
        this.nombre = nombre;
        this.apellido = apellido;
        //this.nacimiento = nacimiento;
    }*/

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    /*public String getNacimiento() {
        return this.nacimiento.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    public void setNacimiento(LocalDate nacimiento) {
        this.nacimiento = nacimiento;
    }*/
}
