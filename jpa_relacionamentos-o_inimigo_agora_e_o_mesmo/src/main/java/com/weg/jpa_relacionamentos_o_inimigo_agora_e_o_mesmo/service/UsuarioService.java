package com.weg.jpa_relacionamentos_o_inimigo_agora_e_o_mesmo.service;

import com.weg.jpa_relacionamentos_o_inimigo_agora_e_o_mesmo.dto.LivroResponseDto;
import com.weg.jpa_relacionamentos_o_inimigo_agora_e_o_mesmo.dto.UsuarioRequestDto;
import com.weg.jpa_relacionamentos_o_inimigo_agora_e_o_mesmo.dto.UsuarioResponseDto;
import com.weg.jpa_relacionamentos_o_inimigo_agora_e_o_mesmo.mapper.LivroMapper;
import com.weg.jpa_relacionamentos_o_inimigo_agora_e_o_mesmo.mapper.UsuarioMapper;
import com.weg.jpa_relacionamentos_o_inimigo_agora_e_o_mesmo.model.Livro;
import com.weg.jpa_relacionamentos_o_inimigo_agora_e_o_mesmo.model.Usuario;
import com.weg.jpa_relacionamentos_o_inimigo_agora_e_o_mesmo.repo.LivroRepo;
import com.weg.jpa_relacionamentos_o_inimigo_agora_e_o_mesmo.repo.UsuarioRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepo usuarioRepo;
    private final LivroRepo livroRepo;
    private final UsuarioMapper usuarioMapper;
    private final LivroMapper livroMapper;

    public UsuarioResponseDto create(UsuarioRequestDto usuarioRequestDto) {
        Usuario usuario = usuarioMapper.toEntity(usuarioRequestDto);
        usuarioRepo.save(usuario);
        return usuarioMapper.toResponse(usuario);
    }

    public UsuarioResponseDto emprestarLivro(Long usuarioId, Long livroId) {
        Usuario usuario = usuarioRepo.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));

        Livro livro = livroRepo.findById(livroId)
                .orElseThrow(() -> new RuntimeException("Livro não encontrado!"));

        for (Livro livroExistente : usuario.getLivrosEmprestados()) {
            if (livroExistente.getId().equals(livroId)) {
                throw new RuntimeException("Livro já foi emprestado para esse usuário!");
            }
        }

        usuario.getLivrosEmprestados().add(livro);
        usuarioRepo.save(usuario);

        return usuarioMapper.toResponse(usuario);
    }

    public UsuarioResponseDto getById(Long id) {
        Usuario usuario = usuarioRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));
        return usuarioMapper.toResponse(usuario);
    }
}
