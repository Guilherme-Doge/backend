package com.weg.crud_exemplo.repo;

import com.weg.crud_exemplo.model.ItemPedido;
import com.weg.crud_exemplo.projection.RelatorioSimplesProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemPedidoRepo extends JpaRepository<ItemPedido, Long> {
    public List<ItemPedido> findBypedido_id(Long id);

    @Query(value = """
            SELECT p.id AS idPedido,
                    i.nome AS nomeItem,
                    i.preco AS precoItem
            FROM item_pedido i
            INNER JOIN pedido p
            ON i.id = p.id
            """, nativeQuery = true)
    List<RelatorioSimplesProjection> getRelatorioSimples();
}