package com.weg.jpa.dto;

import com.weg.jpa.model.Conta;

public record ContatoResponseDto(
        Long id,
        String nome,
        String numero,
        Conta conta
) {

}
