package com.weg.biblioteca.controller;

import com.weg.biblioteca.dto.AutorRequestDto;
import com.weg.biblioteca.dto.AutorResponseDto;
import com.weg.biblioteca.service.AutorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/autor")
@RequiredArgsConstructor
public class AutorController {

    private final AutorService autorService;

    @PostMapping
    public ResponseEntity<AutorResponseDto> post(@RequestBody AutorRequestDto autorRequestDto) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(autorService.save(autorRequestDto));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<AutorResponseDto> getById(@PathVariable Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(autorService.getById(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping
    public ResponseEntity<List<AutorResponseDto>> getAll() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(autorService.getAll());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<AutorResponseDto> put(@RequestBody AutorRequestDto autorRequestDto, @PathVariable Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(autorService.update(autorRequestDto, id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<AutorResponseDto> delete(@PathVariable Long id) {
        try {
            autorService.delete(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
