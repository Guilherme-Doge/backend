package com.weg.biblioteca.controller;

import com.weg.biblioteca.model.Usuario;
import com.weg.biblioteca.service.UsuarioService;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    private final UsuarioService service;

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    @PostMapping
    public Usuario salvar(@RequestBody Usuario usuario) {
        try {
            return service.salvar(usuario);
        } catch (SQLException | RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping
    public List<Usuario> buscarTodos() {
        try {
            return service.buscarTodos();
        } catch (SQLException | RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public Optional<Usuario> buscarPorId(@PathVariable Long id) {
        try {
            return service.buscarPorId(id);
        } catch (SQLException | RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public Usuario atualizar(@PathVariable long id, @RequestBody Usuario usuario) {
        try {
            return service.atualizar(id, usuario);
        } catch (SQLException | RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable long id) {
        try {
            service.deletar(id);
        } catch (SQLException | RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
