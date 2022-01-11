package org.entreculturas.dao.JDBC;

import org.entreculturas.dao.JDBC.IPaisDAO;
import org.entreculturas.entities.Pais;
import org.entreculturas.jdbc.Connect;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PaisMYSQL extends Connect implements IPaisDAO {
    private static final String SQL_SELECT_ALL = "SELECT idPais, nombre FROM paises";
    private static final String SQL_SELECT = "SELECT idPais, nombre FROM paises WHERE idPais=?";
    private static final String SQL_INSERT = "INSERT INTO paises (nombre) VALUES(?)";
    private static final String SQL_UPDATE = "UPDATE paises SET nombre=? WHERE idPais=?";
    private Connection transactionalConnection;

    public PaisMYSQL() {
    }

    public PaisMYSQL(Connection transactionalConnection) {
        this.transactionalConnection = transactionalConnection;
    }

    @Override
    public int insert(Pais pais) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int idPais = 0;

        try {
            conn = this.transactionalConnection != null ? this.transactionalConnection : Connect.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, pais.getNombre());
            int affectedRows = stmt.executeUpdate();

            if(affectedRows == 0){
                throw new SQLException("No se pudo guardar");
            }

            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                idPais = generatedKeys.getInt(1);
            }
        } catch(SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Connect.close(stmt);
            if( this.transactionalConnection == null) {
                Connect.close(conn);
            }
        }

        return idPais;
    }

    @Override
    public Pais update(Pais pais) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = this.transactionalConnection != null ? this.transactionalConnection : Connect.getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setString(1, pais.getNombre());
            stmt.setInt(2, pais.getId());
            stmt.execute();
        } catch(SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Connect.close(stmt);
            if( this.transactionalConnection == null) {
                Connect.close(conn);
            }
        }

        return pais;
    }

    @Override
    public List<Pais> selectAll() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Pais pais = null;
        List<Pais> paises = new ArrayList<>();

        try {
            conn = this.transactionalConnection != null ? this.transactionalConnection : Connect.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_ALL);
            rs = stmt.executeQuery();
            while(rs.next()){
                int idPais = rs.getInt("idPais");
                String nombre = rs.getString("nombre");
                pais = new Pais(idPais, nombre);
                paises.add(pais);
            }
        } catch(SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Connect.close(rs);
            Connect.close(stmt);
            if( this.transactionalConnection == null) {
                Connect.close(conn);
            }
        }
        return paises;
    }

    @Override
    public Pais selectOne(int uid) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Pais pais = null;

        try {
            conn = this.transactionalConnection != null ? this.transactionalConnection : Connect.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            stmt.setInt(1, uid);
            rs = stmt.executeQuery();
            while(rs.next()) {
                int idPais = rs.getInt("idPais");
                String nombre = rs.getString("nombre");
                pais = new Pais(idPais, nombre);
            }
        } catch(SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Connect.close(rs);
            Connect.close(stmt);
            if( this.transactionalConnection == null) {
                Connect.close(conn);
            }
        }

        return pais;
    }
}
