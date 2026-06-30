package com.weg.biblioteca.controller;

import com.weg.biblioteca.dto.LivroRequestDto;
import com.weg.biblioteca.dto.LivroResponseDto;
import com.weg.biblioteca.service.LivroService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/livro")
@RequiredArgsConstructor
public class LivroController {

    private final LivroService livroService;

    @PostMapping
    public ResponseEntity<LivroResponseDto> post(@RequestBody LivroRequestDto livroRequestDto) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(livroService.save(livroRequestDto));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<LivroResponseDto> getById(@PathVariable Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(livroService.getById(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping
    public ResponseEntity<List<LivroResponseDto>> getAll() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(livroService.getAll());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<LivroResponseDto> getByName(@PathVariable String nome) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(livroService.findByName(nome));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/{categoria}/{preco}")
    public ResponseEntity<List<LivroResponseDto>> getByName(@PathVariable String categoria, @PathVariable Double preco) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(livroService.getByCategoriaAndPrecoLessThan(categoria, preco));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/preco/{minimo:.+}/{maximo:.+}")
    public ResponseEntity<List<LivroResponseDto>> getByPrecoBetween(@PathVariable Double minimo, @PathVariable Double maximo) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(livroService.getByPrecoBetween(minimo, maximo));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/titulo/{titulo}")
    public ResponseEntity<List<LivroResponseDto>> getByTituloContaininig(@PathVariable String titulo) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(livroService.getByTituloContaining(titulo));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/categorias/{categorias}")
    public ResponseEntity<List<LivroResponseDto>> getByTituloContaininig(@PathVariable List<String> categorias) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(livroService.findByCategoriaIn(categorias));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<LivroResponseDto> put(@RequestBody LivroRequestDto livroRequestDto, @PathVariable Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(livroService.update(livroRequestDto, id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<LivroResponseDto> delete(@PathVariable Long id) {
        try {
            livroService.delete(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
