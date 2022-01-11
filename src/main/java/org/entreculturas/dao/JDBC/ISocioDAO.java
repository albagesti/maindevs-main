package org.entreculturas.dao.JDBC;

import org.entreculturas.entities.Socio;

import java.util.List;

public interface ISocioDAO {

    public void insert(Socio socio);
    public Socio update(Socio socio);
    public List<Socio> selectAll();
    public Socio selectOne(int uid);

}
