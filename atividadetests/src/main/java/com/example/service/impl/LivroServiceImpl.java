package com.example.service.impl;

import com.example.model.Livro;
import com.example.repository.LivroRepository;
import com.example.repository.impl.LivroRepositoryImpl;
import com.example.service.LivroService;

import java.sql.SQLException;
import java.util.List;

public class LivroServiceImpl implements LivroService {

    private final LivroRepository livroRepository = new LivroRepositoryImpl();

    @Override
    public Livro salvarLivro(Livro livro) throws SQLException {
        return livroRepository.salvarLivro(livro);
    }

    @Override
    public List<Livro> buscarTodos() throws SQLException {
        return livroRepository.buscarTodos();
    }

    @Override
    public Livro alterarDisponibilidade(Livro livro, boolean disponibilidade) throws SQLException {
        livroRepository.atualizarDisponibilidade(livro.getId(), disponibilidade);

        livro.setDisponivel(disponibilidade);

        return livro;
    }
}
