package com.weg.biblioteca.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "livro")
@NoArgsConstructor
@AllArgsConstructor
public class Livro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String isbn;
    private Double preco;
    private LocalDate dataPublicacao;
    private String categoria;

    @ManyToOne
    @JoinColumn(name = "editora_id")
    private Editora editora;

    @ManyToMany
    @JoinTable(
            name = "livro_autores",
            joinColumns = @JoinColumn(name = "livro_id"),
            inverseJoinColumns = @JoinColumn(name = "autor_id")
    )
    private List<Autor> autores;

    public Livro(String titulo, String isbn, Double preco, LocalDate dataPublicacao, String categoria, Editora editora, List<Autor> autores) {
        this.titulo = titulo;
        this.isbn = isbn;
        this.preco = preco;
        this.dataPublicacao = dataPublicacao;
        this.categoria = categoria;
        this.editora = editora;
        this.autores = autores;
    }
}