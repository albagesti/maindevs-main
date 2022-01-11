package org.entreculturas;

import org.entreculturas.dao.*;
import org.entreculturas.dao.JDBC.*;
import org.entreculturas.entities.*;
import org.entreculturas.jdbc.Connect;
import org.entreculturas.utils.CreateDB;
import org.entreculturas.utils.GlobalVars;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import org.hibernate.*;


public class Main extends Application implements GlobalVars  {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) throws SQLException, XPathExpressionException, ParserConfigurationException, IOException, SAXException {
        launch();
        MysqlDAOFactory dao = new DAOFactory().getDAOFactory(DAOFactory.MYSQL);
        IPaisDAO daoPais = dao.getPaisDAO();
        ILineaDAO daoLinea = dao.getLineasAccionDAO();
        ISocioDAO daoSocio = dao.getSociosDAO();
        IProyectoDAO daoProyecto = dao.getProyectoDAO();
        IDelegacionDAO daoDelegacion = dao.getDelegacionDAO();

        // Generamos Estructura de tablas y rellenamos datos previos con los XML generados en las anteriores prácticas
        CreateDB db = new CreateDB();
        db.generateTables();
        db.generateXMLContentToDB(path.concat("socios.xml"), "socio", "socios");
        db.generateXMLContentToDB(path.concat("delegaciones.xml"), "delegacion", "delegaciones");
        db.generateXMLContentToDB(path.concat("proyectos.xml"), "proyecto", "proyectos");
        db.generateXMLContentToDB(path.concat("paises.xml"), "pais", "paises");
        db.generateXMLContentToDB(path.concat("lineasAccion.xml"), "lineaAccion", "lineas");
        System.out.println("\n");


        //Prueba acceso JPA hibernate


        // Listamos socios
        List<Socio> socios = daoSocio.selectAll();
        System.out.println("/////////////////////////////////////");
        System.out.println("Listado de socios:");
        for (Socio socio : socios) {
            System.out.println(socio.getNombre());
        }
        System.out.println("\n");

        // Listamos delegaciones
        List<Delegacion> delegaciones = daoDelegacion.selectAll();
        System.out.println("/////////////////////////////////////");
        System.out.println("Listado de delegaciones:");
        for (Delegacion delegacion : delegaciones) {
            System.out.println(delegacion.getNombre());
        }
        System.out.println("\n");

        // Listamos Proyectos
        List<Proyecto> proyectos = daoProyecto.selectAll();
        System.out.println("/////////////////////////////////////");
        System.out.println("Listado de Proyectos:");
        for (Proyecto proyecto : proyectos) {
            System.out.println(proyecto.getNombre());
        }
        System.out.println("\n");

        // Seleccionamos Proyecto
        System.out.println("/////////////////////////////////////");
        System.out.println("Seleccionamos proyecto id=1:");
        Proyecto proyecto1 = daoProyecto.selectOne(1);
        System.out.println("Nombre: " + proyecto1.getNombre());
        System.out.println("\n");

        // Modificamos Proyecto id=1
        System.out.println("/////////////////////////////////////");
        System.out.println("Modificamos Proyecto id=1:");
        proyecto1.setNombre("Comedores Sociales en España");
        System.out.println("Actualizamos nombre en la base de datos...");
        Proyecto proyecto1_actualizado = daoProyecto.update(proyecto1);
        System.out.println("Nuevo nombre: "+proyecto1_actualizado.getNombre());
        System.out.println("\n");

        // Creamos un nuevo proyecto
        System.out.println("/////////////////////////////////////");
        System.out.println("Creamos un nuevo Proyecto con una transacción que depende de insertar un nuevo `País` y un nuevo `Proyecto`");
        Connection conn = null;
        Proyecto nuevoProyecto = null;
        Pais nuevoPais = null;
        try{
            conn = Connect.getConnection();
            IPaisDAO daoPaisTrans = dao.getPaisDAO(conn);
            if(conn.getAutoCommit()){
                conn.setAutoCommit(false);
            }
            nuevoPais = new Pais(null, "Indonesia");
            int idPais = daoPaisTrans.insert(nuevoPais);

            nuevoProyecto = new Proyecto(null, "Mar de plástico", "2022-08-10", "2023-10-15", "BBVA", 2000000, 1, 1, idPais);
            IProyectoDAO daoProyectoTrans = dao.getProyectoDAO(conn);
            daoProyectoTrans.insert(nuevoProyecto);

            conn.commit();
            System.out.println("El país "+nuevoPais.getNombre()+" y el proyecto "+nuevoProyecto.getNombre()+" se han creado correctamente");
        } catch(SQLException ex){
            ex.printStackTrace(System.out);
            System.out.println("Entramos en el rollbackm, ha habido un error con esta transacción");
            try {
                assert conn != null;
                conn.rollback();
            } catch (SQLException exRollback){
                exRollback.printStackTrace(System.out);
            }
        }
    }
}
