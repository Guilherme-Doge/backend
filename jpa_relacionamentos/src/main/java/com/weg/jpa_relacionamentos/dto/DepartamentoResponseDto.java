package com.weg.jpa_relacionamentos.dto;

import java.util.List;

public record DepartamentoResponseDto(
        Long id,
        String name,
        List<FuncionarioResponseDto> funcionarios
) {
}
