package com.weg.jpa.dto;

import com.weg.jpa.model.Conta;

public record ContatoRequestDto(
        String nome,
        String numero,
        Conta conta
) {
}
