package com.weg.jpa_relacionamentos.dto;

import com.weg.jpa_relacionamentos.model.Departamento;

public record FuncionarioRequestDto(
        String name,
        Long departamentoId
) {
}
