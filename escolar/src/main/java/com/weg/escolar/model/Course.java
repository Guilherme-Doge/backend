package com.weg.escolar.model;

import java.util.List;

public class Course {
    private Long id;
    private String nome;
    private String codigo;
    private List<Long> listaProfessorIds;

    public Course(Long id, String nome, String codigo, List<Long> listaProfessorIds) {
        this.id = id;
        this.nome = nome;
        this.codigo = codigo;
        this.listaProfessorIds = listaProfessorIds;
    }

    public Course(String nome, String codigo, List<Long> listaProfessorIds) {
        this.nome = nome;
        this.codigo = codigo;
        this.listaProfessorIds = listaProfessorIds;
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

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public List<Long> getListaProfessorIds() {
        return listaProfessorIds;
    }

    public void setListaProfessorIds(List<Long> listaProfessorIds) {
        this.listaProfessorIds = listaProfessorIds;
    }
}