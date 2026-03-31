package com.example.service;

import com.example.model.Livro;
import com.example.repository.LivroRepository;

public class LivroService {

    private final LivroRepository repository;

    public LivroService(LivroRepository repository) {
        this.repository = repository;
    }

    public void registerBook(String title, String author, int publicationYear) {
        repository.registerBook(title, author, publicationYear);
    }

    public void validarExistenciaLivro(Livro livro) {
        if (livro.getId() == repository.listarLivroPorId(livro.getId()).getId()) {
            throw new RuntimeException("Livro não existe");
        }
    }

    public void marcarLivroComoNaoDisponivel(Livro livro) {
        repository.marcarLivroComoNaoDisponivel(livro.getId());
    }

    public void devolverLivro(Livro livro, ) {

    }
}