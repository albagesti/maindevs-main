package org.entreculturas.dao;

import org.entreculturas.dao.JDBC.*;

import java.sql.Connection;

public class MysqlDAOFactory extends DAOFactory implements IMsqlDAO {
    public ProyectoMYSQL getProyectoDAO() {
        return new ProyectoMYSQL();
    }
    public ProyectoMYSQL getProyectoDAO(Connection transactionalConnection) {
        return new ProyectoMYSQL(transactionalConnection);
    }

    public PaisMYSQL getPaisDAO() {
        return new PaisMYSQL();
    }
    public PaisMYSQL getPaisDAO(Connection transactionalConnection) {
        return new PaisMYSQL(transactionalConnection);
    }

    public SociosMYSQL getSociosDAO(){
        return new SociosMYSQL();
    }

    public LineaMYSQL getLineasAccionDAO() {
        return new LineaMYSQL();
    }

    public DelegacionMYSQL getDelegacionDAO() {
        return new DelegacionMYSQL();
    }
}



