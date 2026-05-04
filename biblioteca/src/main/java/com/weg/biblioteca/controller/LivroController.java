package com.weg.biblioteca.controller;

import com.weg.biblioteca.model.Livro;
import com.weg.biblioteca.service.LivroService;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/livros")
public class LivroController {
    private final LivroService service;

    public LivroController(LivroService service) {
        this.service = service;
    }

    @PostMapping
    public Livro salvar(@RequestBody Livro livro) {
        try {
            return service.salvar(livro);
        } catch (SQLException | RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping
    public List<Livro> buscarTodos() {
        try {
            return service.buscarTodos();
        } catch (SQLException | RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public Optional<Livro> buscarPorId(@PathVariable Long id) {
        try {
            return service.buscarPorId(id);
        } catch (SQLException | RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
