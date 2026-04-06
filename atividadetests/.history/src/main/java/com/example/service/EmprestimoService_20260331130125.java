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

    public EmprestimoService(EmprestimoRepository repo, LivroService livroService) {
        this.repository = repo;
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

        repository.registerBorrow(usuario, livro, dataEmprestimo, dataDevolucao, this.livroService);
    }

    public void validarExistenciaUsuario(Usuario usuario) {
        Usuario usuarioBanco = repository.listarUsuarioPorId(usuario.getId());

        if (usuarioBanco == null) {
            throw new RuntimeException("Usuário não existe");
        }
    }

    public void validarDataEmprestimo(LocalDate data) {
        if (data.getYear() < LocalDate.now().getYear() - 100) {
            throw new DateTimeException("Data de empréstimo não pode ser mais de cem anos no passado");
        }

        if (data.isAfter(LocalDate.now())) {
            throw new DateTimeException("Data de empréstimo não pode ser depois da data atual");
        }
    }

    public void validarDataDevolucao(LocalDate data) {
        if (data.isBefore(LocalDate.now())) {
            throw new DateTimeException("Data de devolução não pode ser antes da data atual");
        }

        if (data.getYear() > LocalDate.now().getYear() + 100) {
            throw new DateTimeException("Data de devolução não pode ser mais de cem anos no futuro");
        }

    }

    public void devolverLivro(Emprestimo emprestimo) {
        Livro livro = repository.pegarLivroPeloEmprestimo(this.livroService, emprestimo.getId());
        repository.devolverLivro(livro.getId());
    }

    public void verEmprestimos() {
        List<Emprestimo> emprestimos = repository.verEmprestimos();

        System.out.println("ID | LIVRO_ID | USUARIO_ID | DATA_EMPRESTIMO | DATA_DEVOLUÇÃO");
        for (Emprestimo emprestimo : emprestimos) {
            System.out.println(emprestimo.getId() + " | " + 
            emprestimo.getLivroId() + " | " + 
            emprestimo.getUsuario().getId() + " | " + 
            emprestimo.getDataEmprestimo() + " | " + 
            emprestimo.getDataDevolucao());
        }
    }
}
