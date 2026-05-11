package com.weg.escolar.model;

public class Teacher {
    private Long id;
    private String nome;
    private String email;
    private String disciplina;

    public Teacher(Long id, String nome, String email, String disciplina) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.disciplina = disciplina;
    }

    public Teacher(String nome, String email, String disciplina) {
        this.nome = nome;
        this.email = email;
        this.disciplina = disciplina;
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

    public String getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(String disciplina) {
        this.disciplina = disciplina;
    }
}
