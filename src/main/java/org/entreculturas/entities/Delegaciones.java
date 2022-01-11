


package org.entreculturas.entities;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "delegaciones")
public class Delegaciones {
    List<Delegacion> delegaciones;
    public List<Delegacion> getDelegaciones() {
        return delegaciones;
    }

    @XmlElement(name = "delegacion")
    public void setDelegaciones(List<Delegacion> delegaciones) {
        this.delegaciones = delegaciones;
    }

    public void add(Delegacion delegacion) {
        if (this.delegaciones == null) {
            this.delegaciones = new ArrayList<>();
        }
        this.delegaciones.add(delegacion);

    }
}
