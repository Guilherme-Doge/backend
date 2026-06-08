package com.weg.jpa.controller;

import com.weg.jpa.dto.ContaRequestDto;
import com.weg.jpa.dto.ContaResponseDto;
import com.weg.jpa.dto.ContatoResponseDto;
import com.weg.jpa.service.ContaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/conta")
public class ContaController {
    private final ContaService contaService;

    @PostMapping
    ContaResponseDto save(@RequestBody ContaRequestDto contaRequestDto) {
        try {
            return contaService.save(contaRequestDto);
        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping("/{id")
    ContaResponseDto getConta(@PathVariable Long id) {
        try {
            return contaService.findById(id);
        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping
    List<ContaResponseDto> getAllConta() {
        try {
            return contaService.searchAll();
        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping("/{id}/contatos")
    List<ContatoResponseDto> getContaContatos(@PathVariable Long id) {
        try {
            return contaService.getContaContatos(id);
        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    ContaResponseDto updateConta(@PathVariable Long id,
                                     @RequestBody ContaRequestDto contaRequestDto) {
        try {
            return contaService.updateById(id, contaRequestDto);
        } catch(RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping("/{id")
    void deleteConta(@PathVariable Long id) {
        try {
            contaService.deleteById(id);
        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}