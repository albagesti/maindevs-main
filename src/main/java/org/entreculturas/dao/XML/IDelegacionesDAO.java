package org.entreculturas.dao.XML;

import org.entreculturas.entities.Delegaciones;

import javax.xml.bind.JAXBException;
import java.util.List;

public interface IDelegacionesDAO {
    public void listarDelegaciones() throws JAXBException;
    public void guardarDelegaciones(Delegaciones delegaciones) throws JAXBException;
}