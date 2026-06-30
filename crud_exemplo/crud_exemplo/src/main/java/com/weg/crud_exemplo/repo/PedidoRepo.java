package com.weg.crud_exemplo.repo;

import com.weg.crud_exemplo.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoRepo extends JpaRepository<Pedido, Long> {

    @Query("""
            SELECT p
            FROM Pedido p
            JOIN FETCH p.itens i
            WHERE p.id = :idPedido
            """)
    public List<Pedido> getByPedidoId(@Param("idPedido") Long id);
}
