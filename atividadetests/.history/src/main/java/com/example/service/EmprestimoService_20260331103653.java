package com.example.service;

import java.time.LocalDate;

import com.example.model.Usuario;
import com.example.repository.EmprestimoRepository;

public class EmprestimoService {

    private final EmprestimoRepository repository;

    public EmprestimoService(EmprestimoRepository repository) {
        this.repository = repository;
    }
    
    public registerBorrow(Usuario usuario, Livro livro, LocalDate dataEmprestimo, LocalDate dataDevolucao) {
        if (repository.listarUsuarioPorId(Usuario.getId()).isNull()) {
            throw new RuntimeException("ID do usuário não existe");
        }
        repository.registerBorrow(usuario, livro, dataEmprestimo, dataDevolucao);
    }
}
