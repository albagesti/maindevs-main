package org.entreculturas.dao.JDBC;
import org.entreculturas.entities.Socio;
import org.entreculturas.jdbc.Connect;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SociosMYSQL extends Connect implements ISocioDAO {
    private static final String SQL_SELECT_ALL = "SELECT * FROM socios ";
    private static final String SQL_SELECT = "SELECT * FROM socios WHERE idSocios=?";
    private static final String SQL_INSERT = "INSERT INTO socios (nombre, DNI, idProyecto,TipoCuota) VALUES(?,?,?,?)";
    private static final String SQL_UPDATE = "UPDATE socios SET nombre=?, DNI=?,idProyecto=?, TipoCuota=? WHERE idSocios=?";

    @Override
    public void insert(Socio socio){
        Connection conn=null;
        PreparedStatement stmt=null;
        int idSocio=0;

        try{
            conn= Connect.getConnection();
            stmt=conn.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1,socio.getNombre());
            stmt.setString(2,socio.getDNI());
            stmt.setString(3,socio.getTipoCuota());
            stmt.setInt(4, socio.getProyectoId());
            int affectedRows= stmt.executeUpdate();
        if(affectedRows == 0){
            throw new SQLException("No se ha podido guardar");
        }
            ResultSet generatedKeys= stmt.getGeneratedKeys();
        if (generatedKeys.next()){
            idSocio=generatedKeys.getInt(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }finally{
            Connect.close(stmt);
            Connect.close(conn);
        }
    }

    @Override
    public Socio update(Socio socio) {
        Connection conn =null;
        PreparedStatement stmt=null;
        System.out.println(socio.getNombre());

        try{
            conn= Connect.getConnection();
            stmt=conn.prepareStatement(SQL_UPDATE);
            stmt.setString(1,socio.getNombre());
            stmt.setString(2,socio.getDNI());
            stmt.setString(2,socio.getTipoCuota());
            stmt.setInt(3, socio.getProyectoId());
            stmt.execute();

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }finally{
            Connect.close(stmt);
            Connect.close(conn);
        }
        return socio;
    }

    @Override
    public Socio selectOne(int uid) {
        Connection conn = null;
        PreparedStatement stmt= null;
        ResultSet rs=null;
        Socio socio = null;

        try{
            conn= Connect.getConnection();
            stmt= conn.prepareStatement(SQL_SELECT);
            stmt.setInt(1,uid);
            rs=stmt.executeQuery();
            while(rs.next()){
                String nombre=rs.getString("nombre");
                String DNI=rs.getString("DNI");
                int idSocio=rs.getInt("idSocio");
                String TipoCuota=rs.getString("TipoCuota");
                int idProyecto = rs.getInt("idProyecto");
                socio = new Socio(nombre,DNI,idSocio,TipoCuota,idProyecto);
            }
        }catch (SQLException ex){
            ex.printStackTrace(System.out);
        }finally {
            Connect.close(rs);
            Connect.close(stmt);
            Connect.close(conn);
        }
        return socio;
    }

    @Override
    public List<Socio> selectAll() {
        Connection conn = null;
        PreparedStatement stmt= null;
        ResultSet rs=null;
        Socio socio = null;
        List<Socio> socios = new ArrayList<>();

        try{
            conn= Connect.getConnection();
            stmt=conn.prepareStatement(SQL_SELECT_ALL);
            rs = stmt.executeQuery();
            while(rs.next()){
                String nombre=rs.getString("nombre");
                String DNI=rs.getString("DNI");
                int idSocio=rs.getInt("idSocio");
                String TipoCuota=rs.getString("TipoCuota");
                int idProyecto = rs.getInt("idProyecto");
                socio = new Socio(nombre,DNI,idSocio,TipoCuota,idProyecto);
                socios.add(socio);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }finally {
            Connect.close(rs);
            Connect.close(stmt);
            Connect.close(conn);
        }
        return socios;
    }

}
