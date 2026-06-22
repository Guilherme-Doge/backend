package com.weg.jpa_relacionamentos.dto;

import com.weg.jpa_relacionamentos.model.Funcionario;

import java.util.List;

public record DepartamentoRequestDto(
        String name,
        List<Funcionario> funcionarios
) {
}
