package com.weg.jpa_relacionamentos_o_inimigo_agora_e_o_mesmo.controller;

import com.weg.jpa_relacionamentos_o_inimigo_agora_e_o_mesmo.dto.UsuarioRequestDto;
import com.weg.jpa_relacionamentos_o_inimigo_agora_e_o_mesmo.dto.UsuarioResponseDto;
import com.weg.jpa_relacionamentos_o_inimigo_agora_e_o_mesmo.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping
    public UsuarioResponseDto create(@RequestBody UsuarioRequestDto usuarioRequestDto) {
        return usuarioService.create(usuarioRequestDto);
    }

    @PostMapping("/{usuarioId}/livro/{livroId}")
    public UsuarioResponseDto emprestarLivro(@PathVariable Long usuarioId, @PathVariable Long livroId) {
        return usuarioService.emprestarLivro(usuarioId, livroId);
    }

    @GetMapping("/{id}")
    public UsuarioResponseDto getById(@PathVariable Long id) {
        return usuarioService.getById(id);
    }
}
