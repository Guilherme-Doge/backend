package com.weg.crud_exemplo.dto;

import java.time.LocalDate;
import java.util.List;

public record PedidoRequestDto (
        LocalDate dataCriacao,
        String nomeCliente,
        List<Long> itensId
){
}
