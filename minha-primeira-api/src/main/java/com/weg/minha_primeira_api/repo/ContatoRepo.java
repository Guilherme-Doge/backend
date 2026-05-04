package com.weg.minha_primeira_api.repo;

import com.weg.minha_primeira_api.model.Contato;

import java.sql.SQLException;
import java.util.List;

public interface ContatoRepo {
    public Contato createContato(Contato contato) throws SQLException;

    public List<Contato> readAllContato() throws SQLException;

    public void updateContatoById(Contato contato) throws SQLException;

    public void deleteContatoById(long id) throws SQLException;
}