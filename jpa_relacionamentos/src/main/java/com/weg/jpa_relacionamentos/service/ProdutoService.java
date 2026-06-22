package com.weg.jpa_relacionamentos.service;

import com.weg.jpa_relacionamentos.dto.ProdutoRequestDto;
import com.weg.jpa_relacionamentos.dto.ProdutoResponseDto;
import com.weg.jpa_relacionamentos.mapper.ProdutoMapper;
import com.weg.jpa_relacionamentos.model.Categoria;
import com.weg.jpa_relacionamentos.model.Produto;
import com.weg.jpa_relacionamentos.repo.CategoriaRepo;
import com.weg.jpa_relacionamentos.repo.ProdutoRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProdutoService {
    private final ProdutoMapper produtoMapper;
    private final ProdutoRepo produtoRepo;
    private final CategoriaRepo categoriaRepo;

    public ProdutoResponseDto post(ProdutoRequestDto dto) {
        if (dto.categoriaId() == null) {
            throw new RuntimeException("Informe o id da categoria");
        }
        Categoria categoria = categoriaRepo.findById(dto.categoriaId()).orElseThrow(() -> new RuntimeException("Categoria não existe"));
        Produto produto = produtoMapper.toEntity(dto, categoria);
        return produtoMapper.toResponse(produtoRepo.save(produto));
    }

    public ProdutoResponseDto get(Long id) {
        Produto produto = produtoRepo.findById(id).orElseThrow(() -> new RuntimeException("Produto não existe"));
        return produtoMapper.toResponse(produto);
    }

    public List<ProdutoResponseDto> list() {
        return produtoRepo.findAll().stream().map(produtoMapper::toResponse).toList();
    }

    public ProdutoResponseDto put(Long id, ProdutoRequestDto dto) {
        Produto produto = produtoRepo.findById(id).orElseThrow(() -> new RuntimeException("Produto não existe"));
        if (dto.nome() != null && !dto.nome().isBlank()) {
            produto.setNome(dto.nome());
        }
        if (dto.preco() != null) {
            produto.setPreco(dto.preco());
        }
        if (dto.categoriaId() != null) {
            Categoria categoria = categoriaRepo.findById(dto.categoriaId()).orElseThrow(() -> new RuntimeException("Categoria não existe"));
            produto.setCategoria(categoria);
        }
        return produtoMapper.toResponse(produtoRepo.save(produto));
    }

    public void delete(Long id) {
        if (!produtoRepo.existsById(id)) {
            throw new RuntimeException("Produto não existe");
        }
        produtoRepo.deleteById(id);
    }
}
