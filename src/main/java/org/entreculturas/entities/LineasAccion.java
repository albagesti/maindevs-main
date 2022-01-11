package org.entreculturas.entities;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "lineasAccion")
public class LineasAccion {
    List<LineaAccion> lineasAccion;
    List<LineaAccion> sublineasAccion;

    @XmlElement(name = "lineaAccion")
    public void setLineasAccion(List<LineaAccion> lineasAccion) {
        this.lineasAccion = lineasAccion;
    }

    public List<LineaAccion> getLineasAccion() {
        return lineasAccion;
    }

    @XmlElement(name = "sublineasAccion")
    public void setSublineasAccion(List<LineaAccion> sublineasAccion) {
        this.sublineasAccion = sublineasAccion;
    }

    public List<LineaAccion> getSublineasAccion() {
        return sublineasAccion;
    }

    public void add(LineaAccion lineaAccion) {
        if (this.lineasAccion == null) {
            this.lineasAccion = new ArrayList<>();
        }

        if(lineaAccion.getLineaPadreId() != 0) {
            this.lineasAccion.add(lineaAccion);
        } else {
            this.sublineasAccion.add(lineaAccion);
        }

    }
}
