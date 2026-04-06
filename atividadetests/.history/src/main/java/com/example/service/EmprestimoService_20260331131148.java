package com.example.service;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.List;

import com.example.model.Emprestimo;
import com.example.model.Livro;
import com.example.model.Usuario;
import com.example.repository.EmprestimoRepository;

public class EmprestimoService {

    private final EmprestimoRepository repository;
    private final LivroService livroService;

    public EmprestimoService(EmprestimoRepository repository, LivroService livroService) {
        this.repository = repository;
        this.livroService = livroService;
    }

    public void registrarEmprestimo(Usuario usuario,
                                     Livro livro,
                                     LocalDate dataEmprestimo,
                                     LocalDate dataDevolucao) {

        validarExistenciaUsuario(usuario);
        livroService.validarExistenciaLivro(livro);

        validarDataEmprestimo(dataEmprestimo);
        validarDataDevolucao(dataDevolucao);

        repository.registerBorrow(usuario, livro, dataEmprestimo, dataDevolucao);
        livroService.marcarLivroComoNaoDisponivel(livro);
    }

    public void validarExistenciaUsuario(Usuario usuario) {
        Usuario usuarioBanco = repository.listarUsuarioPorId(usuario.getId());

        if (usuarioBanco == null) {
            throw new RuntimeException("Usuário não existe");
        }
    }

    public void validarDataEmprestimo(LocalDate data) {

        if (data.getYear() < LocalDate.now().getYear() - 100) {
            throw new DateTimeException("Data de empréstimo muito antiga");
        }

        if (data.isAfter(LocalDate.now())) {
            throw new DateTimeException("Data de empréstimo no futuro");
        }
    }

    public void validarDataDevolucao(LocalDate data) {

        if (data.isBefore(LocalDate.now())) {
            throw new DateTimeException("Data de devolução no passado");
        }

        if (data.getYear() > LocalDate.now().getYear() + 100) {
            throw new DateTimeException("Data de devolução muito distante");
        }
    }

    public void devolverLivro(Emprestimo emprestimo) {

        Livro livro = repository.pegarLivroPeloEmprestimo(emprestimo.getId());

        if (livro == null) {
            throw new RuntimeException("Empréstimo inválido");
        }

        repository.devolverLivro(livro.getId());
        livroService.marcarLivroComoDisponivel(livro);
    }

    public List<Emprestimo> verEmprestimos() {
        return repository.verEmprestimos();
    }
}