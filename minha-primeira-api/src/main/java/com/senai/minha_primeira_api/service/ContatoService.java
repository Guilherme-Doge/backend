package com.senai.minha_primeira_api.service;

import com.senai.minha_primeira_api.dto.ContatoRequestDto;
import com.senai.minha_primeira_api.dto.ContatoResponseDto;
import com.senai.minha_primeira_api.model.Contato;
import com.senai.minha_primeira_api.repository.ContatoRepository;
import org.springframework.stereotype.Service;
import com.senai.minha_primeira_api.mapper.*;

import java.sql.SQLException;
import java.util.List;

@Service
public class ContatoService {
    private final ContatoRepository contatoRepository;
    private final ContatoMapper contatoMapper;

    public ContatoService(
            ContatoRepository contatoRepository,
            ContatoMapper contatoMapper){
        this.contatoRepository = contatoRepository;
        this.contatoMapper = contatoMapper;
    }

    public ContatoResponseDto criarContato(
            ContatoRequestDto contatoRequisicaoDto) throws SQLException {
        Contato contato
                = contatoMapper.paraEntidade(contatoRequisicaoDto);

        contatoRepository.saveContato(contato);

        return contatoMapper.paraRespostaDto(contato);
    }
    
    public List<ContatoResponseDto> obterContatos() throws SQLException{
        List<ContatoResponseDto> contatosResponseDto = contatoRepository.findAllContatos()
                .stream()
                .map(contato -> new ContatoResponseDto(contato.getId(), contato.getNome(), contato.getNumero()))
                .toList();

        return contatosResponseDto;
    }

    public ContatoResponseDto buscarPorId(Long id) throws SQLException{
        ContatoResponseDto contatoResponseDto = contatoMapper.paraRespostaDto(contatoRepository.findContatoPorId(id)
                .orElseThrow(() -> new RuntimeException("O contato não foi encontrado!")));

        return contatoResponseDto;
    }

    public ContatoResponseDto atualizarContato(Long id, ContatoRequestDto contatoRequestDto)throws SQLException{
        if(!contatoRepository.existsPorId(id)){
            throw new RuntimeException("Usuário não encontrado!");
        }

        Contato contato = contatoMapper.paraEntidade(contatoRequestDto);

        contato.setId(id);
        contatoRepository.atualizarContato(contato);

        ContatoResponseDto contatoResponseDto = contatoMapper.paraRespostaDto(contato);

        return contatoResponseDto;
    }

    public void deletarContatoPorId(Long id) throws SQLException{
        if(!contatoRepository.existsPorId(id)){
            throw new RuntimeException("Usuário não encontrado!");
        }

        contatoRepository.deletePorId(id);
    }
}
