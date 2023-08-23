package com.remedios.Curso.controllers;

import com.remedios.Curso.remedio.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/remedios")
public class RemedioController {

    @Autowired
    private RemedioRepository remedioRepository;

    @PostMapping
    @Transactional
    public void cadastrar( @RequestBody @Valid DadosCadastroRemedio dados){
        remedioRepository.save( new Remedio(dados));
    }

    @GetMapping
    public List<DadosListagemRemedio> listar(){
        return remedioRepository.findAll()
                .stream()
                .map(DadosListagemRemedio::new).toList();
    }

    @PutMapping
    @Transactional
    public void atualizar(@RequestBody @Valid DadosAtualizarRemedio dados){
        var remedio = remedioRepository.getReferenceById(dados.id());
        remedio.atualizarInformacoes(dados);
    }

    @DeleteMapping(value = "/{id}")
    public void excluir(@PathVariable Long id){
        remedioRepository.deleteById(id);
    }
}
