package org.entreculturas.entityJPA;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Delegaciones {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idDelegacion")
    private int idDelegacion;
    @Basic
    @Column(name = "nombre")
    private String nombre;
    @Basic
    @Column(name = "direccion")
    private String direccion;
    @Basic
    @Column(name = "telefono")
    private String telefono;
    @Basic
    @Column(name = "email")
    private String email;

    public int getIdDelegacion() {
        return idDelegacion;
    }

    public void setIdDelegacion(int idDelegacion) {
        this.idDelegacion = idDelegacion;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Delegaciones that = (Delegaciones) o;
        return idDelegacion == that.idDelegacion && Objects.equals(nombre, that.nombre) && Objects.equals(direccion, that.direccion) && Objects.equals(telefono, that.telefono) && Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idDelegacion, nombre, direccion, telefono, email);
    }
}
