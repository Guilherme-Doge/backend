package com.weg.jpa_relacionamentos.mapper;

import com.weg.jpa_relacionamentos.dto.TarefaRequestDto;
import com.weg.jpa_relacionamentos.dto.TarefaResponseDto;
import com.weg.jpa_relacionamentos.model.Projeto;
import com.weg.jpa_relacionamentos.model.Tarefa;
import org.springframework.stereotype.Component;

@Component
public class TarefaMapper {
    public Tarefa toEntity(TarefaRequestDto dto, Projeto projeto) {
        Tarefa tarefa = new Tarefa();
        tarefa.setTitulo(dto.titulo());
        tarefa.setDescricao(dto.descricao());
        tarefa.setProjeto(projeto);
        return tarefa;
    }

    public TarefaResponseDto toResponse(Tarefa tarefa) {
        return new TarefaResponseDto(
                tarefa.getId(),
                tarefa.getTitulo(),
                tarefa.getDescricao(),
                tarefa.getProjeto() != null ? tarefa.getProjeto().getId() : null,
                tarefa.getProjeto() != null ? tarefa.getProjeto().getNome() : null
        );
    }
}
