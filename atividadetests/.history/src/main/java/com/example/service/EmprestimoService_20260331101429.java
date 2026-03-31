package com.example.service;

import com.example.repository.LivroRepository;

public class EmprestimoService {

    private final LivroRepository repository;

    public LivroService(LivroRepository repository) {
        this.repository = repository;
    }
    
}
