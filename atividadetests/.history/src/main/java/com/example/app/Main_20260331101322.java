package com.example.app;

import java.sql.Connection;
import java.sql.SQLException;

import com.example.config.ConnectionFactory;
import com.example.repository.EmprestimoRepository;
import com.example.repository.LivroRepository;
import com.example.service.LivroService;

public class Main {
    public static void main(String[] args) {
        while (true) {
            try {
                Connection conn = ConnectionFactory.getConnection();

                LivroRepository livroRepo = new LivroRepository(conn);
                LivroService livreService = new LivroService(repo);

                EmprestimoRepository repo = new LivroRepository(conn);
                Emprestimo service = new LivroService(repo)

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}