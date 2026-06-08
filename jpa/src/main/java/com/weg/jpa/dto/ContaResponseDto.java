package com.weg.jpa.dto;

import com.weg.jpa.model.Contato;

import java.util.List;

public record ContaResponseDto(
        Long id,
        String nome,
        String numero,
        List<Contato> contatos
) {
}