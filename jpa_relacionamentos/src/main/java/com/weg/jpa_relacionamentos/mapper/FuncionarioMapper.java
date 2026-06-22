package com.weg.jpa_relacionamentos.mapper;

import com.weg.jpa_relacionamentos.dto.FuncionarioRequestDto;
import com.weg.jpa_relacionamentos.dto.FuncionarioResponseDto;
import com.weg.jpa_relacionamentos.model.Departamento;
import com.weg.jpa_relacionamentos.model.Funcionario;
import org.springframework.stereotype.Component;

@Component
public class FuncionarioMapper {
    public Funcionario toEntity(FuncionarioRequestDto funcionarioRequestDto) {
        Funcionario funcionario = new Funcionario();
        funcionario.setName(funcionarioRequestDto.name());
        if (funcionarioRequestDto.departamentoId() != null) {
            funcionario.setDepartamento(new Departamento(funcionarioRequestDto.departamentoId()));
        }
        return funcionario;
    }

    public FuncionarioResponseDto toResponse(Funcionario funcionario) {
        return new FuncionarioResponseDto(
                funcionario.getId(),
                funcionario.getName(),
                funcionario.getDepartamento() != null ? funcionario.getDepartamento().getName() : null
        );
    }
}
