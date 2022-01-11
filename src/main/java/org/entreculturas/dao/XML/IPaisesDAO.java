package org.entreculturas.dao.XML;

import org.entreculturas.entities.Paises;

import javax.xml.bind.JAXBException;
import java.util.List;

public interface IPaisesDAO {
    public List<Paises> listarPaises();
    public void guardarPaises(Paises paises) throws JAXBException;
}
