package com.weg.jpa_relacionamentos_o_inimigo_agora_e_o_mesmo.mapper;

import com.weg.jpa_relacionamentos_o_inimigo_agora_e_o_mesmo.dto.EnderecoResponseDto;
import com.weg.jpa_relacionamentos_o_inimigo_agora_e_o_mesmo.dto.FuncionarioRequestDto;
import com.weg.jpa_relacionamentos_o_inimigo_agora_e_o_mesmo.dto.FuncionarioResponseDto;
import com.weg.jpa_relacionamentos_o_inimigo_agora_e_o_mesmo.model.Funcionario;
import org.springframework.stereotype.Component;

@Component
public class FuncionarioMapper {

    public FuncionarioResponseDto toResponse(Funcionario funcionario, EnderecoResponseDto endereco) {
        return new FuncionarioResponseDto(funcionario.getId(), funcionario.getNome(), endereco);
    }

    public Funcionario toEntity(FuncionarioRequestDto funcionarioRequestDto) {
        return new Funcionario(funcionarioRequestDto.nome(), funcionarioRequestDto.endereco());
    }
}
