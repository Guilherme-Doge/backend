package com.weg.jpa_relacionamentos_o_inimigo_agora_e_o_mesmo.repo;

import com.weg.jpa_relacionamentos_o_inimigo_agora_e_o_mesmo.model.Musica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MusicaRepo extends JpaRepository<Musica, Long> {
}
