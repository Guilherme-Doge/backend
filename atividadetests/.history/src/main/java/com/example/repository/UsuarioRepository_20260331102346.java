public class UsuarioRepository {
    private Connection connection;

    public UsuarioRepository(Connection connection) {
        this.connection = connection;
    }