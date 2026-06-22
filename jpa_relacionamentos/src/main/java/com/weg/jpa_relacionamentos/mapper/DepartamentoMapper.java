package com.weg.jpa_relacionamentos.mapper;

import com.weg.jpa_relacionamentos.dto.DepartamentoRequestDto;
import com.weg.jpa_relacionamentos.dto.DepartamentoResponseDto;
import com.weg.jpa_relacionamentos.dto.FuncionarioResponseDto;
import com.weg.jpa_relacionamentos.model.Departamento;
import com.weg.jpa_relacionamentos.model.Funcionario;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DepartamentoMapper {
    public Departamento toEntity(DepartamentoRequestDto departamentoRequestDto) {
        return new Departamento(
                departamentoRequestDto.name(),
                new ArrayList<>()
        );
    }

    public DepartamentoResponseDto toResponse(Departamento departamento) {
        List<FuncionarioResponseDto> funcionarios = departamento.getFuncionarios() == null
                ? List.of()
                : departamento.getFuncionarios().stream()
                .map(funcionario -> new FuncionarioResponseDto(
                        funcionario.getId(),
                        funcionario.getName(),
                        funcionario.getDepartamento() != null ? funcionario.getDepartamento().getName() : null
                ))
                .toList();

        return new DepartamentoResponseDto(
                departamento.getId(),
                departamento.getName(),
                funcionarios
        );
    }
}
