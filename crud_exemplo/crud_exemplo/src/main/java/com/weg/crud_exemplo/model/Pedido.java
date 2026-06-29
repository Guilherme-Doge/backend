package com.weg.crud_exemplo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate dataCriacao;

    private String nomeCliente;

    @OneToMany(mappedBy = "pedido")
    List<ItemPedido> itens;

    public Pedido(LocalDate dataCriacao, String nomeCliente) {
        this.dataCriacao = dataCriacao;
        this.nomeCliente = nomeCliente;
    }

    public Pedido(LocalDate dataCriacao, String nomeCliente, List<ItemPedido> itens) {
        this.dataCriacao = dataCriacao;
        this.nomeCliente = nomeCliente;
        this.itens = itens;
    }

    public Pedido(Long id) {
        this.id = id;
    }
}
