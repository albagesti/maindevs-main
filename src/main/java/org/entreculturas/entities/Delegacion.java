package org.entreculturas.entities;


import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "delegacion")
public class Delegacion {

    // Identificador de la delegación
    private int id = 0;

    // Nombre de la delegación
    private String nombre;

    // Dirección de la delegación
    private String direccion;

    // Número de teléfono de la delegación
    private String telefono;

    // Dirección de correo electrónico de la delegación
    private String email;

    public Delegacion() {
    }
    public Delegacion(Integer id, String nombre, String direccion, String telefono, String email) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.email = email;
    }


    public Delegacion(int id, String nombre, String direccion, String telefono, String email) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.email = email;

    }


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


    public String getDireccion() {
        return direccion;
    }


    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }


    public String getTelefono() {
        return telefono;
    }


    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }


    public String getEmail() {
        return email;
    }


    public void setEmail(String email) {
        this.email = email;
    }







    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Delegacion{id=").append(this.id);
        sb.append(", nombre=").append(this.nombre);
        sb.append(", direccion=").append(this.direccion);
        sb.append(", telefono=").append(this.telefono);
        sb.append(", email=").append(this.email);
        sb.append('}');
        return sb.toString();
    }
}

