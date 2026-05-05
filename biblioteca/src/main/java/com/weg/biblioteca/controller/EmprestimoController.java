package com.weg.biblioteca.controller;

import com.weg.biblioteca.model.Emprestimo;
import com.weg.biblioteca.service.EmprestimoService;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/emprestimos")
public class EmprestimoController {
    private final EmprestimoService service;

    public EmprestimoController(EmprestimoService service) {
        this.service = service;
    }

    @PostMapping
    public Emprestimo salvar(@RequestBody Emprestimo emprestimo) {
        try {
            return service.salvar(emprestimo);
        } catch (SQLException | RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping
    public List<Emprestimo> buscarTodos() {
        try {
            return service.buscarTodos();
        } catch (SQLException | RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public Optional<Emprestimo> buscarPorId(@PathVariable Long id) {
        try {
            return service.buscarPorId(id);
        } catch (SQLException | RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public Emprestimo atualizar(@PathVariable long id, @RequestBody Emprestimo emprestimo) {
        try {
            return service.atualizar(id, emprestimo);
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

    @PutMapping("/{id}/devolucao")
    public Emprestimo devolver(@PathVariable long id) {
        try {
            return service.devolver(id);
        } catch (SQLException | RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
