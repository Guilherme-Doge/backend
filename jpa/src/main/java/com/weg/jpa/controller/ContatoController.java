package com.weg.jpa.controller;

import com.weg.jpa.dto.ContatoRequestDto;
import com.weg.jpa.dto.ContatoResponseDto;
import com.weg.jpa.service.ContatoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/contato")
public class ContatoController {
    private final ContatoService contatoService;

    @PostMapping
    ContatoResponseDto save(@RequestBody ContatoRequestDto contatoRequestDto) {
        try {
            return contatoService.save(contatoRequestDto);
        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping("/{id")
    ContatoResponseDto getContato(@PathVariable Long id) {
        try {
            return contatoService.findById(id);
        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping
    List<ContatoResponseDto> getAllContato() {
        try {
            return contatoService.searchAll();
        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    ContatoResponseDto updateContato(@PathVariable Long id,
                                     @RequestBody ContatoRequestDto contatoRequestDto) {
        try {
            return contatoService.updateById(id, contatoRequestDto);
        } catch(RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping("/{id")
    void deleteContato(@PathVariable Long id) {
        try {
            contatoService.deleteById(id);
        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}