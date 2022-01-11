package org.entreculturas.entities;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "socio")
public class Socios {
    List<Socio>  socios;
    public List<Socio> getSocios(){return socios;}

    public void setSocios(List<Socio> socios) {
        this.socios = socios;
    }

    @XmlElement(name = "socio")
    public void add(Socio socio){
        if(this.socios == null){
            this.socios = new ArrayList<>();
        }
        this.socios.add(socio);
    }
}
