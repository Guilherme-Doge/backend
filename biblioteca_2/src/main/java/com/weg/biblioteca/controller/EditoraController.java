package com.weg.biblioteca.controller;

import com.weg.biblioteca.dto.EditoraRequestDto;
import com.weg.biblioteca.dto.EditoraResponseDto;
import com.weg.biblioteca.service.EditoraService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/editora")
@RequiredArgsConstructor
public class EditoraController {

    private final EditoraService editoraService;

    @PostMapping
    public ResponseEntity<EditoraResponseDto> post(@RequestBody EditoraRequestDto editoraRequestDto) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(editoraService.save(editoraRequestDto));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<EditoraResponseDto> getById(@PathVariable Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(editoraService.getById(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping
    public ResponseEntity<List<EditoraResponseDto>> getAll() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(editoraService.getAll());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<EditoraResponseDto> put(@RequestBody EditoraRequestDto editoraRequestDto, @PathVariable Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(editoraService.update(editoraRequestDto, id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<EditoraResponseDto> delete(@PathVariable Long id) {
        try {
            editoraService.delete(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
