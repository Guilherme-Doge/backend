package com.weg.jpa.repository;

import com.weg.jpa.model.Conta;
import com.weg.jpa.model.Contato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GrupoRepository extends JpaRepository<Conta, Long> {

    List<Contato> findByContaId(Long contaId);
}
