package com.weg.biblioteca.service;

import com.weg.biblioteca.model.Livro;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface LivroService {
    public Livro salvar(Livro livro) throws SQLException;
    public List<Livro> buscarTodos() throws SQLException;
    public Optional<Livro> buscarPorId(long id) throws SQLException;
    public Livro atualizar(long id, Livro livro) throws SQLException;
    public void deletar(long id) throws SQLException;
}
