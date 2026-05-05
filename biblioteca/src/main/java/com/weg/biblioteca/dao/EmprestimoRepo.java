package com.weg.biblioteca.dao;

import com.weg.biblioteca.model.Emprestimo;
import org.springframework.cglib.core.Local;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface EmprestimoRepo {
    public Emprestimo salvar(Emprestimo emprestimo) throws SQLException;
    public List<Emprestimo> buscarTodos() throws SQLException;
    public Optional<Emprestimo> buscarPorId(long id) throws SQLException;
    public Emprestimo atualizar(Emprestimo emprestimo) throws SQLException;
    public boolean existePorId(long id) throws SQLException;
    public void deletar(long id) throws SQLException;
}
