package com.senai.minha_primeira_api.dto;

public record ContatoResponseDto(
        Long id,
        String nome,
        String numero
) {
}
