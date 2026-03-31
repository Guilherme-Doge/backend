package com.example.service;

import com.example.repository.LivroRepository;

public class EmprestimoService {

    private final EmprestimoService repository;

    public LivroService(LivroRepository repository) {
        this.repository = repository;
    }
    
}
