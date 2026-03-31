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

    public void seeBooks() {

    }

    public void registerBorrow() {

    }

    public void registerDevolution() {

    }

    public void validarExistenciaLivro(Livro livro) {
        
    }
}