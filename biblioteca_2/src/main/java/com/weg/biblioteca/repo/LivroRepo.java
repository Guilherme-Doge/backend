package com.weg.biblioteca.repo;

import com.weg.biblioteca.model.Livro;
import com.weg.biblioteca.projection.LivroMinimoProjection;
import com.weg.biblioteca.projection.LivroSimplesProjection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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

    public List<Livro> findByIsbnIsNull();

    public List<Livro> findByEditoraIdOrderByTitulo(Long id);

    public Long countByAutoresNacionalidade(String nacionalidade);

    @Query("""
        SELECT titulo
        FROM Livro
        WHERE categoria = :categoria""")
    public List<String> findLivroTituloByCategoria(@Param("categoria") String categoria);

    @Query("""
        SELECT l
        FROM Livro l
        JOIN Autor a
        ON a.id = a.id
        WHERE a.nome = :nome""")
    public List<Livro> findByAutorNome(@Param("nome") String nome);

    @Query("""
        SELECT l
        FROM Livro l
        JOIN FETCH Autor a
        ON a.id = a.id""")
    public List<Livro> findAllJoinAutor();

    @Query("""
        SELECT AVG(l.preco)
        FROM Livro l
        JOIN Editora e
        ON e.id = :id""")
    public Double findAveragePrecoByEditora(@Param("id") Long id);

    @Query("""
        SELECT l
        FROM Livro l
        WHERE l.preco > (SELECT AVG(l2.preco) FROM Livro l2)""")
    public List<Livro> findLivroPrecoBiggerThanAverage();

    @NativeQuery("""
        SELECT l.id AS livro_id,
               l.titulo,
               l.isbn,
               l.preco,
               l.data_publicacao,
               l.categoria,
               e.id AS editora_id,
               e.nome AS editora_nome,
               a.id AS autor_id,
               a.nome AS autor_nome,
               a.nacionalidade
        FROM Livro l
        JOIN Editora e ON e.id = l.editora_id
        JOIN livro_autores la ON l.id = la.livro_id
        JOIN Autor a ON la.autor_id = a.id
        WHERE EXTRACT(YEAR FROM l.data_publicacao) > 2023""")
    public List<Livro> findLivroWhereDataPublicacaoAfter2023();

    @NativeQuery("""
        SELECT l.id AS livro_id,
               l.titulo,
               l.isbn,
               l.preco,
               l.data_publicacao,
               l.categoria,
               e.id AS editora_id,
               e.nome AS editora_nome,
               a.id AS autor_id,
               a.nome AS autor_nome,
               a.nacionalidade
        FROM Livro l
        JOIN Editora e ON e.id = l.editora_id
        NATURAL JOIN livro_autores
        NATURAL JOIN autor a
        WHERE a.nacionalidade IN ('Brasileiro', 'Brasileira', 'brasileiro', 'brasileira')""")
    public List<Livro> findLivroByAutorBrasileiro();

    @NativeQuery("""
        SELECT l.id AS livro_id,
               l.titulo,
               l.isbn,
               l.preco,
               l.data_publicacao,
               l.categoria,
               e.id AS editora_id,
               e.nome AS editora_nome,
               a.id AS autor_id,
               a.nome AS autor_nome,
               a.nacionalidade
        FROM Livro l
        JOIN Editora e ON e.id = l.editora_id
        JOIN livro_autores la ON l.id = la.livro_id
        JOIN Autor a ON la.autor_id = a.id
        WHERE LOWER(l.categoria) = LOWER(:categoria)""")
    public List<Livro> findByCategoriaLower(@Param("categoria") String categoria);

    @NativeQuery("""
        SELECT l.titulo,
               l.preco
        FROM livro l""")
    public List<LivroMinimoProjection> findAllMinimo();

    @NativeQuery("""
        SELECT titulo AS tituloLivro,
            isbn AS isbnLivro,
            preco AS precoLivro,
            data_publicacao AS dataPublicacaoLivro,
            categoria AS categoriaLivro
        FROM Livro l""")
    public List<LivroSimplesProjection> findAllSimples();

    @Query("""
        SELECT l.id AS livroId,
            l.titulo AS titulo,
            l.isbn AS isbn,
            l.preco AS preco,
            l.dataPublicacao AS dataPublicacao,
            l.categoria AS categoria,
            e.id AS editoraId,
            e.nome AS editoraNome,
            a.id AS autorId,
            a.nome AS autorNome,
            a.nacionalidade AS nacionalidade
        FROM Livro l
        JOIN l.editora e
        JOIN l.autores a""")
    public <T> List<T> findAllDynamic(Class<T> type);
}
