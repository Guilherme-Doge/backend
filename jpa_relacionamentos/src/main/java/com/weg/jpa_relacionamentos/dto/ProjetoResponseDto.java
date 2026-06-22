package com.weg.jpa_relacionamentos.dto;

import java.util.List;

public record ProjetoResponseDto(
        Long id,
        String nome,
        List<ProjetoTarefaResumoDto> tarefas
) {}
