package com.weg.jpa.repository;

import com.weg.jpa.model.Conta;
import com.weg.jpa.model.Grupo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GrupoRepository extends JpaRepository<Grupo, Long> {

    List<Grupo> findByMembros_Id(Long id);
}