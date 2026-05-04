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
        return repo.buscarPorId(id);
    }

    @Override
    public Livro atualizar(long id) throws SQLException {
        return null;
    }

    @Override
    public void deletar(long id) throws SQLException {

    }
}
