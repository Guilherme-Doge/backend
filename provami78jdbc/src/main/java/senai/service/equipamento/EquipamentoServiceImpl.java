package senai.service.equipamento;

import senai.model.Equipamento;
import senai.repo.equipamento.EquipamentoRepo;
import senai.repo.equipamento.EquipamentoRepoImpl;

import java.sql.SQLException;
import java.util.List;

public class EquipamentoServiceImpl implements EquipamentoService {

    private EquipamentoRepo repo = new EquipamentoRepoImpl();

    @Override
    public Equipamento criarEquipamento(Equipamento equipamento) throws SQLException {
        try {
            return repo.criarEquipamento(equipamento);
        } catch (SQLException e) {
            throw new RuntimeException("Fornecedor inválido ou inexistente!");
        }
    }

    @Override
    public Equipamento buscarPorId(int id) throws SQLException {
        return repo.buscarPorId(id);
    }

    @Override
    public List<Equipamento> buscarPorFornecedorId(int fornecedorId) throws SQLException {
        return repo.buscarPorFornecedorId(fornecedorId);
    }

    @Override
    public void atualizarEquipamento(Equipamento equipamento) throws SQLException {
        try {
            repo.buscarPorId(equipamento.getId());
        } catch (RuntimeException e) {
            throw new RuntimeException("Equipamento não encontrado para atualização!");
        }

        repo.atualizarEquipamento(equipamento);
    }

    @Override
    public void deletarEquipamento(int id) throws SQLException {
        try {
            repo.buscarPorId(id);
        } catch (RuntimeException e) {
            throw new RuntimeException("Equipamento não encontrado para exclusão!");
        }

        repo.deletarEquipamento(id);
    }
}