package org.entreculturas.entityJPA;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Socios {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idSocio")
    private int idSocio;
    @Basic
    @Column(name = "nombre")
    private String nombre;
    @Basic
    @Column(name = "DNI")
    private String dni;
    @Basic
    @Column(name = "tipoCuota")
    private String tipoCuota;
    @Basic
    @Column(name = "idProyecto")
    private Integer idProyecto;

    public int getIdSocio() {
        return idSocio;
    }

    public void setIdSocio(int idSocio) {
        this.idSocio = idSocio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getTipoCuota() {
        return tipoCuota;
    }

    public void setTipoCuota(String tipoCuota) {
        this.tipoCuota = tipoCuota;
    }

    public Integer getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(Integer idProyecto) {
        this.idProyecto = idProyecto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Socios socios = (Socios) o;
        return idSocio == socios.idSocio && Objects.equals(nombre, socios.nombre) && Objects.equals(dni, socios.dni) && Objects.equals(tipoCuota, socios.tipoCuota) && Objects.equals(idProyecto, socios.idProyecto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idSocio, nombre, dni, tipoCuota, idProyecto);
    }
}
