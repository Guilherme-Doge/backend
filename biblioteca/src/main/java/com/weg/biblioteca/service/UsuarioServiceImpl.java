package com.weg.biblioteca.service;

import com.weg.biblioteca.dao.UsuarioRepo;
import com.weg.biblioteca.model.Usuario;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepo repo;

    public UsuarioServiceImpl(UsuarioRepo repo) {
        this.repo = repo;
    }

    @Override
    public Usuario salvar(Usuario usuario) throws SQLException {
        if (usuario == null) {
            throw new RuntimeException("Usuario não pode ser nulo");
        }

        return repo.salvar(usuario);
    }

    @Override
    public List<Usuario> buscarTodos() throws SQLException {
        return repo.buscarTodos();
    }

    @Override
    public Optional<Usuario> buscarPorId(long id) throws SQLException {
        Usuario usuario = repo.buscarPorId(id).orElseThrow(() -> new RuntimeException("O usuário não foi encontrado!"));
        return Optional.of(usuario);
    }

    @Override
    public Usuario atualizar(long id, Usuario usuario) throws SQLException {
        if (!repo.existePorId(id)) {
            throw new RuntimeException("Usuário não encontrado!");
        }

        usuario.setId(id);
        return repo.atualizar(usuario);
    }

    @Override
    public void deletar(long id) throws SQLException {
        if (!repo.existePorId(id)) {
            throw new RuntimeException("Usuário não encontrado!");
        }

        repo.deletar(id);
    }
}
