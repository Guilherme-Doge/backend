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
    private Long id;

    @Column(name="nome", nullable = false)
    private String nome;

    @Column(name="numero", nullable = false)
    private String numero;

    @Column(name = "numeros")
    @OneToMany(mappedBy = "contato")
    private List<Contato> contatos = new ArrayList<>();

    public Conta(String nome, String numero, List<Contato> contatos) {
        this.nome = nome;
        this.numero = numero;
        this.contatos = contatos;
    }
}
