package org.entreculturas.dao.JDBC;

import org.entreculturas.dao.JDBC.IProyectoDAO;
import org.entreculturas.entities.Proyecto;
import org.entreculturas.jdbc.Connect;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProyectoMYSQL extends Connect implements IProyectoDAO {
    private static final String SQL_SELECT_ALL = "SELECT * FROM proyectos";
    private static final String SQL_SELECT = "SELECT * FROM proyectos WHERE idProyecto=?";
    private static final String SQL_INSERT = "INSERT INTO proyectos (nombre, fechaInicio, fechaFin, financiador, financiacion, socioLocal, idDelegacion, idPais) VALUES(?,?,?,?,?,?,?,?)";
    private static final String SQL_UPDATE = "UPDATE proyectos SET nombre=?, fechaInicio=?, fechaFin=?, financiador=?, financiacion=?, socioLocal=?, idDelegacion=?, idPais=? WHERE idProyecto=?";
    private Connection transactionalConnection;

    public ProyectoMYSQL() {
    }

    public ProyectoMYSQL(Connection conn) {
        this.transactionalConnection = conn;
    }

    @Override
    public int insert(Proyecto proyecto) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int idProyecto = 0;
        try {
            conn = this.transactionalConnection != null ? this.transactionalConnection : Connect.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, proyecto.getNombre());
            stmt.setDate(2, Date.valueOf(proyecto.fechaInicio));
            stmt.setDate(3, Date.valueOf(proyecto.fechafin));
            stmt.setString(4, proyecto.getFinanciador());
            stmt.setInt(5, proyecto.getFinanciacion());
            stmt.setInt(6, proyecto.getIdSocioLocal());
            stmt.setInt(7, proyecto.getIdDelegacion());
            stmt.setInt(8, proyecto.getIdpais());
            int affectedRows = stmt.executeUpdate();

            if(affectedRows == 0){
                throw new SQLException("No se pudo guardar");
            }

            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                idProyecto = generatedKeys.getInt(1);
            }
        } catch(SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Connect.close(stmt);
            if( this.transactionalConnection == null) {
                Connect.close(conn);
            }
        }

        return idProyecto;
    }

    @Override
    public Proyecto update(Proyecto proyecto) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = this.transactionalConnection != null ? this.transactionalConnection : Connect.getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setString(1, proyecto.getNombre());
            stmt.setDate(2, Date.valueOf(proyecto.fechaInicio));
            stmt.setDate(3, Date.valueOf(proyecto.fechafin));
            stmt.setString(4, proyecto.getFinanciador());
            stmt.setInt(5, proyecto.getFinanciacion());
            stmt.setInt(6, proyecto.getIdSocioLocal());
            stmt.setInt(7, proyecto.getIdDelegacion());
            stmt.setInt(8, proyecto.getIdpais());
            stmt.setInt(9, proyecto.getIdProyecto());
            stmt.execute();
        } catch(SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Connect.close(stmt);
            if( this.transactionalConnection == null) {
                Connect.close(conn);
            }
        }

        return proyecto;
    }

    @Override
    public List<Proyecto> selectAll() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Proyecto proyecto = null;
        List<Proyecto> proyectos = new ArrayList<>();

        try {
            conn = this.transactionalConnection != null ? this.transactionalConnection : Connect.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_ALL);
            rs = stmt.executeQuery();
            while(rs.next()){
                int idProyecto = rs.getInt("idProyecto");
                String nombre = rs.getString("nombre");
                Date fechaInicio = rs.getDate("fechaInicio");
                Date fechafin = rs.getDate("fechafin");
                String financiador = rs.getString("financiador");
                int financiacion = rs.getInt("financiacion");
                int socioLocal = rs.getInt("socioLocal");
                int idDelegacion = rs.getInt("idDelegacion");
                int idPais = rs.getInt("idPais");

                proyecto = new Proyecto(idPais, nombre, Proyecto.convertDateToString(fechaInicio), Proyecto.convertDateToString(fechafin), financiador, financiacion, socioLocal, idDelegacion, idPais);
                proyectos.add(proyecto);
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
        return proyectos;
    }

    @Override
    public Proyecto selectOne(int uid) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Proyecto proyecto = null;

        try {
            conn = this.transactionalConnection != null ? this.transactionalConnection : Connect.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            stmt.setInt(1, uid);
            rs = stmt.executeQuery();
            while(rs.next()){
                int idProyecto = rs.getInt("idProyecto");
                String nombre = rs.getString("nombre");
                Date fechaInicio = rs.getDate("fechaInicio");
                Date fechafin = rs.getDate("fechafin");
                String financiador = rs.getString("financiador");
                int financiacion = rs.getInt("financiacion");
                int socioLocal = rs.getInt("socioLocal");
                int idDelegacion = rs.getInt("idDelegacion");
                int idPais = rs.getInt("idPais");
                proyecto = new Proyecto(idPais, nombre, Proyecto.convertDateToString(fechaInicio), Proyecto.convertDateToString(fechafin), financiador, financiacion, socioLocal, idDelegacion, idPais);
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

        return proyecto;
    }
}
