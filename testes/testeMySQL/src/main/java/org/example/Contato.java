package org.example;

import java.util.ArrayList;

public class Contato {
    private int id;
    private String nome;
    private String numero;
    private ArrayList<Grupo> grupos;

    public Contato(int id, String nome, String numero) {
        this.id = id;
        this.nome = nome;
        this.numero = numero;
        this.grupos = new ArrayList<Grupo>();
    }

    public Contato(String nome, String numero) {
        this.nome = nome;
        this.numero = numero;
        this.grupos = new ArrayList<Grupo>();
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getNumero() {
        return numero;
    }

    public ArrayList<Grupo> getGrupos() {
        return grupos;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public void setGrupos(ArrayList<Grupo> grupos) {
        this.grupos = grupos;
    }
}
