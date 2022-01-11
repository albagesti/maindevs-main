package org.entreculturas.dao.XML;

import org.entreculturas.dao.XML.IProyectosDAO;
import org.entreculturas.entities.Proyectos;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.util.List;

import static org.entreculturas.utils.GlobalVars.path;

public class ProyectosXML implements IProyectosDAO {

    private JAXBContext jaxbContext = null;
    private String nombreFichero = null;

    public ProyectosXML() throws JAXBException {
        this.jaxbContext = JAXBContext.newInstance(Proyectos.class);
        this.nombreFichero = path.concat("proyectos.xml");
    }

    @Override
    public List<Proyectos> listarProyectos() {
        return null;
    }

    @Override
    public void guardarProyectos(Proyectos proyectos) throws JAXBException {
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(proyectos, new File(nombreFichero));
    }
}
