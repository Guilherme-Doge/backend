package org.example.repositories;

import org.example.classes.Order;

import java.util.Optional;

public interface OrderRepository {
    Optional<Order> findById(int id);

    void save(Order order);
}