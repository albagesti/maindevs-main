package org.entreculturas.entityJPA;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Paises {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idPais")
    private int idPais;
    @Basic
    @Column(name = "nombre")
    private String nombre;

    public int getIdPais() {
        return idPais;
    }

    public void setIdPais(int idPais) {
        this.idPais = idPais;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Paises paises = (Paises) o;
        return idPais == paises.idPais && Objects.equals(nombre, paises.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPais, nombre);
    }
}
