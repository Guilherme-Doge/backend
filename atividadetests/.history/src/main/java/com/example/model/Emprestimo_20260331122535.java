package com.example.model;

import java.time.LocalDate;

public class Emprestimo {
    private int id;
    private int livroId;
    private Usuario usuario;
    private LocalDate dataEmprestimo;
    private LocalDate dataDevolucao;

    public Emprestimo(int id, int livroId, Usuario usuario, LocalDate dataEmprestimo, LocalDate dataDevolucao) {
        this.id = id;
        this.livroId = livroId;
        this.usuario = usuario;
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucao = dataDevolucao;
    }

    public Emprestimo(int livroId, Usuario usuario, LocalDate dataEmprestimo, LocalDate dataDevolucao) {
        this.livroId = livroId;
        this.usuario = usuario;
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucao = dataDevolucao;
    }

    
}