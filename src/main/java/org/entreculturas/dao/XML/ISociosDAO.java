package org.entreculturas.dao.XML;
import org.entreculturas.entities.Socios;

import javax.xml.bind.JAXBException;
import java.util.List;

public interface ISociosDAO {
    public List<Socios> listarSocios();
    public void guardarSocios(Socios socios) throws JAXBException;
}
