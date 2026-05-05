package com.weg.biblioteca.service;

import com.weg.biblioteca.model.Emprestimo;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface EmprestimoService {
    public Emprestimo salvar(Emprestimo emprestimo) throws SQLException;
    public List<Emprestimo> buscarTodos() throws SQLException;
    public Optional<Emprestimo> buscarPorId(long id) throws SQLException;
    public Emprestimo atualizar(long id, Emprestimo emprestimo) throws SQLException;
    public void deletar(long id) throws SQLException;
    public Emprestimo devolver(long id) throws SQLException;
}
