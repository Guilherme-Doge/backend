package com.weg.jpa_relacionamentos_o_inimigo_agora_e_o_mesmo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
public class Funcionario {

    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @OneToOne(mappedBy = "funcionario", cascade = CascadeType.ALL)
    private Endereco endereco;

    public Funcionario(String nome, Endereco endereco) {
        this.nome = nome;
        this.endereco = endereco;
    }
}
