package com.weg.biblioteca.service;

import com.weg.biblioteca.dto.EditoraRequestDto;
import com.weg.biblioteca.dto.EditoraResponseDto;
import com.weg.biblioteca.mapper.EditoraMapper;
import com.weg.biblioteca.model.Editora;
import com.weg.biblioteca.projection.EstatisticasEditoraProjection;
import com.weg.biblioteca.repo.EditoraRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EditoraService {

    private final EditoraMapper editoraMapper;
    private final EditoraRepo editoraRepo;

    public EditoraResponseDto save(EditoraRequestDto editoraRequestDto) throws Exception {
        Editora editora = editoraMapper.toEntity(editoraRequestDto);

        editoraRepo.save(editora);

        return editoraMapper.toResponse(editora);
    }

    public EditoraResponseDto getById(Long id) throws Exception {
        Editora editora = editoraRepo.findById(id).orElseThrow(() -> new RuntimeException("Editora não encontrado"));

        return editoraMapper.toResponse(editora);
    }

    public List<EditoraResponseDto> getAll() throws Exception {
        List<Editora> editoraes = editoraRepo.findAll();

        return editoraes.stream().map(editoraMapper::toResponse).toList();
    }

    public List<EstatisticasEditoraProjection> findEstatisticasEditora() throws Exception {
        List<EstatisticasEditoraProjection> editoras = editoraRepo.findAllWithNumberOfBooks();

        return editoras;
    }

    public EditoraResponseDto update(EditoraRequestDto editoraRequestDto, Long id) throws Exception {
        Editora editora = editoraRepo.findById(id).orElseThrow(() -> new RuntimeException("Editora não encontrado"));

        if (editoraRequestDto.nome() != null) {
            editora.setNome(editoraRequestDto.nome());
        }

        return editoraMapper.toResponse(editora);
    }

    public void delete(Long id) throws Exception {
        editoraRepo.deleteById(id);
    }

}
