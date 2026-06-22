package com.weg.jpa_relacionamentos.service;

import com.weg.jpa_relacionamentos.dto.CursoRequestDto;
import com.weg.jpa_relacionamentos.dto.CursoResponseDto;
import com.weg.jpa_relacionamentos.mapper.CursoMapper;
import com.weg.jpa_relacionamentos.model.Curso;
import com.weg.jpa_relacionamentos.model.Professor;
import com.weg.jpa_relacionamentos.repo.CursoRepo;
import com.weg.jpa_relacionamentos.repo.ProfessorRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CursoService {
    private final CursoMapper cursoMapper;
    private final CursoRepo cursoRepo;
    private final ProfessorRepo professorRepo;

    public CursoResponseDto post(CursoRequestDto dto) {
        if (dto.professorId() == null) {
            throw new RuntimeException("Informe o id do professor");
        }
        Professor professor = professorRepo.findById(dto.professorId()).orElseThrow(() -> new RuntimeException("Professor não existe"));
        Curso curso = cursoMapper.toEntity(dto, professor);
        return cursoMapper.toResponse(cursoRepo.save(curso));
    }

    public CursoResponseDto get(Long id) {
        Curso curso = cursoRepo.findById(id).orElseThrow(() -> new RuntimeException("Curso não existe"));
        return cursoMapper.toResponse(curso);
    }

    public List<CursoResponseDto> list() {
        return cursoRepo.findAll().stream().map(cursoMapper::toResponse).toList();
    }

    public CursoResponseDto put(Long id, CursoRequestDto dto) {
        Curso curso = cursoRepo.findById(id).orElseThrow(() -> new RuntimeException("Curso não existe"));
        if (dto.titulo() != null && !dto.titulo().isBlank()) {
            curso.setTitulo(dto.titulo());
        }
        if (dto.professorId() != null) {
            Professor professor = professorRepo.findById(dto.professorId()).orElseThrow(() -> new RuntimeException("Professor não existe"));
            curso.setProfessor(professor);
        }
        return cursoMapper.toResponse(cursoRepo.save(curso));
    }

    public void delete(Long id) {
        if (!cursoRepo.existsById(id)) {
            throw new RuntimeException("Curso não existe");
        }
        cursoRepo.deleteById(id);
    }
}
