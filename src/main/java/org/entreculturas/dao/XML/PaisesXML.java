package org.entreculturas.dao.XML;

import org.entreculturas.dao.XML.IPaisesDAO;
import org.entreculturas.entities.Paises;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import java.io.File;
import java.util.List;

import static org.entreculturas.utils.GlobalVars.path;

public class PaisesXML implements IPaisesDAO {

    private JAXBContext jaxbContext = null;
    private String nombreFichero = null;

    public PaisesXML() throws JAXBException {
        this.jaxbContext = JAXBContext.newInstance(Paises.class);
        this.nombreFichero = path.concat("paises.xml");
    }

    @Override
    public List<Paises> listarPaises() {
        return null;
    }

    @Override
    public void guardarPaises(Paises paises) throws JAXBException {
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(paises, new File(nombreFichero));
    }
}
