package com.weg.jpa_relacionamentos.mapper;

import com.weg.jpa_relacionamentos.dto.ProdutoRequestDto;
import com.weg.jpa_relacionamentos.dto.ProdutoResponseDto;
import com.weg.jpa_relacionamentos.model.Categoria;
import com.weg.jpa_relacionamentos.model.Produto;
import org.springframework.stereotype.Component;

@Component
public class ProdutoMapper {
    public Produto toEntity(ProdutoRequestDto dto, Categoria categoria) {
        Produto produto = new Produto();
        produto.setNome(dto.nome());
        produto.setPreco(dto.preco());
        produto.setCategoria(categoria);
        return produto;
    }

    public ProdutoResponseDto toResponse(Produto produto) {
        return new ProdutoResponseDto(
                produto.getId(),
                produto.getNome(),
                produto.getPreco(),
                produto.getCategoria() != null ? produto.getCategoria().getId() : null,
                produto.getCategoria() != null ? produto.getCategoria().getNome() : null
        );
    }
}
