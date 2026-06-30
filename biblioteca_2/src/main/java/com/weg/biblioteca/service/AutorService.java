package com.weg.biblioteca.service;

import com.weg.biblioteca.dto.AutorRequestDto;
import com.weg.biblioteca.dto.AutorResponseDto;
import com.weg.biblioteca.mapper.AutorMapper;
import com.weg.biblioteca.model.Autor;
import com.weg.biblioteca.repo.AutorRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AutorService {

    private final AutorMapper autorMapper;
    private final AutorRepo autorRepo;

    public AutorResponseDto save(AutorRequestDto autorRequestDto) throws Exception {
        Autor autor = autorMapper.toEntity(autorRequestDto);

        autorRepo.save(autor);

        return autorMapper.toResponse(autor);
    }

    public AutorResponseDto getById(Long id) throws Exception {
        Autor autor = autorRepo.findById(id).orElseThrow(() -> new RuntimeException("Autor não encontrado"));

        return autorMapper.toResponse(autor);
    }

    public List<AutorResponseDto> getAll() throws Exception {
        List<Autor> autores = autorRepo.findAll();

        return autores.stream().map(autorMapper::toResponse).toList();
    }

    public AutorResponseDto update(AutorRequestDto autorRequestDto, Long id) throws Exception {
        Autor autor = autorRepo.findById(id).orElseThrow(() -> new RuntimeException("Autor não encontrado"));

        if (autorRequestDto.nome() != null) {
            autor.setNome(autorRequestDto.nome());
        }

        if (autorRequestDto.nacionalidade() != null) {
            autor.setNacionalidade(autorRequestDto.nacionalidade());
        }

        return autorMapper.toResponse(autor);
    }

    public void delete(Long id) throws Exception {
        autorRepo.deleteById(id);
    }

}
