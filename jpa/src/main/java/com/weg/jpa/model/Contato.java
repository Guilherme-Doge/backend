package com.weg.jpa.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="contato")
@Getter
@Setter
public class Contato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = true)
    private String nome;

    @Column(name = "numero", nullable = true)
    private String numero;

    @Column(name = "conta", nullable = false)
    @ManyToOne
    @JoinColumn(name = "conta_id")
    private Conta conta;

    public Contato(String nome, String numero, Conta conta) {
        this.nome = nome;
        this.numero = numero;
        this.conta = conta;
    }
}
