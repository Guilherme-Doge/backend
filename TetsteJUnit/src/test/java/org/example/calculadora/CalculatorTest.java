package org.example.calculadora;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CalculatorTest {

    Calculator calc;

    @BeforeEach
    void setUp() {
        calc = new Calculator();
    }

    @Test
    void add() {
        assertEquals(15, calc.add(10, 5));
        assertEquals(-5, calc.add(-10, 5));
        assertEquals(10, calc.add(10, 0));
    }

    @Test
    void shouldSubtractCorrectly() {
        assertEquals(5, calc.subtract(10, 5));
        assertEquals(-15, calc.subtract(-10, 5));
        assertEquals(0, calc.subtract(5, 5));
    }

    @Test
    void divide() {
        assertEquals(2, calc.divide(10, 5));
        assertEquals(-2, calc.divide(10, -5));
        assertEquals(-2, calc.divide(-10, 5));
        assertEquals(2, calc.divide(-10, -5));
    }

    @Test
    void shouldThrowWhenDividingByZero() {
        assertThrows(IllegalArgumentException.class, () -> calc.divide(10, 0));
    }

    @Test
    void isEven() {
        assertTrue(calc.isEven(2));
        assertTrue(calc.isEven(6));
        assertTrue(calc.isEven(34));
    }

    @Test
    void isEvenShouldBeOdd() {
        assertFalse(calc.isEven(3));
        assertFalse(calc.isEven(5));
        assertFalse(calc.isEven(7));
    }
}