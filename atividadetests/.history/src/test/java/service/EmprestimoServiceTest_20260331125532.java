package service;

import org.junit.Test;

public class EmprestimoServiceTest {
    package com.example.service;
    
    import com.example.model.Emprestimo;
    import com.example.model.Livro;
    import com.example.model.Usuario;
    import com.example.repository.EmprestimoRepository;
    
    import org.junit.jupiter.api.BeforeEach;
    import org.junit.jupiter.api.Test;
    
    import java.time.DateTimeException;
    import java.time.LocalDate;
    import java.util.List;
    
    import static org.junit.jupiter.api.Assertions.*;
    import static org.mockito.Mockito.*;
    
    class EmprestimoServiceTest {
    
        private EmprestimoRepository repository;
        private EmprestimoService service;
        private LivroService livroService;
    
        @BeforeEach
        void setup() {
            repository = mock(EmprestimoRepository.class);
            livroService = mock(LivroService.class);
            service = new EmprestimoService(repository);
        }
    
        // =============================
        // registrarEmprestimo
        // =============================
    
        @Test
        void deveRegistrarEmprestimoComDadosValidos() {
            Usuario usuario = new Usuario();
            usuario.setId(1);
    
            Livro livro = new Livro();
            livro.setId(1);
    
            LocalDate hoje = LocalDate.now();
    
            when(repository.listarUsuarioPorId(1)).thenReturn(usuario);
    
            service.registrarEmprestimo(usuario, livro, hoje, hoje.plusDays(5), livroService);
    
            verify(repository).registerBorrow(usuario, livro, hoje, hoje.plusDays(5), livroService);
            verify(livroService).validarExistenciaLivro(livro);
        }
    
        // =============================
        // validarExistenciaUsuario
        // =============================
    
        @Test
        void deveLancarExcecaoQuandoUsuarioNaoExiste() {
            Usuario usuario = new Usuario();
            usuario.setId(1);
    
            Usuario outro = new Usuario();
            outro.setId(1); // <- isso revela o bug
    
            when(repository.listarUsuarioPorId(1)).thenReturn(outro);
    
            assertThrows(RuntimeException.class, () ->
                service.validarExistenciaUsuario(usuario)
            );
        }
    
        // =============================
        // validarDataEmprestimo
        // =============================
    
        @Test
        void deveLancarExcecaoQuandoDataMuitoAntiga() {
            LocalDate data = LocalDate.now().minusYears(101);
    
            assertThrows(DateTimeException.class, () ->
                service.validarDataEmprestimo(data)
            );
        }
    
        @Test
        void deveLancarExcecaoQuandoDataNoFuturo() {
            LocalDate data = LocalDate.now().plusDays(1);
    
            assertThrows(DateTimeException.class, () ->
                service.validarDataEmprestimo(data)
            );
        }
    
        @Test
        void deveAceitarDataValidaEmprestimo() {
            LocalDate data = LocalDate.now();
    
            assertDoesNotThrow(() ->
                service.validarDataEmprestimo(data)
            );
        }
    
        // =============================
        // validarDataDevolucao
        // =============================
    
        @Test
        void deveLancarExcecaoQuandoDataDevolucaoNoPassado() {
            LocalDate data = LocalDate.now().minusDays(1);
    
            assertThrows(DateTimeException.class, () ->
                service.validarDataDevolucao(data)
            );
        }
    
        @Test
        void deveLancarExcecaoQuandoDataMuitoNoFuturo() {
            LocalDate data = LocalDate.now().plusYears(101);
    
            assertThrows(DateTimeException.class, () ->
                service.validarDataDevolucao(data)
            );
        }
    
        @Test
        void deveAceitarDataValidaDevolucao() {
            LocalDate data = LocalDate.now().plusDays(5);
    
            assertDoesNotThrow(() ->
                service.validarDataDevolucao(data)
            );
        }
    
        // =============================
        // devolverLivro
        // =============================
    
        @Test
        void deveDevolverLivroCorretamente() {
            Emprestimo emprestimo = new Emprestimo();
            emprestimo.setId(1);
    
            Livro livro = new Livro();
            livro.setId(10);
    
            when(repository.pegarLivroPeloEmprestimo(livroService, 1)).thenReturn(livro);
    
            service.devolverLivro(emprestimo, livroService);
    
            verify(repository).devolverLivro(10);
        }
    
        // =============================
        // verEmprestimos
        // =============================
    
        @Test
        void deveBuscarEmprestimos() {
            when(repository.verEmprestimos()).thenReturn(List.of());
    
            service.verEmprestimos();
    
            verify(repository).verEmprestimos();
        }
    }
}

import com.example.service.EmprestimoService;
import com.example.service.LivroService;