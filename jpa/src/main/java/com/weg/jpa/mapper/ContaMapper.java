package com.weg.jpa.mapper;

import com.weg.jpa.dto.ContaRequestDto;
import com.weg.jpa.dto.ContaResponseDto;
import com.weg.jpa.model.Conta;
import org.springframework.stereotype.Component;

@Component
public class ContaMapper {
    public Conta toEntity(ContaRequestDto contaRequestDto) {
        return new Conta(
                contaRequestDto.nome(),
                contaRequestDto.numero(),
                contaRequestDto.contatos(),
                contaRequestDto.grupos(),
                contaRequestDto.mensagensEnviadas(),
                contaRequestDto.ligacoes()
        );
    }

    public ContaResponseDto toResponse(Conta conta) {
        return new ContaResponseDto(
                conta.getId(),
                conta.getNome(),
                conta.getNumero(),
                conta.getContatos(),
                conta.getGrupos(),
                conta.getMensagensEnviadas(),
                conta.getLigacoes()
        );
    }
}
