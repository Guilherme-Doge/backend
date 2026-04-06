package com.example.app;

import com.example.repository.LivroRepository;
import com.example.repository.impl.EmprestimoRepositoryImpl;
import com.example.repository.impl.LivroRepositoryImpl;
import com.example.service.LivroService;
import com.example.service.impl.EmprestimoService;
import com.example.service.impl.LivroServiceImpl;
import com.example.service.impl.UsuarioService;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        while (true) {
            try {
                LivroRepository livroRepo = new LivroRepositoryImpl();
                LivroService livroService = new LivroServiceImpl();

                EmprestimoRepositoryImpl emprestimoRepo = new EmprestimoRepositoryImpl();
                EmprestimoService emprestimoService = new EmprestimoService();

                UsuarioRepository usuarioRepo = new UsuarioRepository();
                UsuarioService usuarioService = new UsuarioService();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}