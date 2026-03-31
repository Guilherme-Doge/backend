package example.com.example.repository;

import java.sql.Connection;

public class UsuarioRepository {
    private Connection connection;

    public UsuarioRepository(Connection connection) {
        this.connection = connection;
    }