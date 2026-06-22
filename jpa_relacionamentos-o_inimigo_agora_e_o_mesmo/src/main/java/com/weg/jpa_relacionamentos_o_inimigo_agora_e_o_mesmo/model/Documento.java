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
public class Documento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long numero;

    @OneToOne(mappedBy = "documento")
    private Pessoa pessoa;

    public Documento(Long numero, Pessoa pessoa) {
        this.numero = numero;
        this.pessoa = pessoa;
    }
}