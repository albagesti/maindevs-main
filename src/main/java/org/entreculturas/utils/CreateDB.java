package org.entreculturas.utils;

import org.entreculturas.entities.Proyecto;
import org.entreculturas.jdbc.Connect;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

public class CreateDB {
    Connection conn = null;

    public void generateTables() {
        try {
            Connection conn = Connect.getConnection();
            conn.createStatement()
                    .execute("DROP TABLE IF EXISTS lineas;");
            conn.createStatement()
                    .execute("DROP TABLE IF EXISTS proyectos;");
            conn.createStatement()
                    .execute("DROP TABLE IF EXISTS delegaciones;");
            conn.createStatement()
                    .execute("DROP TABLE IF EXISTS socios;");
            conn.createStatement()
                    .execute("DROP TABLE IF EXISTS paises;");

            // Tabla Delegaciones
            conn.createStatement()
                    .execute("CREATE TABLE delegaciones(\n" +
                            " idDelegacion int PRIMARY KEY NOT NULL AUTO_INCREMENT,\n" +
                            " nombre varchar(45) NOT NULL,\n" +
                            " direccion text NOT NULL,\n" +
                            " telefono varchar(45) NOT NULL,\n" +
                            " email varchar(45) NOT NULL\n" +
                            ")");
            System.out.println("Table delegaciones created");

            // Tabla Socios
            conn.createStatement()
                    .execute("CREATE TABLE socios(\n" +
                            " idSocio int PRIMARY KEY NOT NULL AUTO_INCREMENT,\n" +
                            " nombre varchar(50) NOT NULL,\n" +
                            " DNI varchar(45) NOT NULL,\n" +
                            " tipoCuota varchar(45) NOT NULL,\n" +
                            " idProyecto int DEFAULT NULL\n" +
                            ")");
            System.out.println("Table socios created");

            //Tabla Paises
            conn.createStatement()
                    .execute("CREATE TABLE paises(\n" +
                            " idPais int PRIMARY KEY NOT NULL AUTO_INCREMENT,\n" +
                            " nombre varchar(45) NOT NULL\n" +
                            ")");
            System.out.println("Table paises created");

            // Tabla Proyectos
            conn.createStatement()
                    .execute("CREATE TABLE proyectos(\n" +
                            " idProyecto int PRIMARY KEY NOT NULL AUTO_INCREMENT,\n" +
                            " nombre varchar(50) NOT NULL,\n" +
                            " fechaInicio datetime NOT NULL,\n" +
                            " fechaFin datetime NOT NULL,\n" +
                            " financiador varchar(45) NOT NULL,\n" +
                            " financiacion int NOT NULL,\n" +
                            " socioLocal int NOT NULL,\n" +
                            " idDelegacion int NOT NULL,\n" +
                            " idPais int NOT NULL,\n" +
                            " KEY idSocio_idx (socioLocal),\n" +
                            " KEY idDelegacion_idx (idDelegacion),\n" +
                            " KEY idPais_ix (idPais),\n" +
                            " CONSTRAINT idDelegacion_proyecto FOREIGN KEY (idDelegacion) REFERENCES delegaciones (idDelegacion),\n" +
                            " CONSTRAINT idSocio_proyecto FOREIGN KEY (socioLocal) REFERENCES socios (idSocio)\n" +
                            ")");
            System.out.println("Table proyectos created");

            // Tabla LineasAccion
            conn.createStatement()
                    .execute("CREATE TABLE lineas(\n" +
                            " idLinea int PRIMARY KEY NOT NULL AUTO_INCREMENT,\n" +
                            " nombre varchar(45) NOT NULL,\n" +
                            " lineaPadreId int DEFAULT NULL,\n" +
                            " idProyecto int DEFAULT NULL,\n" +
                            " KEY idProyecto_idx (idProyecto),\n" +
                            " KEY lineaPadreId (idLinea),\n" +
                            " CONSTRAINT idProyecto_linea FOREIGN KEY (idProyecto) REFERENCES proyectos (idProyecto)\n" +
                            ")");
            System.out.println("Table lineas created");
        } catch(SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }

    public void generateXMLContentToDB(String fileName, String nodeName, String tableName) throws ParserConfigurationException, IOException, SAXException {
        File file = new File(fileName);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document xmlDoc = builder.parse(file);
        xmlDoc.getDocumentElement ().normalize();
        ArrayList<String> columns = new ArrayList<>();
        ArrayList<Object> attributes = new ArrayList<>();

        NodeList listOfNodes = xmlDoc.getElementsByTagName(nodeName);
        for(int i=0; i<listOfNodes.getLength(); i++){
            Node node = listOfNodes.item(i);
            if(node.getNodeType() == Node.ELEMENT_NODE) {
                Element parentNode = (Element)node;
                NodeList childNodes = parentNode.getChildNodes();

                for(int j=0; j<childNodes.getLength(); j++){
                    Node childNodeName = childNodes.item(j);
                    if(childNodeName.getNodeType() == Node.ELEMENT_NODE) {
                        columns.add(childNodeName.getNodeName());
                        attributes.add(childNodeName.getChildNodes().item(0).getNodeValue());
                    }
                }
            }
            this.insertSQL(columns, attributes, tableName);
            columns.clear();
            attributes.clear();
        }
        System.out.println("Se han insertado los registros del archivo "+fileName+" en la tabla "+tableName);

    }

    private void insertSQL(ArrayList<String> columns, ArrayList<Object> attributes, String tableName) {
        Connection conn = null;
        PreparedStatement stmt = null;
        String params = String.join(",", columns);
        ArrayList<String> valuesArr = new ArrayList<>();

        for (int i = 0; i < columns.size(); i++) {
            valuesArr.add("?");
        }
        String values = String.join(",", valuesArr);

        final String SQL_INSERT = "INSERT INTO " + tableName + " (" + params + ") VALUES(" + values + ")";

        try {
            conn = Connect.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
            switch (tableName) {
                case "proyectos":
                    stmt.setInt(1, Integer.parseInt(attributes.get(0).toString()));
                    stmt.setString(2, attributes.get(1).toString());
                    stmt.setDate(3, Date.valueOf(Proyecto.convertToLocalDate(attributes.get(2).toString())));
                    stmt.setDate(4, Date.valueOf(Proyecto.convertToLocalDate(attributes.get(3).toString())));
                    stmt.setString(5, attributes.get(4).toString());
                    stmt.setInt(6, Integer.parseInt(attributes.get(5).toString()));
                    stmt.setInt(7, Integer.parseInt(attributes.get(6).toString()));
                    stmt.setInt(8, Integer.parseInt(attributes.get(7).toString()));
                    stmt.setInt(9, Integer.parseInt(attributes.get(8).toString()));
                    break;
                case "socios":
                    stmt.setInt(1, Integer.parseInt(attributes.get(0).toString()));
                    stmt.setString(2,attributes.get(1).toString());
                    stmt.setString(3,attributes.get(2).toString());
                    stmt.setString(4,attributes.get(3).toString());
                    stmt.setInt(5, Integer.parseInt(attributes.get(4).toString()));
                    break;
                case "paises":
                    stmt.setInt(1, Integer.parseInt(attributes.get(0).toString()));
                    stmt.setString(2, attributes.get(1).toString());
                    break;
                case "lineas":
                    stmt.setInt(1, Integer.parseInt(attributes.get(0).toString()));
                    stmt.setString(2, attributes.get(1).toString());
                    stmt.setInt(3, Integer.parseInt(attributes.get(2).toString()));
                    stmt.setInt(4, Integer.parseInt(attributes.get(3).toString()));
                    break;
                case "delegaciones":
                    stmt.setInt(1, Integer.parseInt(attributes.get(0).toString()));
                    stmt.setString(2, attributes.get(1).toString());
                    stmt.setString(3, attributes.get(2).toString());
                    stmt.setString(4, attributes.get(3).toString());
                    stmt.setString(5, attributes.get(4).toString());
                default:
                    break;
            }
            stmt.execute();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Connect.close(stmt);
            Connect.close(conn);
        }
    }
}
