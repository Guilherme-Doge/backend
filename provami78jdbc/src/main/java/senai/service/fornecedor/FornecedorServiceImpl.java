package senai.service.fornecedor;

import senai.database.Conexao;
import senai.model.Fornecedor;
import senai.repo.fornecedor.FornecedorRepo;
import senai.repo.fornecedor.FornecedorRepoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FornecedorServiceImpl implements FornecedorService{

    private FornecedorRepo repo = new FornecedorRepoImpl();

    @Override
    public Fornecedor criarFornecedor(Fornecedor fornecedor) throws SQLException {
        return repo.criarFornecedor(fornecedor);
    }

    @Override
    public Fornecedor buscarPorId(int id) throws SQLException {
        return repo.buscarPorId(id);
    }

    @Override
    public List<Fornecedor> buscarTodos() throws SQLException {
        return repo.buscarTodos();
    }

    @Override
    public void atualizarFornecedor(Fornecedor fornecedor) throws SQLException {
        repo.atualizarFornecedor(fornecedor);
    }

    @Override
    public void deletarFornecedor(int id) throws SQLException {
        deletarFornecedor(id);
    }
}
