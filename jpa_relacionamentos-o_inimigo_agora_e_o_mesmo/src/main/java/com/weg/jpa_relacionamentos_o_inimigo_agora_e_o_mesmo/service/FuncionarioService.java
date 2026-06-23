package com.weg.jpa_relacionamentos_o_inimigo_agora_e_o_mesmo.service;

import com.weg.jpa_relacionamentos_o_inimigo_agora_e_o_mesmo.dto.EnderecoResponseDto;
import com.weg.jpa_relacionamentos_o_inimigo_agora_e_o_mesmo.dto.FuncionarioRequestDto;
import com.weg.jpa_relacionamentos_o_inimigo_agora_e_o_mesmo.dto.FuncionarioResponseDto;
import com.weg.jpa_relacionamentos_o_inimigo_agora_e_o_mesmo.mapper.EnderecoMapper;
import com.weg.jpa_relacionamentos_o_inimigo_agora_e_o_mesmo.mapper.FuncionarioMapper;
import com.weg.jpa_relacionamentos_o_inimigo_agora_e_o_mesmo.model.Endereco;
import com.weg.jpa_relacionamentos_o_inimigo_agora_e_o_mesmo.model.Funcionario;
import com.weg.jpa_relacionamentos_o_inimigo_agora_e_o_mesmo.repo.EnderecoRepo;
import com.weg.jpa_relacionamentos_o_inimigo_agora_e_o_mesmo.repo.FuncionarioRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FuncionarioService {

    private final FuncionarioRepo funcionarioRepo;
    private final EnderecoRepo enderecoRepo;
    private final FuncionarioMapper funcionarioMapper;
    private final EnderecoMapper enderecoMapper;

    public FuncionarioResponseDto create(FuncionarioRequestDto funcionarioRequestDto) {
        Funcionario funcionario = funcionarioMapper.toEntity(funcionarioRequestDto);

        if (funcionarioRepo.existsByNome(funcionario.getNome())) {
            throw new RuntimeException("Funcionário já cadastrado!");
        }

        if (enderecoRepo.existsByRuaAndNumero(
                funcionarioRequestDto.endereco().getRua(),
                funcionarioRequestDto.endereco().getNumero())) {
            throw new RuntimeException("Endereço já cadastrado!");
        }

        Endereco endereco = funcionario.getEndereco();
        endereco.setFuncionario(funcionario);
        funcionario.setEndereco(endereco);

        funcionarioRepo.save(funcionario);

        Endereco enderecoSalvo = enderecoRepo.findByFuncionarioId(funcionario.getId());
        EnderecoResponseDto enderecoResponseDto = enderecoMapper.toResponse(enderecoSalvo);

        return funcionarioMapper.toResponse(funcionario, enderecoResponseDto);
    }

    public FuncionarioResponseDto getById(Long id) {
        Funcionario funcionario = funcionarioRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Funcionário não encontrado!"));

        Endereco endereco = enderecoRepo.findByFuncionarioId(funcionario.getId());
        EnderecoResponseDto enderecoResponseDto = enderecoMapper.toResponse(endereco);

        return funcionarioMapper.toResponse(funcionario, enderecoResponseDto);
    }
}
