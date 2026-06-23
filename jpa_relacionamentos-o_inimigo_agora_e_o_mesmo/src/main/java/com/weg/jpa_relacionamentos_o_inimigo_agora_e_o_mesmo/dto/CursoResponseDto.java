package com.weg.jpa_relacionamentos_o_inimigo_agora_e_o_mesmo.dto;

import java.util.List;

public record CursoResponseDto(
        Long id,
        String nome,
        List<AlunoResumoDto> alunos
) {
}
