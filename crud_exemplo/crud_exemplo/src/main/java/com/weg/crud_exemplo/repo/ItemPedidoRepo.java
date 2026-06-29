package com.weg.crud_exemplo.repo;

import com.weg.crud_exemplo.model.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemPedidoRepo extends JpaRepository<ItemPedido, Long> {
    public List<ItemPedido> findBypedido_id(Long id);
}