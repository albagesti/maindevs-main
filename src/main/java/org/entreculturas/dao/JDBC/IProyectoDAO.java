package org.entreculturas.dao.JDBC;
import org.entreculturas.entities.Proyecto;

import java.util.List;

public interface IProyectoDAO {
    public int insert(Proyecto linea);
    public Proyecto update(Proyecto linea);
    public List<Proyecto> selectAll();
    public Proyecto selectOne(int uid);
}
