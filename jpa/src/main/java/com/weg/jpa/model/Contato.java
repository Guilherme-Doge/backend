package com.weg.jpa.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="contato")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Contato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contato_id")
    private Long id;

    @Column(name = "nome", nullable = true)
    private String nome;

    @Column(name = "numero", nullable = true)
    private String numero;

    @ManyToOne
    @JoinColumn(name = "conta_id", nullable = false)
    private Conta conta;

    public Contato(String nome, String numero, Conta conta) {
        this.nome = nome;
        this.numero = numero;
        this.conta = conta;
    }
}