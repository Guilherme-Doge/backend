package com.weg.jpa_relacionamentos.service;

import com.weg.jpa_relacionamentos.dto.TarefaRequestDto;
import com.weg.jpa_relacionamentos.dto.TarefaResponseDto;
import com.weg.jpa_relacionamentos.mapper.TarefaMapper;
import com.weg.jpa_relacionamentos.model.Projeto;
import com.weg.jpa_relacionamentos.model.Tarefa;
import com.weg.jpa_relacionamentos.repo.ProjetoRepo;
import com.weg.jpa_relacionamentos.repo.TarefaRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TarefaService {
    private final TarefaMapper tarefaMapper;
    private final TarefaRepo tarefaRepo;
    private final ProjetoRepo projetoRepo;

    public TarefaResponseDto post(TarefaRequestDto dto) {
        Projeto projeto = projetoRepo.findById(dto.projetoId()).orElseThrow(() -> new RuntimeException("Projeto não existe"));
        Tarefa tarefa = tarefaMapper.toEntity(dto, projeto);

        return tarefaMapper.toResponse(tarefaRepo.save(tarefa));
    }

    public TarefaResponseDto get(Long id) {
        Tarefa tarefa = tarefaRepo.findById(id).orElseThrow(() -> new RuntimeException("Tarefa não existe"));
        return tarefaMapper.toResponse(tarefa);
    }

    public List<TarefaResponseDto> list() {
        return tarefaRepo.findAll().stream().map(tarefaMapper::toResponse).toList();
    }

    public TarefaResponseDto put(Long id, TarefaRequestDto dto) {
        Tarefa tarefa = tarefaRepo.findById(id).orElseThrow(() -> new RuntimeException("Tarefa não existe"));

        if (dto.titulo() != null && !dto.titulo().isBlank()) {
            tarefa.setTitulo(dto.titulo());
        }

        if (dto.descricao() != null) {
            tarefa.setDescricao(dto.descricao());
        }

        if (dto.projetoId() != null) {
            Projeto projeto = projetoRepo.findById(dto.projetoId()).orElseThrow(() -> new RuntimeException("Projeto não existe"));
            tarefa.setProjeto(projeto);
        }
        return tarefaMapper.toResponse(tarefaRepo.save(tarefa));
    }

    public void delete(Long id) {
        if (!tarefaRepo.existsById(id)) {
            throw new RuntimeException("Tarefa não existe");
        }
        tarefaRepo.deleteById(id);
    }
}
