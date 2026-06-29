package com.weg.crud_exemplo.dto;

import java.time.LocalDate;
import java.util.List;

public record PedidoResponseDto (
        Long id,
        LocalDate dataCriacao,
        String nomeCliente
){
}
