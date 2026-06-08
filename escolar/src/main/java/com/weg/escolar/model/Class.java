package com.weg.escolar.model;

import java.util.List;

public class Class {
    private Long id;
    private String nome;
    private Long cursoId;
    private Long professorId;
    private List<Long> listaAlunoIds;
    private List<String> listaAlunoNomes;

    public Class(Long id, String nome, Long cursoId, Long professorId, List<Long> listaAlunoIds, List<String> listaAlunoNomes) {
        this.id = id;
        this.nome = nome;
        this.cursoId = cursoId;
        this.professorId = professorId;
        this.listaAlunoIds = listaAlunoIds;
        this.listaAlunoNomes = listaAlunoNomes;
    }

    public Class(String nome, Long cursoId, Long professorId, List<Long> listaAlunoIds, List<String> listaAlunoNomes) {
        this.nome = nome;
        this.cursoId = cursoId;
        this.professorId = professorId;
        this.listaAlunoNomes = listaAlunoNomes;
    }

    public Class(String nome, Long cursoId, Long professorId, List<Long> listaAlunoIds) {
        this.nome = nome;
        this.cursoId = cursoId;
        this.professorId = professorId;
        this.listaAlunoIds = listaAlunoIds;
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

    public Long getCursoId() {
        return cursoId;
    }

    public void setCursoId(Long cursoId) {
        this.cursoId = cursoId;
    }

    public Long getProfessorId() {
        return professorId;
    }

    public void setProfessorId(Long professorId) {
        this.professorId = professorId;
    }

    public List<Long> getListaAlunoIds() {
        return listaAlunoIds;
    }

    public void setListaAlunoIds(List<Long> listaAlunoIds) {
        this.listaAlunoIds = listaAlunoIds;
    }

    public List<String> getListaAlunoNomes() {
        return listaAlunoNomes;
    }

    public void setListaAlunoNomes(List<String> listaAlunoNomes) {
        this.listaAlunoNomes = listaAlunoNomes;
    }
}
