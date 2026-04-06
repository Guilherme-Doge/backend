package com.example.service;

import com.example.model.Livro;
import com.example.repository.LivroRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LivroServiceTest {

    @Mock
    private LivroRepository repository;

    private LivroService service;

    @BeforeEach
    void setup() {
        service = new LivroService(repository);
    }

    // =========================
    // registerBook
    // =========================

    @Test
    void deveRegistrarLivroComSucesso() {
        service.registerBook("Clean Code", "Robert Martin", 2008);

        verify(repository).registerBook("Clean Code", "Robert Martin", 2008);
    }

    // =========================
    // validarExistenciaLivro
    // =========================

    @Test
    void deveValidarLivroExistente() {
        Livro livro = mock(Livro.class);

        when(livro.getId()).thenReturn(1);
        when(repository.listarLivroPorId(1)).thenReturn(livro);

        assertDoesNotThrow(() ->
                service.validarExistenciaLivro(livro)
        );
    }

    @Test
    void deveFalharSeLivroNaoExistir() {
        Livro livro = mock(Livro.class);

        when(livro.getId()).thenReturn(1);
        when(repository.listarLivroPorId(1)).thenReturn(null);

        assertThrows(RuntimeException.class, () ->
                service.validarExistenciaLivro(livro)
        );
    }

    // =========================
    // marcarLivroComoNaoDisponivel
    // =========================

    @Test
    void deveMarcarLivroComoNaoDisponivel() {
        Livro livro = mock(Livro.class);

        when(livro.getId()).thenReturn(10);

        service.marcarLivroComoNaoDisponivel(livro);

        verify(repository).marcarLivroComoNaoDisponivel(10);
    }

    // =========================
    // marcarLivroComoDisponivel
    // =========================

    @Test
    void deveMarcarLivroComoDisponivel() {
        Livro livro = mock(Livro.class);

        when(livro.getId()).thenReturn(10);

        service.marcarLivroComoDisponivel(livro);

        verify(repository).marcarLivroComoDisponivel(10);
    }

    // =========================
    // listarLivros
    // =========================

    @Test
    void deveRetornarListaDeLivros() {
        when(repository.listarLivros()).thenReturn(List.of());

        List<Livro> resultado = service.listarLivros();

        assertNotNull(resultado);
        verify(repository).listarLivros();
    }
}