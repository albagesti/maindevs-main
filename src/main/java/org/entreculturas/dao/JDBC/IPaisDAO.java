package org.entreculturas.dao.JDBC;
import org.entreculturas.entities.Pais;

import java.util.List;

public interface IPaisDAO {
    public int insert(Pais pais);
    public Pais update(Pais pais);
    public List<Pais> selectAll();
    public Pais selectOne(int uid);
}
