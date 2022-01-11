package org.entreculturas.entities;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "lineaAccion")
public class LineaAccion {
    private Integer idLinea;
    private String nombre;
    private Integer idLineaPadre;
    private Integer idProyecto;

    public LineaAccion() {}

    public LineaAccion(Integer idLinea, String nombre, Integer idLineaPadre, int idProyecto) {
        this.idLinea = idLinea;
        this.nombre = nombre;
        this.idLineaPadre = idLineaPadre;
        this.idProyecto = idProyecto;
    }

    @XmlElement(name = "id")
    public int getId() {
        return idLinea;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getLineaPadreId() {
        if(this.idLineaPadre == null) return 0;
        return this.idLineaPadre;
    }

    public void setLineaPadreId(Integer idLineaPadre) {
        this.idLineaPadre = idLineaPadre;
    }

    public Integer getProyectoId() {
        return this.idProyecto;
    }

    public void setProyectoId(Integer idProyecto) {
        this.idProyecto = idProyecto;
    }

    public Integer getIdLinea() {
        return idLinea;
    }

    public void setIdLinea(Integer idLinea) {
        this.idLinea = idLinea;
    }
}
