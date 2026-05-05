package com.weg.biblioteca.dao;

import com.weg.biblioteca.model.Usuario;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface UsuarioRepo {
    public Usuario salvar(Usuario usuario) throws SQLException;
    public List<Usuario> buscarTodos() throws SQLException;
    public Optional<Usuario> buscarPorId(long id) throws SQLException;
    public Usuario atualizar(Usuario usuario) throws SQLException;
    public boolean existePorId(long id) throws SQLException;
    public void deletar(long id) throws SQLException;
}
