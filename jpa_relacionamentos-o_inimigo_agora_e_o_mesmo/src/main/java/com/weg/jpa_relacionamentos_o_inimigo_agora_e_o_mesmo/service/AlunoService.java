package com.weg.jpa_relacionamentos_o_inimigo_agora_e_o_mesmo.service;

import com.weg.jpa_relacionamentos_o_inimigo_agora_e_o_mesmo.dto.AlunoRequestDto;
import com.weg.jpa_relacionamentos_o_inimigo_agora_e_o_mesmo.dto.AlunoResponseDto;
import com.weg.jpa_relacionamentos_o_inimigo_agora_e_o_mesmo.dto.CursoResumoDto;
import com.weg.jpa_relacionamentos_o_inimigo_agora_e_o_mesmo.mapper.AlunoMapper;
import com.weg.jpa_relacionamentos_o_inimigo_agora_e_o_mesmo.mapper.CursoMapper;
import com.weg.jpa_relacionamentos_o_inimigo_agora_e_o_mesmo.model.Aluno;
import com.weg.jpa_relacionamentos_o_inimigo_agora_e_o_mesmo.model.Curso;
import com.weg.jpa_relacionamentos_o_inimigo_agora_e_o_mesmo.repo.AlunoRepo;
import com.weg.jpa_relacionamentos_o_inimigo_agora_e_o_mesmo.repo.CursoRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AlunoService {

    private final AlunoRepo alunoRepo;
    private final CursoRepo cursoRepo;
    private final AlunoMapper alunoMapper;
    private final CursoMapper cursoMapper;

    public AlunoResponseDto create(AlunoRequestDto alunoRequestDto) {
        Aluno aluno = alunoMapper.toEntity(alunoRequestDto);

        if (alunoRepo.existsByNome(aluno.getNome())) {
            throw new RuntimeException("Aluno já cadastrado!");
        }

        alunoRepo.save(aluno);
        return alunoMapper.toResponse(aluno);
    }

    public AlunoResponseDto matricular(Long alunoId, Long cursoId) {
        Aluno aluno = alunoRepo.findById(alunoId)
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado!"));

        Curso curso = cursoRepo.findById(cursoId)
                .orElseThrow(() -> new RuntimeException("Curso não encontrado!"));

        for (Curso cursoExistente : aluno.getCursos()) {
            if (cursoExistente.getId().equals(cursoId)) {
                throw new RuntimeException("Aluno já matriculado nesse curso!");
            }
        }

        aluno.getCursos().add(curso);
        curso.getAlunos().add(aluno);

        alunoRepo.save(aluno);

        return alunoMapper.toResponse(aluno);
    }

    public AlunoResponseDto getById(Long id) {
        Aluno aluno = alunoRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado!"));

        return alunoMapper.toResponse(aluno);
    }
}
