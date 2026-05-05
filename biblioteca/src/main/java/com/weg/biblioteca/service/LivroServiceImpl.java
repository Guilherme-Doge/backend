package com.weg.biblioteca.service;

import com.weg.biblioteca.dao.LivroRepo;
import com.weg.biblioteca.model.Livro;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public class LivroServiceImpl implements LivroService {

    private final LivroRepo repo;

    public LivroServiceImpl(LivroRepo repo) {
        this.repo = repo;
    }

    @Override
    public Livro salvar(Livro livro) throws SQLException {
        if (livro == null) {
            throw new RuntimeException("Livro não pode ser nulo");
        }

        return repo.salvar(livro);
    }

    @Override
    public List<Livro> buscarTodos() throws SQLException {
        return repo.buscarTodos();
    }

    @Override
    public Optional<Livro> buscarPorId(long id) throws SQLException {
        Livro livro = repo.buscarPorId(id).orElseThrow(() -> new RuntimeException("O livro não foi encontrado!"));
        return Optional.of(livro);
    }

    @Override
    public Livro atualizar(long id, Livro livro) throws SQLException {
        if (!repo.existePorId(id)) {
            throw new RuntimeException("Livro não encontrado!");
        }

        livro.setId(id);
        return repo.atualizar(livro);
    }

    @Override
    public void deletar(long id) throws SQLException {
        if (!repo.existePorId(id)) {
            throw new RuntimeException("Livro não encontrado!");
        }

        repo.deletar(id);
    }
}
