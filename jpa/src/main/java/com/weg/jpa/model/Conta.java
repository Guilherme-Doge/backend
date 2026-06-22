package com.weg.jpa.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="conta")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Conta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "conta_id")
    private Long id;

    @Column(name="nome", nullable = false)
    private String nome;

    @Column(name="numero", nullable = false)
    private String numero;

    @OneToMany(mappedBy = "conta", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Contato> contatos = new ArrayList<>();

    @ManyToMany(mappedBy = "membros")
    private List<Grupo> grupos = new ArrayList<>();

    @OneToMany(mappedBy = "remetente")
    private List<Mensagem> mensagensEnviadas = new ArrayList<>();

    @OneToMany(mappedBy = "conta", cascade = CascadeType.ALL)
    private List<Ligacao> ligacoes = new ArrayList<>();

    public Conta(String nome, String numero, List<Contato> contatos, List<Grupo> grupos, List<Mensagem> mensagensEnviadas, List<Ligacao> ligacoes) {
        this.nome = nome;
        this.numero = numero;
        this.contatos = contatos;
        this.grupos = grupos;
        this.mensagensEnviadas = mensagensEnviadas;
        this.ligacoes = ligacoes;
    }
}