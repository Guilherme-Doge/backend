package com.weg.crud_exemplo.repo;

import com.weg.crud_exemplo.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepo extends JpaRepository<Pedido, Long> {
}
