package org.entreculturas.dao.XML;

import org.entreculturas.entities.Proyectos;

import javax.xml.bind.JAXBException;
import java.util.List;

public interface IProyectosDAO {
    public List<Proyectos> listarProyectos();
    public void guardarProyectos(Proyectos proyectos) throws JAXBException;
}
