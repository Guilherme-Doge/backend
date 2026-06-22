package com.weg.jpa_relacionamentos.controller;

import com.weg.jpa_relacionamentos.dto.ClienteRequestDto;
import com.weg.jpa_relacionamentos.dto.ClienteResponseDto;
import com.weg.jpa_relacionamentos.service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cliente")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService service;

    @PostMapping
    public ClienteResponseDto post(@RequestBody ClienteRequestDto dto) {
        return service.post(dto);
    }

    @GetMapping
    public List<ClienteResponseDto> list() {
        return service.list();
    }

    @GetMapping("/{id}")
    public ClienteResponseDto get(@PathVariable Long id) {
        return service.get(id);
    }

    @PutMapping("/{id}")
    public ClienteResponseDto put(@PathVariable Long id, @RequestBody ClienteRequestDto dto) {
        return service.put(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
