package com.example.service;

import com.example.repository.LivroRepository;

public class LivroService {

    private final LivroRepository repository;

    public LivroService(LivroRepository repository) {
        this.repository = repository;
    }

    public void registerBook() {
        repository.registerBook()
    }

    public void seeBooks() {

    }

    public void registerBorrow() {

    }

    public void registerDevolution() {

    }

    
}