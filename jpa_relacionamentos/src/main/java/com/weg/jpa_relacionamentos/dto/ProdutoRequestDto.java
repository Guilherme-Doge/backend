package com.weg.jpa_relacionamentos.dto;

import java.math.BigDecimal;

public record ProdutoRequestDto(
        String nome,
        BigDecimal preco,
        Long categoriaId
) {}
