package com.weg.biblioteca.service;

import com.weg.biblioteca.dao.EmprestimoRepo;
import com.weg.biblioteca.model.Emprestimo;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class EmprestimoServiceImpl implements EmprestimoService {

    private final EmprestimoRepo repo;

    public EmprestimoServiceImpl(EmprestimoRepo repo) {
        this.repo = repo;
    }

    @Override
    public Emprestimo salvar(Emprestimo emprestimo) throws SQLException {
        if (emprestimo == null) {
            throw new RuntimeException("Empréstimo não cadastrado");
        }

        return repo.salvar(emprestimo);
    }

    @Override
    public List<Emprestimo> buscarTodos() throws SQLException {
        return repo.buscarTodos();
    }

    @Override
    public Optional<Emprestimo> buscarPorId(long id) throws SQLException {
        Emprestimo emprestimo = repo.buscarPorId(id).orElseThrow(() -> new RuntimeException("O emprestimo não foi encontrado!"));
        return Optional.of(emprestimo);
    }

    @Override
    public Emprestimo atualizar(long id, Emprestimo emprestimo) throws SQLException {
        if (!repo.existePorId(id)) {
            throw new RuntimeException("Emprestimo não encontrado");
        }

        emprestimo.setId(id);
        return repo.atualizar(emprestimo);
    }


    @Override
    public void deletar(long id) throws SQLException {
        if (!repo.existePorId(id)) {
            throw new RuntimeException("Emprestimo não encontrado");
        }

        repo.deletar(id);
    }

    @Override
    public Emprestimo devolver(long id) throws SQLException {
        if (!repo.existePorId(id)) {
            throw new RuntimeException("Empresitmo não encontrado");
        }

        Emprestimo emprestimo;
        emprestimo = buscarPorId(id).get();
        emprestimo.setDataDevolucao(LocalDate.now());

        return repo.atualizar(emprestimo);
    }
}
