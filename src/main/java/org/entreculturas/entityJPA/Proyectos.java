package org.entreculturas.entityJPA;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
public class Proyectos {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idProyecto")
    private int idProyecto;
    @Basic
    @Column(name = "nombre")
    private String nombre;
    @Basic
    @Column(name = "fechaInicio")
    private Timestamp fechaInicio;
    @Basic
    @Column(name = "fechaFin")
    private Timestamp fechaFin;
    @Basic
    @Column(name = "financiador")
    private String financiador;
    @Basic
    @Column(name = "financiacion")
    private int financiacion;
    @Basic
    @Column(name = "socioLocal")
    private int socioLocal;
    @Basic
    @Column(name = "idDelegacion")
    private int idDelegacion;
    @Basic
    @Column(name = "idPais")
    private int idPais;

    public int getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(int idProyecto) {
        this.idProyecto = idProyecto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Timestamp getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Timestamp fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Timestamp getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Timestamp fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getFinanciador() {
        return financiador;
    }

    public void setFinanciador(String financiador) {
        this.financiador = financiador;
    }

    public int getFinanciacion() {
        return financiacion;
    }

    public void setFinanciacion(int financiacion) {
        this.financiacion = financiacion;
    }

    public int getSocioLocal() {
        return socioLocal;
    }

    public void setSocioLocal(int socioLocal) {
        this.socioLocal = socioLocal;
    }

    public int getIdDelegacion() {
        return idDelegacion;
    }

    public void setIdDelegacion(int idDelegacion) {
        this.idDelegacion = idDelegacion;
    }

    public int getIdPais() {
        return idPais;
    }

    public void setIdPais(int idPais) {
        this.idPais = idPais;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Proyectos proyectos = (Proyectos) o;
        return idProyecto == proyectos.idProyecto && financiacion == proyectos.financiacion && socioLocal == proyectos.socioLocal && idDelegacion == proyectos.idDelegacion && idPais == proyectos.idPais && Objects.equals(nombre, proyectos.nombre) && Objects.equals(fechaInicio, proyectos.fechaInicio) && Objects.equals(fechaFin, proyectos.fechaFin) && Objects.equals(financiador, proyectos.financiador);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idProyecto, nombre, fechaInicio, fechaFin, financiador, financiacion, socioLocal, idDelegacion, idPais);
    }
}
