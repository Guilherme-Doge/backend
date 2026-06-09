package com.weg.jpa.dto;

import com.weg.jpa.model.Conta;
import com.weg.jpa.model.Conversa;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

public record GrupoResponseDto(
        Long id,
        String nome,
        Conversa conversa,
        List<Conta> membros

) {
}
