package com.weg.minha_primeira_api.service;

import com.weg.minha_primeira_api.model.Contato;
import com.weg.minha_primeira_api.repo.ContatoRepo;
import org.springframework.stereotype.Service;

import java.sql.SQLDataException;
import java.sql.SQLException;
import java.util.List;

@Service
public class ContatoServiceImpl implements ContatoService {

    private final ContatoRepo repo;

    public ContatoServiceImpl(ContatoRepo repo) {
        this.repo = repo;
    }

    @Override
    public void createContato(String nome, String numero) {
        try {
            if (nome == null) {
                throw new IllegalArgumentException("Nome não pode ser nulo");
            } else if (nome.length() < 3) {
                throw new IllegalArgumentException("Nome não pode ser menor que 3 caracteres");
            }

            Contato contato = new Contato(nome, numero);

            repo.createContato(contato);

        } catch (IllegalArgumentException | SQLException e) {
            System.err.println("Erro ao salvar contato: " + e.getMessage());

            throw new RuntimeException("Falha ao criar o contato: " + e.getMessage(), e);
        }
    }

    @Override
    public List<Contato> readAllContato() {

        try {
            return repo.readAllContato();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void updateContatoById(String nome, String numero, long id) {

        try {
            for (Contato contato : repo.readAllContato()) {
                if (contato.getId() == id) {
                    repo.updateContatoById(new Contato(id, nome, numero));
                    return;
                }
            }
            throw new SQLDataException("Contato não encontrado");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void deleteContatoById(long id) {

        try {
            for (Contato contato : repo.readAllContato()) {
                if (contato.getId() == id) {
                    repo.deleteContatoById(id);
                    return;
                }
            }
            throw new SQLDataException("Contato não encontrado");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }


}
