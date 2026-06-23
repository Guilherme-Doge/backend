package com.weg.jpa_relacionamentos_o_inimigo_agora_e_o_mesmo.mapper;

import com.weg.jpa_relacionamentos_o_inimigo_agora_e_o_mesmo.dto.AlunoResumoDto;
import com.weg.jpa_relacionamentos_o_inimigo_agora_e_o_mesmo.dto.CursoRequestDto;
import com.weg.jpa_relacionamentos_o_inimigo_agora_e_o_mesmo.dto.CursoResponseDto;
import com.weg.jpa_relacionamentos_o_inimigo_agora_e_o_mesmo.model.Aluno;
import com.weg.jpa_relacionamentos_o_inimigo_agora_e_o_mesmo.model.Curso;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CursoMapper {

    public Curso toEntity(CursoRequestDto cursoRequestDto) {
        return new Curso(cursoRequestDto.nome());
    }

    public CursoResponseDto toResponse(Curso curso) {
        List<AlunoResumoDto> alunos = new ArrayList<>();
        for (Aluno aluno : curso.getAlunos()) {
            alunos.add(new AlunoResumoDto(aluno.getId(), aluno.getNome()));
        }
        return new CursoResponseDto(curso.getId(), curso.getNome(), alunos);
    }
}
