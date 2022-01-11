package org.entreculturas.entities;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@XmlRootElement(name = "proyecto")
public class Proyecto {
    private Integer idProyecto;
    private String nombre;
    public LocalDate fechaInicio;
    public LocalDate fechafin;
    private String financiador;
    private int idPais;
    private int financiacion;
    private int idSocioLocal;
    private int idDelegacion;

    public Proyecto(){}

    public int getIdDelegacion() {
        return idDelegacion;
    }

    public void setIdDelegacion(int idDelegacion) {
        this.idDelegacion = idDelegacion;
    }

    public int getIdSocioLocal() {
        return idSocioLocal;
    }

    public void setIdSocioLocal(int idSocioLocal) {
        this.idSocioLocal = idSocioLocal;
    }

    public Proyecto(
            Integer idProyecto,
            String nombre,
            String fechaInicio,
            String fechaFin,
            String financiador,
            int financiacion,
            int idSocioLocal,
            int idDelegacion,
            Integer idPais
    ) {
        this.idProyecto = idProyecto;
        this.nombre = nombre;
        this.fechaInicio = this.convertToLocalDate(fechaInicio);
        this.fechafin = this.convertToLocalDate(fechaFin);
        this.financiador = financiador;
        this.financiacion = financiacion;
        this.idSocioLocal = idSocioLocal;
        this.idDelegacion = idDelegacion;
        this.idPais = idPais;
    }

    @XmlElement(name="localizacion")
    public int getIdpais(){
        return idPais;
    }

    public void setLocalizacion(int idPais){
        this.idPais = idPais;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFechaInicio() {
        return convertLocalDateToString(fechaInicio);
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = this.convertToLocalDate(fechaInicio);
    }

    public String getFechafin() {
        return convertLocalDateToString(fechafin);
    }

    public void setFechafin(String fechafin) {
        this.fechafin = this.convertToLocalDate(fechafin);
    }

    @XmlElement(name="idProyecto")
    public Integer getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(Integer idProyecto) {
        this.idProyecto = idProyecto;
    }

    public String getFinanciador() {
        return financiador;
    }

    public void setFinanciador(String financiador) {
        this.financiador = financiador;
    }

    public int getFinanciacion() {
        return this.financiacion;
    }

    public void setFinanciacion(int financiacion) {
        this.financiacion = financiacion;
    }

    public static LocalDate convertToLocalDate(String date){
        return LocalDate.parse(date);
    }

    public static String convertLocalDateToString(LocalDate date){
        return date.format(DateTimeFormatter.ofPattern("yyyy-MM-ddd"));
    }

    public static String convertDateToString(Date date){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(date);
    }
}
