package org.example.services;

import org.example.classes.Order;
import org.example.repositories.OrderRepository;

public class OrderService {

    private OrderRepository repository;

    public OrderService(OrderRepository repository) {
        this.repository = repository;
    }

    public void processPayment(int orderId) {
        Order order = repository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Pedido não encontrado"));

        if (order.isPaid()) {
            throw new IllegalStateException("Pedido já foi pago");
        }

        if (order.getAmount() <= 0) {
            throw new IllegalArgumentException("Valor inválido");
        }

        // regra: desconto de 10% para pedidos acima de 100
        if (order.getAmount() > 100) {
            double discounted = order.getAmount() * 0.9;
            // (simulação: apenas para lógica, não precisa salvar novo valor)
        }

        order.setPaid(true);
        repository.save(order);
    }
}