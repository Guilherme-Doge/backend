package com.senai.minha_primeira_api.controller;

import com.senai.minha_primeira_api.dto.ContatoRequestDto;
import com.senai.minha_primeira_api.dto.ContatoResponseDto;
import com.senai.minha_primeira_api.model.Contato;
import com.senai.minha_primeira_api.service.ContatoService;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/contatos")
public class ContatoController {
    private final ContatoService contatoService;

    public ContatoController(ContatoService contatoService){
        this.contatoService = contatoService;
    }

    @PostMapping
    public ContatoResponseDto postContato(
            @RequestBody ContatoRequestDto requisicaoDto){
        try{
            ContatoResponseDto repostaDto = contatoService.criarContato(requisicaoDto);
            return repostaDto;
        }catch (SQLException | RuntimeException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping
    public List<ContatoResponseDto> buscarContatos(){
        try{
            List<ContatoResponseDto> contatos = contatoService.obterContatos();
            return contatos;
        }catch (SQLException | RuntimeException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ContatoResponseDto buscarContatoPorId(@PathVariable Long id){
        try{
            ContatoResponseDto contato = contatoService.buscarPorId(id);
            return contato;
        }catch (SQLException | RuntimeException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ContatoResponseDto AlterarContato(@PathVariable Long id, @RequestBody ContatoRequestDto contato){
        try{
            return contatoService.atualizarContato(id,contato);
        }catch (SQLException | RuntimeException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public void deletarPorId(@PathVariable Long id){
        try{
            contatoService.deletarContatoPorId(id);
        }catch (SQLException | RuntimeException e){
            throw new RuntimeException(e.getMessage());
        }
    }
}
