package org.entreculturas.dao.XML;

import org.entreculturas.dao.XML.IDelegacionesDAO;
import org.entreculturas.entities.Delegaciones;

import javax.xml.bind.*;

import java.io.File;

import static org.entreculturas.utils.GlobalVars.path;

public class DelegacionesXML implements IDelegacionesDAO {

    private JAXBContext jaxbContext = null;
    private String nombreFichero = null;

    public DelegacionesXML() throws JAXBException {
        this.jaxbContext = JAXBContext.newInstance(Delegaciones.class);
        this.nombreFichero = path.concat("delegaciones.xml");
    }

    @Override
    public void listarDelegaciones() throws JAXBException {

    }

    @Override
    public void guardarDelegaciones(Delegaciones delegaciones) throws JAXBException {
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(delegaciones, new File(nombreFichero));
    }
}
