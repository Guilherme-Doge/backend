package com.weg.jpa_relacionamentos_o_inimigo_agora_e_o_mesmo.mapper;

import com.weg.jpa_relacionamentos_o_inimigo_agora_e_o_mesmo.dto.ContatoResponseDto;
import com.weg.jpa_relacionamentos_o_inimigo_agora_e_o_mesmo.dto.LivroResponseDto;
import com.weg.jpa_relacionamentos_o_inimigo_agora_e_o_mesmo.dto.UsuarioRequestDto;
import com.weg.jpa_relacionamentos_o_inimigo_agora_e_o_mesmo.dto.UsuarioResponseDto;
import com.weg.jpa_relacionamentos_o_inimigo_agora_e_o_mesmo.model.Contato;
import com.weg.jpa_relacionamentos_o_inimigo_agora_e_o_mesmo.model.Livro;
import com.weg.jpa_relacionamentos_o_inimigo_agora_e_o_mesmo.model.Usuario;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UsuarioMapper {

    public Usuario toEntity(UsuarioRequestDto usuarioRequestDto) {
        return new Usuario(usuarioRequestDto.nome(), usuarioRequestDto.contato());
    }

    public UsuarioResponseDto toResponse(Usuario usuario) {
        Contato contato = usuario.getContato();
        ContatoResponseDto contatoResponseDto = new ContatoResponseDto(
                contato.getId(),
                contato.getEmail(),
                contato.getTelefone()
        );

        List<LivroResponseDto> livros = new ArrayList<>();
        for (Livro livro : usuario.getLivrosEmprestados()) {
            livros.add(new LivroResponseDto(livro.getId(), livro.getTitulo(), livro.getAutor()));
        }

        return new UsuarioResponseDto(usuario.getId(), usuario.getNome(), contatoResponseDto, livros);
    }
}
