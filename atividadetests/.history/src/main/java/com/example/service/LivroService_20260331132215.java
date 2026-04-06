package com.example.service;

import java.util.List;

import com.example.model.Livro;
import com.example.repository.LivroRepository;

public class LivroService {

    private final LivroRepository repository;

    public LivroService(LivroRepository repository) {
        this.repository = repository;
    }

    public void registerBook(String title, String author, int publicationYear) {
        repository.registerBook(title, author, publicationYear);
    }

    public void validarExistenciaLivro(Livro livro) {

        Livro livroBanco = repository.listarLivroPorId(livro.getId());

        if (livroBanco == null) {
            throw new RuntimeException("Livro não existe");
        }
    }

    public void marcarLivroComoNaoDisponivel(Livro livro) {
        repository.marcarLivroComoNaoDisponivel(livro.getId());
    }

    public void marcarLivroComoDisponivel(Livro livro) {
        repository.marcarLivroComoDisponivel(livro.getId());
    }

    public List<Livro> listarLivros() {
        return repository.listarLivros();
    }
}