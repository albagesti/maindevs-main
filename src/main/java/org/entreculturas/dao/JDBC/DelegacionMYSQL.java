package org.entreculturas.dao.JDBC;

import org.entreculturas.dao.JDBC.IDelegacionDAO;
import org.entreculturas.entities.Delegacion;

import org.entreculturas.jdbc.Connect;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DelegacionMYSQL extends Connect implements IDelegacionDAO {
    private static final String SQL_SELECT_ALL = "SELECT idDelegacion, nombre, direccion, telefono, email FROM delegaciones";
    private static final String SQL_SELECT = "SELECT idDelegacion, nombre,direccion, telefono, email FROM delegaciones WHERE idDelegacion=?";
    private static final String SQL_INSERT = "INSERT INTO delegaciones (nombre,direccion,telefono,email) VALUES(?,?,?,?)";
    private static final String SQL_UPDATE = "UPDATE delegaciones SET nombre=?,direccion=?,telefono=?,email=? WHERE idDelegacion=?";

    @Override
    public int insert(Delegacion delegacion) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int idDelegacion = 0;

        try {
            conn = Connect.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, delegacion.getNombre());
            stmt.setString(2, delegacion.getDireccion());
            stmt.setString(3, delegacion.getTelefono());
            stmt.setString(4, delegacion.getEmail());
            int affectedRows = stmt.executeUpdate();

            if(affectedRows == 0){
                throw new SQLException("No se pudo guardar");
            }

            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                idDelegacion = generatedKeys.getInt(1);
            }
        } catch(SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Connect.close(stmt);
            Connect.close(conn);
        }

        return idDelegacion;
    }

    @Override
    public Delegacion update(Delegacion delegacion) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = Connect.getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setString(1, delegacion.getNombre());
            stmt.setString(2, delegacion.getDireccion());
            stmt.setString(3, delegacion.getTelefono());
            stmt.setString(4, delegacion.getEmail());
            stmt.setInt(5, delegacion.getId());
            stmt.execute();
        } catch(SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Connect.close(stmt);
            Connect.close(conn);
        }

        return delegacion;
    }

    @Override
    public List<Delegacion> selectAll() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Delegacion delegacion = null;
        List<Delegacion> paises = new ArrayList<>();

        try {
            conn = Connect.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_ALL);
            rs = stmt.executeQuery();
            while(rs.next()){
                int idDelegacion = rs.getInt("idDelegacion");
                String nombre = rs.getString("nombre");
                String direccion = rs.getString("direccion");
                String telefono = rs.getString("telefono");
                String email = rs.getString("email");


                delegacion = new Delegacion(idDelegacion, nombre, direccion,telefono, email);
                paises.add(delegacion);
            }
        } catch(SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Connect.close(rs);
            Connect.close(stmt);
            Connect.close(conn);
        }
        return paises;
    }

    @Override
    public Delegacion selectOne(int uid) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Delegacion delegacion = null;

        try {
            conn = Connect.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            stmt.setInt(1, uid);
            rs = stmt.executeQuery();
            while(rs.next()) {
                int idDelegacion = rs.getInt("idDelegacion");
                String nombre = rs.getString("nombre");
                String direccion = rs.getString("direccion");
                String telefono = rs.getString("telefono");
                String email = rs.getString("email");

                delegacion = new Delegacion(idDelegacion, nombre, direccion, telefono, email);
            }
        } catch(SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Connect.close(rs);
            Connect.close(stmt);
            Connect.close(conn);
        }

        return delegacion;
    }
}