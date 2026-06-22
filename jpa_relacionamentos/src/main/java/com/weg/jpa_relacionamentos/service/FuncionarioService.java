package com.weg.jpa_relacionamentos.service;

import com.weg.jpa_relacionamentos.dto.FuncionarioRequestDto;
import com.weg.jpa_relacionamentos.dto.FuncionarioResponseDto;
import com.weg.jpa_relacionamentos.mapper.FuncionarioMapper;
import com.weg.jpa_relacionamentos.model.Departamento;
import com.weg.jpa_relacionamentos.model.Funcionario;
import com.weg.jpa_relacionamentos.repo.DepartamentoRepo;
import com.weg.jpa_relacionamentos.repo.FuncionarioRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FuncionarioService {

    private final FuncionarioMapper funcionarioMapper;
    private final FuncionarioRepo funcionarioRepo;
    private final DepartamentoRepo departamentoRepo;

    public FuncionarioResponseDto post(FuncionarioRequestDto funcionarioRequestDto) {
        if (funcionarioRequestDto.departamentoId() == null) {
            throw new RuntimeException("Informe o id do departamento");
        }

        Departamento departamento = departamentoRepo.findById(funcionarioRequestDto.departamentoId())
                .orElseThrow(() -> new RuntimeException("Departamento não existe"));

        Funcionario funcionario = funcionarioMapper.toEntity(funcionarioRequestDto);
        funcionario.setDepartamento(departamento);

        return funcionarioMapper.toResponse(
                funcionarioRepo.save(funcionario));
    }

    public FuncionarioResponseDto get(Long id) {
        Funcionario funcionario = funcionarioRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Funcionario não existe"));
        return funcionarioMapper.toResponse(funcionario);
    }

    public List<FuncionarioResponseDto> list() {
        return funcionarioRepo.findAll()
                .stream()
                .map(funcionarioMapper::toResponse)
                .toList();
    }

    public FuncionarioResponseDto put(Long id, FuncionarioRequestDto funcionarioRequestDto) {
        Funcionario funcionario = funcionarioRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Funcionario não existe"));

        if (funcionarioRequestDto.name() != null && !funcionarioRequestDto.name().isBlank()) {
            funcionario.setName(funcionarioRequestDto.name());
        }

        if (funcionarioRequestDto.departamentoId() != null) {
            Departamento departamento = departamentoRepo.findById(funcionarioRequestDto.departamentoId())
                    .orElseThrow(() -> new RuntimeException("Departamento não existe"));
            funcionario.setDepartamento(departamento);
        }

        return funcionarioMapper.toResponse(funcionarioRepo.save(funcionario));
    }

    public void delete(Long id) {
        if (!funcionarioRepo.existsById(id)) {
            throw new RuntimeException("Funcionario não existe");
        }
        funcionarioRepo.deleteById(id);
    }

}
