package com.weg.jpa.dto;

import com.weg.jpa.model.Contato;
import com.weg.jpa.model.Grupo;
import com.weg.jpa.model.Ligacao;
import com.weg.jpa.model.Mensagem;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

public record ContaResponseDto(
        Long id,
        String nome,
        String numero,
        List<Contato> contatos,
        List<Grupo> grupos,
        List<Mensagem> mensagensEnviadas,
        List<Ligacao> ligacoes
) {
}