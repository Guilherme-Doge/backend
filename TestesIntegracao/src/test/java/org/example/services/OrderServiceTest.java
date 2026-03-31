package org.example.services;

import org.example.classes.Order;
import org.example.repositories.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class OrderServiceTest {

    private OrderRepository repository;
    private OrderService service;

    @BeforeEach
    void setUp() {
        repository = mock(OrderRepository.class);
        service = new OrderService(repository);
    }

    // ✅ 1. Pedido válido
    @Test
    void shouldProcessPaymentSuccessfully() {
        Order order = new Order(1, 50);

        when(repository.findById(1)).thenReturn(Optional.of(order));

        service.processPayment(1);

        assertTrue(order.isPaid());
        verify(repository).save(order);
    }

    // ❌ 2. Pedido não existe
    @Test
    void shouldThrowWhenOrderDoesNotExist() {
        when(repository.findById(1)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class,
                () -> service.processPayment(1));

        verify(repository, never()).save(any());
    }

    // ❌ 3. Pedido já pago
    @Test
    void shouldThrowWhenOrderAlreadyPaid() {
        Order order = new Order(1, 50);
        order.setPaid(true);

        when(repository.findById(1)).thenReturn(Optional.of(order));

        assertThrows(IllegalStateException.class,
                () -> service.processPayment(1));

        verify(repository, never()).save(any());
    }

    // ❌ 4. Valor inválido (zero)
    @Test
    void shouldThrowWhenAmountIsZero() {
        Order order = new Order(1, 0);

        when(repository.findById(1)).thenReturn(Optional.of(order));

        assertThrows(IllegalArgumentException.class,
                () -> service.processPayment(1));

        verify(repository, never()).save(any());
    }

    // ❌ 4. Valor inválido (negativo)
    @Test
    void shouldThrowWhenAmountIsNegative() {
        Order order = new Order(1, -10);

        when(repository.findById(1)).thenReturn(Optional.of(order));

        assertThrows(IllegalArgumentException.class,
                () -> service.processPayment(1));

        verify(repository, never()).save(any());
    }

    // 🟡 5. Pedido com desconto (> 100)
    @Test
    void shouldProcessPaymentWithDiscount() {
        Order order = new Order(1, 200);

        when(repository.findById(1)).thenReturn(Optional.of(order));

        service.processPayment(1);

        assertTrue(order.isPaid());
        verify(repository).save(order);
    }
}