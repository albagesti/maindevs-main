package org.entreculturas.dao.JDBC;
import org.entreculturas.entities.Delegacion;

import java.util.List;

public interface IDelegacionDAO {
    public int insert(Delegacion delegacion);
    public Delegacion update(Delegacion delegacion);
    public List<Delegacion> selectAll();
    public Delegacion selectOne(int uid);
}