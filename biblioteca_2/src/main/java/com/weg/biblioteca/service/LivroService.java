package com.weg.biblioteca.service;

import com.weg.biblioteca.dto.AutorResponseDto;
import com.weg.biblioteca.dto.EditoraResponseDto;
import com.weg.biblioteca.dto.LivroRequestDto;
import com.weg.biblioteca.dto.LivroResponseDto;
import com.weg.biblioteca.mapper.AutorMapper;
import com.weg.biblioteca.mapper.EditoraMapper;
import com.weg.biblioteca.mapper.LivroMapper;
import com.weg.biblioteca.model.Autor;
import com.weg.biblioteca.model.Editora;
import com.weg.biblioteca.model.Livro;
import com.weg.biblioteca.projection.LivroMinimoProjection;
import com.weg.biblioteca.projection.LivroSimplesProjection;
import com.weg.biblioteca.repo.AutorRepo;
import com.weg.biblioteca.repo.EditoraRepo;
import com.weg.biblioteca.repo.LivroRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LivroService {

    private final LivroMapper livroMapper;
    private final EditoraMapper editoraMapper;
    private final AutorMapper autorMapper;
    private final LivroRepo livroRepo;
    private final EditoraRepo editoraRepo;
    private final AutorRepo autorRepo;

    public LivroResponseDto save(LivroRequestDto livroRequestDto) {
        List<Autor> autores = new ArrayList<>();
        for (Long id : livroRequestDto.autoresId()) {
            autores.add(autorRepo.findById(id)
                    .orElseThrow(() -> new RuntimeException("Autor não encontrado com o ID: " + id)));
        }

        Editora editora = editoraRepo.findById(livroRequestDto.editoraId())
                .orElseThrow(() -> new RuntimeException("Editora não encontrada com o ID: " + livroRequestDto.editoraId()));

        Livro livro = livroMapper.toEntity(livroRequestDto, editora, autores);
        livroRepo.save(livro);

        return mapearParaResponse(livro, editora, autores);
    }

    public LivroResponseDto getById(Long id) {
        Livro livro = livroRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Livro não encontrado"));

        return mapearParaResponse(livro, livro.getEditora(), livro.getAutores());
    }

    public List<LivroResponseDto> getByCategoriaAndPrecoLessThan(String categoria, Double preco) {
        List<Livro> livros = livroRepo.findByCategoriaAndPrecoLessThan(categoria, preco);

        return livros.stream()
                .map(livro -> this.mapearParaResponse(livro, livro.getEditora(), livro.getAutores()))
                .toList();
    }

    public List<LivroResponseDto> getByPrecoBetween(Double minimo, Double maximo) {
        List<Livro> livros = livroRepo.findByPrecoBetween(minimo, maximo);

        return livros.stream()
                .map(livro -> this.mapearParaResponse(livro, livro.getEditora(), livro.getAutores()))
                .toList();
    }

    public List<LivroResponseDto> getByTituloContaining(String titulo) {
        List<Livro> livros = livroRepo.findByTituloContaining(titulo);

        return livros.stream()
                .map(livro -> this.mapearParaResponse(livro, livro.getEditora(), livro.getAutores()))
                .toList();
    }
    public List<LivroResponseDto> findByCategoriaIn(List<String> categorias) {
        List<Livro> livros = livroRepo.findByCategoriaIn(categorias);

        return livros.stream()
                .map(livro -> this.mapearParaResponse(livro, livro.getEditora(), livro.getAutores()))
                .toList();
    }

    public List<LivroResponseDto> findByIsbnNull() {
        List<Livro> livros = livroRepo.findByIsbnIsNull();

        return livros.stream()
                .map(livro -> this.mapearParaResponse(livro, livro.getEditora(), livro.getAutores()))
                .toList();
    }

    public List<LivroResponseDto> findByEditoraId(Long id) {
        List<Livro> livros = livroRepo.findByEditoraIdOrderByTitulo(id);

        return livros.stream()
                .map(livro -> this.mapearParaResponse(livro, livro.getEditora(), livro.getAutores()))
                .toList();
    }

    public Long countByAutorNacionalidade(String nacionalidade) {
        return livroRepo.countByAutoresNacionalidade(nacionalidade);
    }

    public List<String> getLivroTituloByCategoria(String categoria) {
        return livroRepo.findLivroTituloByCategoria(categoria);
    }

    public List<LivroResponseDto> findByAutorNome(String nome) {
        List<Livro> livros = livroRepo.findByAutorNome(nome);

        return livros.stream()
                .map(livro -> mapearParaResponse(livro, livro.getEditora(), livro.getAutores()))
                .toList();
    }

    public List<LivroResponseDto> getAll() {
        List<Livro> livros = livroRepo.findAll();

        return livros.stream()
                .map(livro -> mapearParaResponse(livro, livro.getEditora(), livro.getAutores()))
                .toList();
    }

    public LivroResponseDto findByName(String nome) {
        Livro livro = livroRepo.findByTitulo(nome)
                .orElseThrow(() -> new RuntimeException("Livro não encontrado"));

        return mapearParaResponse(livro, livro.getEditora(), livro.getAutores());
    }

    public List<LivroResponseDto> findAllJoinAutor() {
        List<Livro> livros = livroRepo.findAllJoinAutor();

        return livros.stream()
                .map(livro -> mapearParaResponse(livro, livro.getEditora(), livro.getAutores()))
                .toList();
    }

    public Double findAveragePrecoByEditora(Long id) {
        return livroRepo.findAveragePrecoByEditora(id);
    }

    public List<LivroResponseDto> findLivroPrecoBiggerThanAverage() {
        List<Livro> livros = livroRepo.findLivroPrecoBiggerThanAverage();

        return livros.stream()
                .map(livro -> mapearParaResponse(livro, livro.getEditora(), livro.getAutores()))
                .toList();
    }

    public List<LivroResponseDto> findLivroWhereDataPublicacaoAfter2023() {
        List<Livro> livros = livroRepo.findLivroWhereDataPublicacaoAfter2023();

        return livros.stream()
                .map(livro -> mapearParaResponse(livro, livro.getEditora(), livro.getAutores()))
                .toList();
    }

    public List<LivroResponseDto> findLivroByAutorBrasileiro() {
        List<Livro> livros = livroRepo.findLivroByAutorBrasileiro();

        return livros.stream()
                .map(livro -> mapearParaResponse(livro, livro.getEditora(), livro.getAutores()))
                .toList();
    }

    public List<LivroResponseDto> findLivroByCategoria() {
        List<Livro> livros = livroRepo.findLivroByAutorBrasileiro();

        return livros.stream()
                .map(livro -> mapearParaResponse(livro, livro.getEditora(), livro.getAutores()))
                .toList();
    }

    public List<LivroMinimoProjection> findAllLivroMinimo() {
        List<LivroMinimoProjection> livros = livroRepo.findAllMinimo();

        return livros;
    }

    public List<LivroSimplesProjection> findAllSimples() {
        List<LivroSimplesProjection> livros = livroRepo.findAllSimples();

        return livros;
    }

    public LivroResponseDto update(LivroRequestDto livroRequestDto, Long id) {
        Livro livro = livroRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Livro não encontrado"));

        if (livroRequestDto.titulo() != null) livro.setTitulo(livroRequestDto.titulo());
        if (livroRequestDto.isbn() != null) livro.setIsbn(livroRequestDto.isbn());
        if (livroRequestDto.preco() != null) livro.setPreco(livroRequestDto.preco());
        if (livroRequestDto.dataPublicacao() != null) livro.setDataPublicacao(livroRequestDto.dataPublicacao());
        if (livroRequestDto.categoria() != null) livro.setCategoria(livroRequestDto.categoria());

        if (livroRequestDto.editoraId() != null) {
            Editora editora = editoraRepo.findById(livroRequestDto.editoraId())
                    .orElseThrow(() -> new RuntimeException("Editora não encontrada"));
            livro.setEditora(editora);
        }

        if (livroRequestDto.autoresId() != null) {
            List<Autor> autores = new ArrayList<>();
            for (Long autorId : livroRequestDto.autoresId()) {
                autores.add(autorRepo.findById(autorId)
                        .orElseThrow(() -> new RuntimeException("Autor não encontrado")));
            }
            livro.setAutores(autores);
        }

        return mapearParaResponse(livro, livro.getEditora(), livro.getAutores());
    }

    public void delete(Long id) {
        if (!livroRepo.existsById(id)) {
            throw new RuntimeException("Livro não encontrado");
        }
        livroRepo.deleteById(id);
    }

    private LivroResponseDto mapearParaResponse(Livro livro, Editora editora, List<Autor> autores) {
        EditoraResponseDto editoraDto = editoraMapper.toResponse(editora);
        List<AutorResponseDto> autoresDto = autores.stream()
                .map(autorMapper::toResponse)
                .toList();

        return livroMapper.toResponse(livro, editoraDto, autoresDto);
    }
}