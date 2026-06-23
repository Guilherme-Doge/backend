package com.weg.jpa_relacionamentos_o_inimigo_agora_e_o_mesmo.dto;

import com.weg.jpa_relacionamentos_o_inimigo_agora_e_o_mesmo.model.Endereco;

public record FuncionarioRequestDto(
        String nome,
        Endereco endereco
) {
}
