package com.weg.jpa_relacionamentos.service;

import com.weg.jpa_relacionamentos.dto.DepartamentoRequestDto;
import com.weg.jpa_relacionamentos.dto.DepartamentoResponseDto;
import com.weg.jpa_relacionamentos.mapper.DepartamentoMapper;
import com.weg.jpa_relacionamentos.model.Departamento;
import com.weg.jpa_relacionamentos.repo.DepartamentoRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartamentoService {

    private final DepartamentoMapper departamentoMapper;
    private final DepartamentoRepo departamentoRepo;

    public DepartamentoResponseDto post(DepartamentoRequestDto departamentoRequestDto) {
        return departamentoMapper.toResponse(
                departamentoRepo.save(
                        departamentoMapper.toEntity(
                                departamentoRequestDto)));
    }

    public DepartamentoResponseDto get(Long id) {
        Departamento departamento = departamentoRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Departamento não existe"));
        return departamentoMapper.toResponse(departamento);
    }

    public List<DepartamentoResponseDto> list() {
        return departamentoRepo.findAll()
                .stream()
                .map(departamentoMapper::toResponse)
                .toList();
    }

    public DepartamentoResponseDto put(Long id, DepartamentoRequestDto departamentoRequestDto) {
        Departamento departamento = departamentoRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Departamento não existe"));

        if (departamentoRequestDto.name() != null && !departamentoRequestDto.name().isBlank()) {
            departamento.setName(departamentoRequestDto.name());
        }

        return departamentoMapper.toResponse(departamentoRepo.save(departamento));
    }

    public void delete(Long id) {
        if (!departamentoRepo.existsById(id)) {
            throw new RuntimeException("Departamento não existe");
        }
        departamentoRepo.deleteById(id);
    }

}
