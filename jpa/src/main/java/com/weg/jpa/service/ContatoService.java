package com.weg.jpa.service;

import com.weg.jpa.dto.ContatoRequestDto;
import com.weg.jpa.dto.ContatoResponseDto;
import com.weg.jpa.mapper.ContatoMapper;
import com.weg.jpa.model.Contato;
import com.weg.jpa.repository.ContatoRepository;
import jakarta.persistence.NoResultException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContatoService {
    private final ContatoRepository contatoRepository;

    private final ContatoMapper contatoMapper;

    public ContatoResponseDto save(ContatoRequestDto contatoRequestDto) {
        Contato contato = contatoMapper.toEntity(contatoRequestDto);

        contatoRepository.save(contato);

        return contatoMapper.toResponse(contato);
    }

    public List<ContatoResponseDto> searchAll() {
        List<Contato> contatos = contatoRepository.findAll();

        return contatos.stream()
                .map(contatoMapper::toResponse)
                .toList();
    }

    public ContatoResponseDto findById(Long id) {
        Contato contato = contatoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contato não existe"));

        return contatoMapper.toResponse(contato);
    }

    public ContatoResponseDto updateById(Long id, ContatoRequestDto contatoRequestDto) {
        Contato contato = contatoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contato não existe"));

        contato.setNome(contatoRequestDto.nome());
        contato.setNumero(contatoRequestDto.numero());

        return contatoMapper.toResponse(contato);
    }

    public void deleteById(Long id) {
        if (!contatoRepository.existsById(id)) {
            throw new RuntimeException("Contato não existe");
        }

        contatoRepository.deleteById(id);
    }
}