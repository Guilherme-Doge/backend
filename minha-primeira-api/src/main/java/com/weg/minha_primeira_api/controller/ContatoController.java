package com.weg.minha_primeira_api.controller;

import com.weg.minha_primeira_api.model.Contato;
import com.weg.minha_primeira_api.repo.ContatoRepo;
import com.weg.minha_primeira_api.repo.ContatoRepoImpl;
import com.weg.minha_primeira_api.service.ContatoService;
import com.weg.minha_primeira_api.service.ContatoServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contatos")
public class ContatoController {

    private ContatoRepo repo = new ContatoRepoImpl();
    private ContatoService service = new ContatoServiceImpl(repo);

    @PostMapping
    public void createContato(String nome, String numero) {
        service.createContato(nome, numero);
    }

    @GetMapping
    public List<Contato> readAllContato() {
        return service.readAllContato();
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