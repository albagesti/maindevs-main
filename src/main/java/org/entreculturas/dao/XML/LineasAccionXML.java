package org.entreculturas.dao.XML;

import org.entreculturas.dao.XML.ILineasAccionDAO;
import org.entreculturas.entities.LineasAccion;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.util.List;
import static org.entreculturas.utils.GlobalVars.path;

public class LineasAccionXML implements ILineasAccionDAO {

    private JAXBContext jaxbContext = null;
    private String nombreFichero = null;

    public LineasAccionXML() throws JAXBException {
        this.jaxbContext = JAXBContext.newInstance(LineasAccion.class);
        this.nombreFichero = path.concat("lineasAccion.xml");
    }

    @Override
    public List<LineasAccion> listarLineasAccion() {
        return null;
    }

    @Override
    public void guardarLineasAccion(LineasAccion lineasAccion) throws JAXBException {
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(lineasAccion, new File(nombreFichero));
    }
}
