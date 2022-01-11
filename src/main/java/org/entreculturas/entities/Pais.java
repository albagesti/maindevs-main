package org.entreculturas.entities;

import com.sun.istack.Nullable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "pais")
public class Pais {
    private Integer idPais;
    String nombre;

    public Pais() {}

    public Pais(Integer idPais, String nombre) {
        this.nombre = nombre;
        this.idPais = idPais;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @XmlElement(name = "id")
    public Integer getId() {
        return idPais;
    }
    public void setId(Integer idPais){
        this.idPais = idPais;
    }
}
