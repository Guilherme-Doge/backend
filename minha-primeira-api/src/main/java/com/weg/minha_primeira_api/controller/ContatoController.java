package com.weg.minha_primeira_api.controller;

import com.weg.minha_primeira_api.model.Contato;
import com.weg.minha_primeira_api.service.ContatoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contatos")
public class ContatoController {
    private final ContatoService service;

    public ContatoController(ContatoService service) {
        this.service = service;
    }

    @PostMapping
    public void createContato(String nome, String numero) {
        service.createContato(nome, numero);
    }

    @GetMapping
    public List<Contato> readAllContato() {
        return service.readAllContato();
    }

    @GetMapping("/{id}")
    public Contato buscarContatoPorId(@PathVariable long id) {
        return service.readContatoById(id);
    }

    @PutMapping
    public void updateContato(String nome, String numero, long id) {
        service.updateContatoById(nome, numero, id);
    }

    @DeleteMapping
    public void deleteContato(long id) {
        service.deleteContatoById(id);
    }
}