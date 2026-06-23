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
public class Endereco {

    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String rua;

    @Column(nullable = false)
    private String numero;

    @OneToOne
    @JoinColumn(name = "funcionario_id")
    private Funcionario funcionario;

    public Endereco(String rua, String numero) {
        this.rua = rua;
        this.numero = numero;
    }
}
