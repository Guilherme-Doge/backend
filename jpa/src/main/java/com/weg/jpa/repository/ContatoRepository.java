package com.weg.jpa.repository;

import com.weg.jpa.model.Contato;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContatoRepository extends JpaRepository<Contato, Long> {

}