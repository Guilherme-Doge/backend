package com.weg.biblioteca.repo;

import com.weg.biblioteca.model.Editora;
import com.weg.biblioteca.projection.EstatisticasEditoraProjection;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EditoraRepo extends JpaRepository<Editora, Long> {

    @Query("""
        SELECT new com.weg.biblioteca.projection.EstatisticasEditoraProjection(e.nome, COUNT(l))
        FROM Editora e
        LEFT JOIN e.livros l
        GROUP BY e.id, e.nome""")
    public List<EstatisticasEditoraProjection> findAllWithNumberOfBooks();
}
