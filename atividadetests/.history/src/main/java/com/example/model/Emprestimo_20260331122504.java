package com.example.model;

import java.time.LocalDate;

public class Emprestimo {
    private final int id;
    private final int livroId;
    private final Usuario usuario;
    private final LocalDate dataEmprestimo;
    private final LocalDate dataDevolucao;
    public Emprestimo(int id, int livroId, Usuario usuario, LocalDate dataEmprestimo, LocalDate dataDevolucao) {
        this.id = id;
        this.livroId = livroId;
        this.usuario = usuario;
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucao = dataDevolucao;
    }

    
}