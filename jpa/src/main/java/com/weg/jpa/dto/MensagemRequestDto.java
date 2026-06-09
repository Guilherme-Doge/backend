package com.weg.jpa.dto;

import com.weg.jpa.model.Conta;
import com.weg.jpa.model.Conversa;

import java.time.LocalDateTime;

public record MensagemRequestDto(
        String texto,
        LocalDateTime dataEnvio,
        Conversa conversa,
        Conta remetente
) {
}
