package com.weg.jpa.dto;

import com.weg.jpa.model.Grupo;
import com.weg.jpa.model.Mensagem;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

public record ConversaRequestDto(
        List<Mensagem> mensagens,
        Grupo grupo
) {
}
