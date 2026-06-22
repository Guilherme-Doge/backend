package com.weg.jpa_relacionamentos.mapper;

import com.weg.jpa_relacionamentos.dto.ProjetoRequestDto;
import com.weg.jpa_relacionamentos.dto.ProjetoResponseDto;
import com.weg.jpa_relacionamentos.dto.ProjetoTarefaResumoDto;
import com.weg.jpa_relacionamentos.model.Projeto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProjetoMapper {
    public Projeto toEntity(ProjetoRequestDto dto) {
        return new Projeto(dto.nome());
    }

    public ProjetoResponseDto toResponse(Projeto projeto) {
        List<ProjetoTarefaResumoDto> tarefas = projeto.getTarefas() == null ? List.of() : projeto.getTarefas().stream()
                .map(tarefa -> new ProjetoTarefaResumoDto(tarefa.getId(), tarefa.getTitulo()))
                .toList();
        return new ProjetoResponseDto(projeto.getId(), projeto.getNome(), tarefas);
    }
}
