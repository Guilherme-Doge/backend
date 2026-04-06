package com.example.repository;

import com.example.model.Livro;

import java.sql.SQLException;
import java.util.List;

public interface LivroRepository {
    Livro salvarLivro(Livro livro) throws SQLException;

    List<Livro> buscarTodos() throws SQLException ;

    Livro atualizarDisponibilidade(int id, boolean disponibilidade) throws SQLException ;
}
