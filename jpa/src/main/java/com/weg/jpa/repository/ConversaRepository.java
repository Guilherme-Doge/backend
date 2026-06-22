package com.weg.jpa.repository;

import com.weg.jpa.model.Conta;
import com.weg.jpa.model.Contato;
import com.weg.jpa.model.Conversa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConversaRepository extends JpaRepository<Conversa, Long> {
}
