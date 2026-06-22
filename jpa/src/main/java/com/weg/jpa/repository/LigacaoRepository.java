package com.weg.jpa.repository;

import com.weg.jpa.model.Contato;

import com.weg.jpa.model.Ligacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LigacaoRepository extends JpaRepository<Ligacao, Long> {

}