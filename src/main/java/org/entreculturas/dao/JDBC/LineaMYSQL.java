package org.entreculturas.dao.JDBC;

import org.entreculturas.dao.JDBC.ILineaDAO;
import org.entreculturas.entities.LineaAccion;
import org.entreculturas.jdbc.Connect;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LineaMYSQL extends Connect implements ILineaDAO {
    private static final String SQL_SELECT_ALL = "SELECT * FROM lineas";
    private static final String SQL_SELECT = "SELECT * FROM lineas WHERE idLinea=?";
    private static final String SQL_INSERT = "INSERT INTO lineas (nombre, lineaPadreId, idProyecto) VALUES(?,?,?)";
    private static final String SQL_UPDATE = "UPDATE lineas SET nombre=?, lineaPadreId=?, idProyecto=? WHERE idLinea=?";
    private Connection transactionalConnection;

    public LineaMYSQL() {
    }

    public LineaMYSQL(Connection transactionalConnection) {
        this.transactionalConnection = transactionalConnection;
    }

    @Override
    public int insert(LineaAccion linea) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int idLinea = 0;

        try {
            conn = this.transactionalConnection != null ? this.transactionalConnection : Connect.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, linea.getNombre());
            stmt.setInt(2, linea.getLineaPadreId());
            stmt.setInt(3, linea.getProyectoId());
            int affectedRows = stmt.executeUpdate();

            if(affectedRows == 0){
                throw new SQLException("No se pudo guardar");
            }

            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                idLinea = generatedKeys.getInt(1);
            }
        } catch(SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Connect.close(stmt);
            if( this.transactionalConnection == null) {
                Connect.close(conn);
            }        }

        return idLinea;
    }

    @Override
    public LineaAccion update(LineaAccion linea) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = this.transactionalConnection != null ? this.transactionalConnection : Connect.getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setString(1, linea.getNombre());
            stmt.setInt(2, linea.getLineaPadreId());
            stmt.setInt(3, linea.getProyectoId());
            stmt.setInt(4, linea.getIdLinea());
            stmt.execute();
        } catch(SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Connect.close(stmt);
            if( this.transactionalConnection == null) {
                Connect.close(conn);
            }        }

        return linea;
    }

    @Override
    public List<LineaAccion> selectAll() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        LineaAccion linea = null;
        List<LineaAccion> lineas = new ArrayList<>();

        try {
            conn = this.transactionalConnection != null ? this.transactionalConnection : Connect.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_ALL);
            rs = stmt.executeQuery();
            while(rs.next()){
                int idLinea = rs.getInt("idLinea");
                String nombre = rs.getString("nombre");
                int lineaPadreId = rs.getInt("lineaPadreId");
                int idProyecto = rs.getInt("idProyecto");

                linea = new LineaAccion(idLinea, nombre, lineaPadreId, idProyecto);
                lineas.add(linea);
            }
        } catch(SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Connect.close(rs);
            Connect.close(stmt);
            if( this.transactionalConnection == null) {
                Connect.close(conn);
            }        }
        return lineas;
    }

    @Override
    public LineaAccion selectOne(int uid) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        LineaAccion linea = null;

        try {
            conn = this.transactionalConnection != null ? this.transactionalConnection : Connect.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            stmt.setInt(1, uid);
            rs = stmt.executeQuery();
            while(rs.next()) {
                int idLinea = rs.getInt("idLinea");
                String nombre = rs.getString("nombre");
                int idLineaPadre = rs.getInt("lineaPadreId");
                int idProyecto = rs.getInt("idProyecto");
                linea = new LineaAccion(idLinea, nombre, idLineaPadre, idProyecto);
            }
        } catch(SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Connect.close(rs);
            Connect.close(stmt);
            if( this.transactionalConnection == null) {
                Connect.close(conn);
            }        }

        return linea;
    }
}
