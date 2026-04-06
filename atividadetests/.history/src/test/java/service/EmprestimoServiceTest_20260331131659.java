package service;

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

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class())
class EmprestimoServiceTest {

    @Mock
    private EmprestimoRepository repository;

    @Mock
    private LivroService livroService;

    private EmprestimoService service;

    @BeforeEach
    void setup() {
        service = new EmprestimoService(repository, livroService);
    }

    // =========================
    // registrarEmprestimo
    // =========================

    @Test
    void deveRegistrarEmprestimoComSucesso() {
        Usuario usuario = mock(Usuario.class);
        Livro livro = mock(Livro.class);

        when(usuario.getId()).thenReturn(1);
        when(repository.listarUsuarioPorId(1)).thenReturn(usuario);

        LocalDate hoje = LocalDate.now();
        LocalDate devolucao = hoje.plusDays(7);

        service.registrarEmprestimo(usuario, livro, hoje, devolucao);

        verify(livroService).validarExistenciaLivro(livro);
        verify(repository).registerBorrow(usuario, livro, hoje, devolucao);
        verify(livroService).marcarLivroComoNaoDisponivel(livro);
    }

    @Test
    void deveFalharSeUsuarioNaoExistir() {
        Usuario usuario = mock(Usuario.class);

        when(usuario.getId()).thenReturn(1);
        when(repository.listarUsuarioPorId(1)).thenReturn(null);

        assertThrows(RuntimeException.class, () ->
                service.registrarEmprestimo(
                        usuario,
                        mock(Livro.class),
                        LocalDate.now(),
                        LocalDate.now().plusDays(5)
                )
        );

        verify(repository, never()).registerBorrow(any(), any(), any(), any());
    }

    // =========================
    // validarDataEmprestimo
    // =========================

    @Test
    void deveFalharDataEmprestimoMuitoAntiga() {
        LocalDate data = LocalDate.now().minusYears(101);

        assertThrows(DateTimeException.class, () ->
                service.validarDataEmprestimo(data)
        );
    }

    @Test
    void deveFalharDataEmprestimoFutura() {
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
    void deveFalharDataDevolucaoPassada() {
        LocalDate data = LocalDate.now().minusDays(1);

        assertThrows(DateTimeException.class, () ->
                service.validarDataDevolucao(data)
        );
    }

    @Test
    void deveFalharDataDevolucaoMuitoFutura() {
        LocalDate data = LocalDate.now().plusYears(101);

        assertThrows(DateTimeException.class, () ->
                service.validarDataDevolucao(data)
        );
    }

    @Test
    void deveAceitarDataDevolucaoValida() {
        LocalDate data = LocalDate.now().plusDays(10);

        assertDoesNotThrow(() ->
                service.validarDataDevolucao(data)
        );
    }

    // =========================
    // devolverLivro
    // =========================

    @Test
    void deveDevolverLivroComSucesso() {
        Emprestimo emprestimo = mock(Emprestimo.class);
        Livro livro = mock(Livro.class);

        when(emprestimo.getId()).thenReturn(10);
        when(livro.getId()).thenReturn(5);

        when(repository.pegarLivroPeloEmprestimo(10)).thenReturn(livro);

        service.devolverLivro(emprestimo);

        verify(repository).devolverLivro(5);
        verify(livroService).marcarLivroComoDisponivel(livro);
    }

    @Test
    void deveFalharSeEmprestimoInvalido() {
        Emprestimo emprestimo = mock(Emprestimo.class);

        when(emprestimo.getId()).thenReturn(10);
        when(repository.pegarLivroPeloEmprestimo(10)).thenReturn(null);

        assertThrows(RuntimeException.class, () ->
                service.devolverLivro(emprestimo)
        );

        verify(repository, never()).devolverLivro(anyInt());
    }

    // =========================
    // verEmprestimos
    // =========================

    @Test
    void deveRetornarListaDeEmprestimos() {
        when(repository.verEmprestimos()).thenReturn(List.of());

        List<Emprestimo> resultado = service.verEmprestimos();

        assertNotNull(resultado);
        verify(repository).verEmprestimos();
    }
}