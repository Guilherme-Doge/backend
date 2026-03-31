package com.example.service;

import java.time.DateTimeException;
import java.time.LocalDate;

import com.example.model.Livro;
import com.example.model.Usuario;
import com.example.repository.EmprestimoRepository;

public class EmprestimoService {

    private final EmprestimoRepository repository;

    public EmprestimoService(EmprestimoRepository repository) {
        this.repository = repository;
    }
    
    public void registerBorrow(Usuario usuario, Livro livro, LocalDate dataEmprestimo, LocalDate dataDevolucao) {

        if (usuario.getId() == repository.listarUsuarioPorId(usuario.getId()).getId()) {
            throw new RuntimeException("Usuário não existe");
        }

        if (dataEmprestimo.getYear() < LocalDate.now().getYear() - 100) {
            throw new DateTimeException("Data de empréstimo não pode ser mais de cem anos no passado");
        }

        if (dataEmprestimo > LocalDate.now().getYear() + 100) {
            throw new DateTimeException("Data de empréstimo não pode ser mais de cem anos no passado");
        }

        if (dataEmprestimo.getYear() < LocalDate.now().getYear() - 100) {
            throw new DateTimeException("Data de empréstimo não pode ser mais de cem anos no passado");
        }

        if (dataEmprestimo.getYear() < LocalDate.now().getYear() - 100) {
            throw new DateTimeException("Data de empréstimo não pode ser mais de cem anos no passado");
        }

        repository.registerBorrow(usuario, livro, dataEmprestimo, dataDevolucao);
    }
}
