package com.weg.jpa.dto;

import com.weg.jpa.model.Contato;
import com.weg.jpa.model.Grupo;
import com.weg.jpa.model.Ligacao;
import com.weg.jpa.model.Mensagem;

import java.util.List;

public record ContaRequestDto(
        String nome,
        String numero,
        List<Contato> contatos,
        List<Grupo> grupos,
        List<Mensagem> mensagensEnviadas,
        List<Ligacao> ligacoes
) {
}
