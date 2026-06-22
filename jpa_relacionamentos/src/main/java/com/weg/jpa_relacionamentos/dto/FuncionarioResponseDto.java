package com.weg.jpa_relacionamentos.dto;

import com.weg.jpa_relacionamentos.model.Departamento;

public record FuncionarioResponseDto(
        Long id,
        String name,
        String departamentoName
) {
}
