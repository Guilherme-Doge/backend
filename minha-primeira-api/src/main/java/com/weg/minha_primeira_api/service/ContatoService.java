package com.weg.minha_primeira_api.service;

import com.weg.minha_primeira_api.model.Contato;

import java.util.List;

public interface ContatoService {
    public void createContato(String nome, String numero);

    public List<Contato> readAllContato();

    public Contato readContatoById(long id);

    public void updateContatoById(String nome, String numero, long id);

    public void deleteContatoById(long id);
}
