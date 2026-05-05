package com.weg.biblioteca.service;

import com.weg.biblioteca.model.Usuario;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface UsuarioService {
    public Usuario salvar(Usuario usuario) throws SQLException;
    public List<Usuario> buscarTodos() throws SQLException;
    public Optional<Usuario> buscarPorId(long id) throws SQLException;
    public Usuario atualizar(long id, Usuario usuario) throws SQLException;
    public void deletar(long id) throws SQLException;
}
