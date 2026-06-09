package com.weg.jpa.dto;

import com.weg.jpa.model.Conta;
import com.weg.jpa.model.Contato;
import jakarta.persistence.*;

import java.time.LocalDateTime;

public record LigacaoResponseDto(
        Long id,
        LocalDateTime dataHora,
        Conta conta,
        Contato contato
) {
}
