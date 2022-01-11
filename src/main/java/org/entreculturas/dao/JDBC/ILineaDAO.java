package org.entreculturas.dao.JDBC;
import org.entreculturas.entities.LineaAccion;

import java.util.List;

public interface ILineaDAO {
    public int insert(LineaAccion linea);
    public LineaAccion update(LineaAccion linea);
    public List<LineaAccion> selectAll();
    public LineaAccion selectOne(int uid);
}
