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
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long numero;

    @Column(nullable = false)
    private String tipo;

    @OneToOne(mappedBy = "documento")
    private Pessoa pessoa;

    public Documento(Long numero, String tipo, Pessoa pessoa) {
        this.numero = numero;
        this.tipo = tipo;
        this.pessoa = pessoa;
    }

    public Documento(Long numero, String tipo) {
        this.numero = numero;
        this.tipo = tipo;
    }
}
