package com.example.service;

import java.sql.SQLException;
import java.util.List;

import com.example.model.Livro;

public interface LivroService {

    Livro salvarLivro(Livro livro) throws SQLException;

    List<Livro> buscarTodos() throws SQLException;

    Livro alterarDisponibilidade(Livro livro, boolean disponibilidade) throws SQLException;
}