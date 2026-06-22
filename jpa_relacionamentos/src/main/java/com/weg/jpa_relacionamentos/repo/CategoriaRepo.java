package com.weg.jpa_relacionamentos.repo;

import com.weg.jpa_relacionamentos.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoriaRepo extends JpaRepository<Categoria, Long> {
}
