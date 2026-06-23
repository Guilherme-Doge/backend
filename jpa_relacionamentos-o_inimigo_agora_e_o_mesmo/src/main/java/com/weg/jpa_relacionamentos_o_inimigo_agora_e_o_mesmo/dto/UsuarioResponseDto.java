package com.weg.jpa_relacionamentos_o_inimigo_agora_e_o_mesmo.dto;

import java.util.List;

public record UsuarioResponseDto(
        Long id,
        String nome,
        ContatoResponseDto contato,
        List<LivroResponseDto> livrosEmprestados
) {
}
