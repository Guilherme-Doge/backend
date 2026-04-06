package com.example.service;

import com.example.model.Emprestimo;
import com.example.model.Livro;
import com.example.model.Usuario;
import com.example.repository.EmprestimoRepository;
import com.example.service.EmprestimoService;
import com.example.service.LivroService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EmprestimoServiceTest {

    @Mock
    EmprestimoRepository repository;

    @Mock
    LivroService livroService;

    EmprestimoService service;

    @BeforeEach
    void setup() {
        service = new EmprestimoService(repository);
    }

    // =========================
    // registrarEmprestimo
    // =========================

    @Test
    void deveRegistrarEmprestimoComDadosValidos() {
        Usuario usuario = mock(Usuario.class);
        Livro livro = mock(Livro.class);

        when(usuario.getId()).thenReturn(1);

        // Corrigindo comportamento esperado do repository
        when(repository.listarUsuarioPorId(1)).thenReturn(usuario);

        LocalDate hoje = LocalDate.now();
        LocalDate devolucao = hoje.plusDays(5);

        service.registrarEmprestimo(usuario, livro, hoje, devolucao, livroService);

        verify(livroService).validarExistenciaLivro(livro);
        verify(repository).registerBorrow(usuario, livro, hoje, devolucao, livroService);
    }

    // =========================
    // validarExistenciaUsuario
    // =========================

    @Test
    void deveLancarExcecaoQuandoUsuarioNaoExiste() {
        Usuario usuario = mock(Usuario.class);

        when(usuario.getId()).thenReturn(1);

        Usuario usuarioBanco = mock(Usuario.class);
        when(usuarioBanco.getId()).thenReturn(1);

        when(repository.listarUsuarioPorId(1)).thenReturn(usuarioBanco);

        assertThrows(RuntimeException.class, () ->
                service.validarExistenciaUsuario(usuario)
        );
    }

    // =========================
    // validarDataEmprestimo
    // =========================

    @Test
    void deveLancarExcecaoQuandoDataMuitoAntiga() {
        LocalDate data = LocalDate.now().minusYears(101);

        assertThrows(DateTimeException.class, () ->
                service.validarDataEmprestimo(data)
        );
    }

    @Test
    void deveLancarExcecaoQuandoDataFutura() {
        LocalDate data = LocalDate.now().plusDays(1);

        assertThrows(DateTimeException.class, () ->
                service.validarDataEmprestimo(data)
        );
    }

    @Test
    void deveAceitarDataEmprestimoValida() {
        LocalDate data = LocalDate.now().minusDays(1);

        assertDoesNotThrow(() ->
                service.validarDataEmprestimo(data)
        );
    }

    // =========================
    // validarDataDevolucao
    // =========================

    @Test
    void deveLancarExcecaoQuandoDataDevolucaoPassada() {
        LocalDate data = LocalDate.now().minusDays(1);

        assertThrows(DateTimeException.class, () ->
                service.validarDataDevolucao(data)
        );
    }

    @Test
    void deveLancarExcecaoQuandoDataMuitoFutura() {
        LocalDate data = LocalDate.now().plusYears(101);

        assertThrows(DateTimeException.class, () ->
                service.validarDataDevolucao(data)
        );
    }

    @Test
    void deveAceitarDataDevolucaoValida() {
        LocalDate data = LocalDate.now().plusDays(5);

        assertDoesNotThrow(() ->
                service.validarDataDevolucao(data)
        );
    }

    // =========================
    // devolverLivro
    // =========================

    @Test
    void deveDevolverLivroCorretamente() {
        Emprestimo emprestimo = mock(Emprestimo.class);
        Livro livro = mock(Livro.class);

        when(emprestimo.getId()).thenReturn(10);
        when(livro.getId()).thenReturn(5);

        when(repository.pegarLivroPeloEmprestimo(livroService, 10))
                .thenReturn(livro);

        service.devolverLivro(emprestimo, livroService);

        verify(repository).devolverLivro(5);
    }

    // =========================
    // verEmprestimos
    // =========================

    @Test
    void deveChamarRepositorioAoVerEmprestimos() {
        when(repository.verEmprestimos()).thenReturn(List.of());

        service.verEmprestimos();

        verify(repository).verEmprestimos();
    }
}