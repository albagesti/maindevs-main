package org.entreculturas.entities;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "socio")
public class Socio<T> {
    private Integer idSocio;
    private String nombre;
    private String DNI;
    private String TipoCuota;
    private Integer idProyecto = 0;

    public Socio(){}

    public Socio(
            String nombre,
            String DNI,
            Integer idSocio,
            String TipoCuota,
            int idProyecto
    ){
        this.nombre=nombre;
        this.DNI=DNI;
        this.idSocio=idSocio;
        this.TipoCuota=TipoCuota;
        this.idProyecto = idProyecto;

    }

    @XmlElement(name ="idSocio")
    public Integer getIdSocio() {
        return idSocio;
    }

    public void setId(String id) {
        this.idSocio = idSocio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {this.nombre = nombre;}

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public String getTipoCuota() {
        return TipoCuota;
    }

    public void setTipoCuota(String TipoCuota){
        this.TipoCuota=TipoCuota;
    }

    public Integer getProyectoId() {
        return this.idProyecto;
    }

    public void setProyectoId(Integer idProyecto) {
        this.idProyecto = idProyecto;
    }

}

