package com.weg.escolar.model;

public class Grade {
    private Long id;
    private Long alunoId;
    private Long aulaId;
    private Long valorId;
    private String alunoNome;
    private String aulaAssunto;
    private Double valor;

    public Grade(Long id, Long alunoId, Long aulaId, Long valorId, String alunoNome, String aulaAssunto, Double valor) {
        this.id = id;
        this.alunoId = alunoId;
        this.aulaId = aulaId;
        this.valorId = valorId;
        this.alunoNome = alunoNome;
        this.aulaAssunto = aulaAssunto;
        this.valor = valor;
    }

    public Grade(Long alunoId, Long aulaId, Long valorId, String alunoNome, String aulaAssunto, Double valor) {
        this.alunoId = alunoId;
        this.aulaId = aulaId;
        this.valorId = valorId;
        this.alunoNome = alunoNome;
        this.aulaAssunto = aulaAssunto;
        this.valor = valor;
    }

    public Grade(String alunoNome, String aulaAssunto, Double valor) {
        this.alunoNome = alunoNome;
        this.aulaAssunto = aulaAssunto;
        this.valor = valor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAlunoId() {
        return alunoId;
    }

    public void setAlunoId(Long alunoId) {
        this.alunoId = alunoId;
    }

    public Long getAulaId() {
        return aulaId;
    }

    public void setAulaId(Long aulaId) {
        this.aulaId = aulaId;
    }

    public Long getValorId() {
        return valorId;
    }

    public void setValorId(Long valorId) {
        this.valorId = valorId;
    }

    public String getAlunoNome() {
        return alunoNome;
    }

    public void setAlunoNome(String alunoNome) {
        this.alunoNome = alunoNome;
    }

    public String getAulaAssunto() {
        return aulaAssunto;
    }

    public void setAulaAssunto(String aulaAssunto) {
        this.aulaAssunto = aulaAssunto;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }
}
