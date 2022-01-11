package org.entreculturas.entities;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "proyectos")
public class Proyectos {
    List<Proyecto> proyectos;
    public List<Proyecto> getProyectos() {
        return proyectos;
    }

    @XmlElement(name = "proyecto")
    public void setProyectos(List<Proyecto> proyectos) {
        this.proyectos = proyectos;
    }

    public void add(Proyecto proyecto) {
        if (this.proyectos == null) {
            this.proyectos = new ArrayList<>();
        }
        this.proyectos.add(proyecto);

    }
}
