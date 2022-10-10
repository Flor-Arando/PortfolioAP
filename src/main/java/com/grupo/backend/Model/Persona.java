package com.grupo.backend.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

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
    private String nacimiento;
    private String fotoPerfil;
    private String banner;
    private int ordenNacimiento;
    private int ordenDireccion;
    private int ordenCorreo;

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

    public String getNacimiento() {
        return nacimiento;
    }

    public void setNacimiento(String nacimiento) {
        this.nacimiento = nacimiento;
    }

    public String getFotoPerfil() {
        return fotoPerfil;
    }

    public void setFotoPerfil(String fotoPerfil) {
        this.fotoPerfil = fotoPerfil;
    }

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    public int getOrdenNacimiento() {
        return ordenNacimiento;
    }

    public void setOrdenNacimiento(int ordenNacimiento) {
        this.ordenNacimiento = ordenNacimiento;
    }

    public int getOrdenDireccion() {
        return ordenDireccion;
    }

    public void setOrdenDireccion(int ordenDireccion) {
        this.ordenDireccion = ordenDireccion;
    }

    public int getOrdenCorreo() {
        return ordenCorreo;
    }

    public void setOrdenCorreo(int ordenCorreo) {
        this.ordenCorreo = ordenCorreo;
    }
}
