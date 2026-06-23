package com.weg.jpa_relacionamentos_o_inimigo_agora_e_o_mesmo.repo;

import com.weg.jpa_relacionamentos_o_inimigo_agora_e_o_mesmo.model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CursoRepo extends JpaRepository<Curso, Long> {
    boolean existsByNome(String nome);
}
