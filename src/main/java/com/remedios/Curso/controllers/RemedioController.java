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
        return remedioRepository.findAllByAtivoTrue()
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
    @Transactional
    public void excluir(@PathVariable Long id){
        remedioRepository.deleteById(id);
    }

    @DeleteMapping("inativar/{id}")
    @Transactional
    public void inativar (@PathVariable Long id){
        var remedio = remedioRepository.getReferenceById(id);
        remedio.inativar();
    }


    @PutMapping("ativar/{id}")
    @Transactional
    public void ativar(@PathVariable Long id){
        var remedio = remedioRepository.getReferenceById(id);
        remedio.ativar();
    }
}
