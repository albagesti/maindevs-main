package org.entreculturas.dao;

public class DAOFactory {

    // Tipos de DAO soportados por la factoría
    public static final int XML = 1;
    public static final int MYSQL = 2;

    private <T> T getMysqlDAO(){
        return (T) new MysqlDAOFactory();
    }

    private <T> T getXmlDAO(){
        return (T) new XmlDAOFactory();
    }

    public <T> T getDAOFactory(int tipo) {
        if(tipo == MYSQL){
            return (T) this.getMysqlDAO();
        }
        return (T) this.getMysqlDAO();
    }
}