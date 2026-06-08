package com.weg.jpa.service;

import com.weg.jpa.dto.ContaRequestDto;
import com.weg.jpa.dto.ContaResponseDto;
import com.weg.jpa.dto.ContatoResponseDto;
import com.weg.jpa.mapper.ContaMapper;
import com.weg.jpa.mapper.ContatoMapper;
import com.weg.jpa.model.Conta;
import com.weg.jpa.repository.ConversaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MensagemService {
    private final ConversaRepository contaRepository;

    private final ContaMapper contaMapper;

    private final ContatoMapper contatoMapper;

    public ContaResponseDto save(ContaRequestDto contaRequestDto) {
        Conta conta = contaMapper.toEntity(contaRequestDto);

        contaRepository.save(conta);

        return contaMapper.toResponse(conta);
    }

    public List<ContaResponseDto> searchAll() {
        List<Conta> contas = contaRepository.findAll();

        return contas.stream()
                .map(contaMapper::toResponse)
                .toList();
    }

    public ContaResponseDto findById(Long id) {
        Conta conta = contaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Conta não existe"));

        return contaMapper.toResponse(conta);
    }

    public List<ContatoResponseDto> getContaContatos(Long id) {
        return contaRepository.findByContaId(id).stream()
                .map(contatoMapper::toResponse)
                .toList();
    }

    public ContaResponseDto updateById(Long id, ContaRequestDto contaRequestDto) {
        Conta conta = contaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Conta não existe"));

        conta.setNome(contaRequestDto.nome());
        conta.setNumero(contaRequestDto.numero());

        return contaMapper.toResponse(conta);
    }

    public void deleteById(Long id) {
        if (!contaRepository.existsById(id)) {
            throw new RuntimeException("Conta não existe");
        }

        contaRepository.deleteById(id);
    }
}