package org.entreculturas.dao;

import org.entreculturas.dao.XML.*;

import javax.xml.bind.JAXBException;

public class XmlDAOFactory extends DAOFactory {
    public IProyectosDAO getProyectoDAO() throws JAXBException {
        return new ProyectosXML();
    }

    public IPaisesDAO getPaisesDAO() throws JAXBException {
        return new PaisesXML();
    }

    public ILineasAccionDAO getLineasAccionDAO() throws JAXBException {
        return new LineasAccionXML();
    }

    public IDelegacionesDAO getDelegacionDAO() throws JAXBException {
        return new DelegacionesXML();
    }

}



