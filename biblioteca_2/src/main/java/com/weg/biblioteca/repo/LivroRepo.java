package com.weg.biblioteca.repo;

import com.weg.biblioteca.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LivroRepo extends JpaRepository<Livro, Long> {
    public Optional<Livro> findByTitulo(String titulo);

    public List<Livro> findByCategoriaAndPrecoLessThan(String categoria, Double preco);

    public List<Livro> findByPrecoBetween(Double minimo, Double maximo);

    public List<Livro> findByTituloContaining(String titulo);

    public List<Livro> findByCategoriaIn(List<String> categorias);

    public List<Livro> findByIsbnNotNull(List<String> categorias);
}
