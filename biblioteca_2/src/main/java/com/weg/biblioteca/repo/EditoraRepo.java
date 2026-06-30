package com.weg.biblioteca.repo;

import com.weg.biblioteca.model.Editora;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EditoraRepo extends JpaRepository<Editora, Long> {

}
