package com.weg.escolar.dto;

import java.util.List;

public record ClassResponseDto(
        Long cursoId,
        Long professorId,
        List<String> listaAlunoNomes
) {
}
