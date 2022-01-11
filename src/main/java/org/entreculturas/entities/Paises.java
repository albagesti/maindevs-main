package org.entreculturas.entities;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "paises")
public class Paises {
    List<Pais> paises;

    public List<Pais> getPaises() {
        return paises;
    }

    @XmlElement(name = "pais")
    public void setPaises(List<Pais> paises) {
        this.paises = paises;
    }

    public void add(Pais pais) {
        if (this.paises == null) {
            this.paises = new ArrayList<>();
        }
        this.paises.add(pais);
    }
}
