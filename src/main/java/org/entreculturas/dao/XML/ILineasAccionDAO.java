package org.entreculturas.dao.XML;

import org.entreculturas.entities.LineasAccion;

import javax.xml.bind.JAXBException;
import java.util.List;

public interface ILineasAccionDAO {
    public List<LineasAccion> listarLineasAccion();
    public void guardarLineasAccion(LineasAccion lineasAccion) throws JAXBException;
}
