package com.example.app;

import java.sql.Connection;

import com.example.config.ConnectionFactory;
import com.example.repository.LivroRepository;
import com.example.repository.impl.LivroRepositoryImpl;
import com.example.service.LivroService;

public class Main {
    public static void main(String[] args) {
        while (true) {
            try {
                Connection conn = ConnectionFactory.getConnection();

                LivroRepository repo = new LivroRepositoryImpl(conn);
                LivroService service = new LivroService(repo);
            } catch (SQL)
        }
    }
}