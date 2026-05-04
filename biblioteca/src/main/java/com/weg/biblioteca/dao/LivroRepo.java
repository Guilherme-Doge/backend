package com.weg.biblioteca.dao;

import com.weg.biblioteca.model.Livro;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface LivroRepo {
    public Livro salvar(Livro livro) throws SQLException;
    public List<Livro> buscarTodos() throws SQLException;
    public Optional<Livro> buscarPorId(long id) throws SQLException;
    public Livro atualizar(long id) throws SQLException;
    public void deletar(long id) throws SQLException;
}
