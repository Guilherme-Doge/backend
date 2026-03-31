package com.example.service;

import com.example.repository.EmprestimoRepository;
import com.example.repository.LivroRepository;
import com.example.repository.UsuarioRepository;

public class UsuarioService {

    private final EmprestimoRepository repository;

    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }
    
}
