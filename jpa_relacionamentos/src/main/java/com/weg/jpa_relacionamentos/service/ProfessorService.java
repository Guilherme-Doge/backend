package com.weg.jpa_relacionamentos.service;

import com.weg.jpa_relacionamentos.dto.ProfessorRequestDto;
import com.weg.jpa_relacionamentos.dto.ProfessorResponseDto;
import com.weg.jpa_relacionamentos.mapper.ProfessorMapper;
import com.weg.jpa_relacionamentos.model.Professor;
import com.weg.jpa_relacionamentos.repo.ProfessorRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProfessorService {
    private final ProfessorMapper professorMapper;
    private final ProfessorRepo professorRepo;

    @Transactional
    public ProfessorResponseDto post(ProfessorRequestDto dto) {
        if (dto.nome() == null || dto.nome().isBlank()) {
            throw new RuntimeException("Informe o nome do professor");
        }
        return professorMapper.toResponse(professorRepo.save(professorMapper.toEntity(dto)));
    }

    @Transactional(readOnly = true)
    public ProfessorResponseDto get(Long id) {
        Professor professor = professorRepo.findById(id).orElseThrow(() -> new RuntimeException("Professor não existe"));
        return professorMapper.toResponse(professor);
    }

    @Transactional(readOnly = true)
    public List<ProfessorResponseDto> list() {
        return professorRepo.findAll().stream().map(professorMapper::toResponse).toList();
    }

    @Transactional
    public ProfessorResponseDto put(Long id, ProfessorRequestDto dto) {
        Professor professor = professorRepo.findById(id).orElseThrow(() -> new RuntimeException("Professor não existe"));
        if (dto.nome() != null && !dto.nome().isBlank()) {
            professor.setNome(dto.nome());
        }
        return professorMapper.toResponse(professorRepo.save(professor));
    }

    @Transactional
    public void delete(Long id) {
        if (!professorRepo.existsById(id)) {
            throw new RuntimeException("Professor não existe");
        }
        professorRepo.deleteById(id);
    }
}
