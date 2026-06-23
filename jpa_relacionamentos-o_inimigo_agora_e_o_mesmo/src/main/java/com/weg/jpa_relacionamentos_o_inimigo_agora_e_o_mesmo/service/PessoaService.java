package com.weg.jpa_relacionamentos_o_inimigo_agora_e_o_mesmo.service;

import com.weg.jpa_relacionamentos_o_inimigo_agora_e_o_mesmo.dto.DocumentoResponseDto;
import com.weg.jpa_relacionamentos_o_inimigo_agora_e_o_mesmo.dto.PessoaRequestDto;
import com.weg.jpa_relacionamentos_o_inimigo_agora_e_o_mesmo.dto.PessoaResponseDto;
import com.weg.jpa_relacionamentos_o_inimigo_agora_e_o_mesmo.mapper.DocumentoMapper;
import com.weg.jpa_relacionamentos_o_inimigo_agora_e_o_mesmo.mapper.PessoaMapper;
import com.weg.jpa_relacionamentos_o_inimigo_agora_e_o_mesmo.model.Documento;
import com.weg.jpa_relacionamentos_o_inimigo_agora_e_o_mesmo.model.Pessoa;
import com.weg.jpa_relacionamentos_o_inimigo_agora_e_o_mesmo.repo.DocumentoRepo;
import com.weg.jpa_relacionamentos_o_inimigo_agora_e_o_mesmo.repo.PessoaRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PessoaService {

    private final PessoaRepo pessoaRepo;
    private final DocumentoRepo documentoRepo;
    private final PessoaMapper pessoaMapper;
    private final DocumentoMapper documentoMapper;

    public PessoaResponseDto create(PessoaRequestDto pessoaRequestDto) {
        Pessoa pessoa = pessoaMapper.toEntity(pessoaRequestDto);

        if (pessoaRepo.existsByNome(pessoa.getNome())) {
            throw new RuntimeException("Pessoa já cadastrada!");
        }

        if (documentoRepo.existsByNumero(pessoaRequestDto.documento().getNumero())) {
            throw new RuntimeException("Documento já associado a uma pessoa");
        }

        pessoa.getDocumento().setPessoa(pessoa);
        pessoaRepo.save(pessoa);

        Documento documento = documentoRepo.findByPessoaId(pessoa.getId());
        DocumentoResponseDto documentoResponseDto = documentoMapper.toResponse(documento);

        return pessoaMapper.toResponse(pessoa, documentoResponseDto);
    }

    public PessoaResponseDto getById(Long id) {
        Pessoa pessoa = pessoaRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Pessoa não encontrada!"));

        Documento documento = documentoRepo.findByPessoaId(pessoa.getId());
        DocumentoResponseDto documentoResponseDto = documentoMapper.toResponse(documento);

        return pessoaMapper.toResponse(pessoa, documentoResponseDto);
    }
}
