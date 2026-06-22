package com.weg.jpa_relacionamentos.repo;

import com.weg.jpa_relacionamentos.model.Departamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartamentoRepo extends JpaRepository<Departamento, Long> {
}
