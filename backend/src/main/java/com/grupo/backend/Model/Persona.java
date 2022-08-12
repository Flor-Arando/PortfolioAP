package com.grupo.backend.Model;

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.Id;

//import java.time.format.DateTimeFormatter;

@Entity
public class Persona {
    @Id
    private int id;
    private String usuario;
    private String clave;
    private String nombre;
    private String apellido;
    private String titulo;
    private String descripcion;
    private String correo;
    private String direccion;
    private LocalDate fechaNacimiento;
    private String fotoPerfil;

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

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getFotoPerfil() {
        return fotoPerfil;
    }

    public void setFotoPerfil(String fotoPerfil) {
        this.fotoPerfil = fotoPerfil;
    }

    /*public String getNacimiento() {
        return this.nacimiento.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    public void setNacimiento(LocalDate nacimiento) {
        this.nacimiento = nacimiento;
    }*/
}
