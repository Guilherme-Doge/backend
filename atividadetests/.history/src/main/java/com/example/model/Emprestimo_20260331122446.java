package com.example.model;

import java.time.LocalDate;

public class Emprestimo {
    private final int id;
    private final int livroId;
    private final Usuario usuario;
    private final LocalDate dataEmprestimo;
    private final LocalDate dataDevolucao;
}