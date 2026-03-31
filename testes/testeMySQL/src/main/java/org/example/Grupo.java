package org.example;

import java.util.ArrayList;

public class Grupo {
    private int id;
    private String nome;
    private ArrayList<Contato> contatos;

    public Grupo(int id, String nome, ArrayList<Contato> contatos) {
        this.id = id;
        this.nome = nome;
        this.contatos = contatos;
    }

    public Grupo(String nome, ArrayList<Contato> contatos) {
        this.contatos = contatos;
        this.nome = nome;
    }

    public Grupo(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public ArrayList<Contato> getContatos() {
        return contatos;
    }

    public void setContatos(ArrayList<Contato> contatos) {
        this.contatos = contatos;
    }
}
