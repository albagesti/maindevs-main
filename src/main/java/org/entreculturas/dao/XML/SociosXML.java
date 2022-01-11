package org.entreculturas.dao.XML;

import org.entreculturas.dao.XML.ISociosDAO;
import org.entreculturas.entities.Socio;
import org.entreculturas.entities.Socios;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.util.List;

import static org.entreculturas.utils.GlobalVars.path;

public class SociosXML implements ISociosDAO {
    private JAXBContext jaxbContext = null;
    private String nombreFichero = null;

    public SociosXML() throws JAXBException {
        this.jaxbContext = JAXBContext.newInstance(Socio.class);
        this.nombreFichero = path.concat("socios.xml");
    }

    public List<Socios> listarSocios() {return null;}

    public void guardarSocios(Socios socios) {
        Marshaller marshaller = null;
        try {
            marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(socios, new File(nombreFichero));
        } catch (JAXBException ex) {
            ex.printStackTrace();
        }
    }
}
