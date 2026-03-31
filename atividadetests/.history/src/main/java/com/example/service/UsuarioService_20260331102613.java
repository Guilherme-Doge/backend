package com.example.service;

import com.example.repository.EmprestimoRepository;
import com.example.repository.LivroRepository;

public class UsuarioService {

    private final EmprestimoRepository repository;

    public UsuarioService(Usuario repository) {
        this.repository = repository;
    }
    
}
