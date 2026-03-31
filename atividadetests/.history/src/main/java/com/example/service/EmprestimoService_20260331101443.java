package com.example.service;

import com.example.repository.EmprestimoRepository;
import com.example.repository.LivroRepository;

public class EmprestimoService {

    private final EmprestimoRepository repository;

    public EmprestimoRepository(EmprestimoRepository repository) {
        this.repository = repository;
    }
    
}
