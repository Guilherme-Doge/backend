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
        validarExistenciaUsuario(usuario);
        validarDataEmprestimo(dataEmprestimo);
        validarDataDevolucao(dataDevolucao);

        repository.registerBorrow(usuario, livro, dataEmprestimo, dataDevolucao);
    }

    public void validarExistenciaUsuario(Usuario usuario) {
        if (usuario.getId() == repository.listarUsuarioPorId(usuario.getId()).getId()) {
            throw new RuntimeException("Usuário não existe");
        }
    }

    public void validarDataEmprestimo(LocalDate data) {
        if (data.getYear() < LocalDate.now().getYear() - 100) {
            throw new DateTimeException("Data de empréstimo não pode ser mais de cem anos no passado");
        }

        if (data.isAfter(LocalDate.now())) {
            throw new DateTimeException("Data de empréstimo não pode ser depois da data atual");
        }
    }

    public void validarDataDevolucao(LocalDate data) {
        if (data.isBefore(LocalDate.now())) {
            throw new DateTimeException("Data de devolução não pode ser antes da data atual");
        }

        if (data.getYear() > LocalDate.now().getYear() + 100) {
            throw new DateTimeException("Data de devolução não pode ser mais de cem anos no futuro");
        }

    }
}
