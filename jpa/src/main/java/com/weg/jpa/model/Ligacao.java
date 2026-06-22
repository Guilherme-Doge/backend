package com.weg.jpa.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "ligacao")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Ligacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "data_hora", nullable = false)
    private LocalDateTime dataHora = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "conta_id", referencedColumnName = "conta_id", nullable = false)
    private Conta conta;

    @ManyToOne
    @JoinColumn(name = "contato_id", referencedColumnName = "contato_id", nullable = false)
    private Contato contato;

    public Ligacao(LocalDateTime dataHora, Conta conta, Contato contato) {
        this.dataHora = dataHora;
        this.conta = conta;
        this.contato = contato;
    }
}