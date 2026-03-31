package com.example.service;

import java.time.LocalDate;

import com.example.repository.EmprestimoRepository;

public class EmprestimoService {

    private final EmprestimoRepository repository;

    public EmprestimoService(EmprestimoRepository repository) {
        this.repository = repository;
    }
    
    public registerBorrow(Usuario usuario, Livro livro, LocalDate dataEmprestimo, LocalDate dataDevolucao) {
        if (usuario.get)
        repository.registerBorrow(usuario, livro, dataEmprestimo, dataDevolucao);
    }
}
