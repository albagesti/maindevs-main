package org.entreculturas.jdbc;

import java.sql.*;

public class Connect {
    private static final String LOGIN = "root";
    private static final String PASSWORD = "";
    private static final String URL = "jdbc:mysql://localhost:3306/entreculturas?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatatimeCode=false$serverTimezone=UTC";
    public Connection connection = null;


    public static Connection getConnection() throws SQLException{
        return DriverManager.getConnection(URL, LOGIN, PASSWORD);
    }

    public static void close(ResultSet rs){
        try {
            rs.close();
        } catch(SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }

    public static void close(PreparedStatement ps){

        try {
            ps.close();
        } catch(SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }

    public static void close(Connection conn){
        try {
            conn.close();
        } catch(SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }

    public void disconnectDb() throws SQLException {
        if(this.connection != null) this.connection.close();
        this.connection = null;
    }
}