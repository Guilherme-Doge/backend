package com.weg.jpa_relacionamentos_o_inimigo_agora_e_o_mesmo.mapper;

import com.weg.jpa_relacionamentos_o_inimigo_agora_e_o_mesmo.dto.AlunoRequestDto;
import com.weg.jpa_relacionamentos_o_inimigo_agora_e_o_mesmo.dto.AlunoResponseDto;
import com.weg.jpa_relacionamentos_o_inimigo_agora_e_o_mesmo.dto.CursoResumoDto;
import com.weg.jpa_relacionamentos_o_inimigo_agora_e_o_mesmo.model.Aluno;
import com.weg.jpa_relacionamentos_o_inimigo_agora_e_o_mesmo.model.Curso;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AlunoMapper {

    public Aluno toEntity(AlunoRequestDto alunoRequestDto) {
        return new Aluno(alunoRequestDto.nome());
    }

    public AlunoResponseDto toResponse(Aluno aluno) {
        List<CursoResumoDto> cursos = new ArrayList<>();
        for (Curso curso : aluno.getCursos()) {
            cursos.add(new CursoResumoDto(curso.getId(), curso.getNome()));
        }
        return new AlunoResponseDto(aluno.getId(), aluno.getNome(), cursos);
    }
}
