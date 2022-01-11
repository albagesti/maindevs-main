package org.entreculturas.entityJPA;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Lineas {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idLinea")
    private int idLinea;
    @Basic
    @Column(name = "nombre")
    private String nombre;
    @Basic
    @Column(name = "lineaPadreId")
    private Integer lineaPadreId;
    @Basic
    @Column(name = "idProyecto")
    private Integer idProyecto;

    public int getIdLinea() {
        return idLinea;
    }

    public void setIdLinea(int idLinea) {
        this.idLinea = idLinea;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getLineaPadreId() {
        return lineaPadreId;
    }

    public void setLineaPadreId(Integer lineaPadreId) {
        this.lineaPadreId = lineaPadreId;
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
        Lineas lineas = (Lineas) o;
        return idLinea == lineas.idLinea && Objects.equals(nombre, lineas.nombre) && Objects.equals(lineaPadreId, lineas.lineaPadreId) && Objects.equals(idProyecto, lineas.idProyecto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idLinea, nombre, lineaPadreId, idProyecto);
    }
}
