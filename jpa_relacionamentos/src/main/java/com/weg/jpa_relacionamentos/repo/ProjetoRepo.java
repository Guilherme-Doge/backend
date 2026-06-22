package com.weg.jpa_relacionamentos.repo;

import com.weg.jpa_relacionamentos.model.Projeto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjetoRepo extends JpaRepository<Projeto, Long> {
}
