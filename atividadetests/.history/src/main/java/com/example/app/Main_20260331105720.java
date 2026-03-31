package com.example.app;

import java.sql.Connection;
import java.sql.SQLException;

import com.example.config.ConnectionFactory;
import com.example.repository.EmprestimoRepository;
import com.example.repository.LivroRepository;
import com.example.repository.UsuarioRepository;
import com.example.service.EmprestimoService;
import com.example.service.LivroService;
import com.example.service.UsuarioService;

public class Main {
    public static void main(String[] args) {
        while (true) {
            try {
                Connection conn = ConnectionFactory.getConnection();

                LivroRepository livroRepo = new LivroRepository(conn);
                LivroService livreService = new LivroService(livroRepo);

                EmprestimoRepository emprestimoRepo = new EmprestimoRepository(conn);
                EmprestimoService emprestimoService = new EmprestimoService(emprestimoRepo);

                UsuarioRepository usuarioRepo = new UsuarioRepository(conn);
                UsuarioService usuarioService = new UsuarioService(usuarioRepo);
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void validarData(Local) {

    }
}