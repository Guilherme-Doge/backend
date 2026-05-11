package com.weg.escolar.model;

import java.time.LocalDate;

public class Student {
    private Long id;
    private String nome;
    private String email;
    private String matricula;
    private LocalDate dataNascimento;

    public Student(Long id, String nome, String email, String matricula, LocalDate dataNascimento) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.matricula = matricula;
        this.dataNascimento = dataNascimento;
    }

    public Student(String nome, String email, String matricula, LocalDate dataNascimento) {
        this.nome = nome;
        this.email = email;
        this.matricula = matricula;
        this.dataNascimento = dataNascimento;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
}
