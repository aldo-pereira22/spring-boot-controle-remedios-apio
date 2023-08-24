package com.remedios.Curso.controllers;

import com.remedios.Curso.remedio.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<DadosListagemRemedio>> listar(){
        var lista =  remedioRepository.findAllByAtivoTrue()
                .stream()
                .map(DadosListagemRemedio::new).toList();
        return ResponseEntity.ok().body(lista);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoRemedio> atualizar(@RequestBody @Valid DadosAtualizarRemedio dados){
        var remedio = remedioRepository.getReferenceById(dados.id());
        remedio.atualizarInformacoes(dados);
        return  ResponseEntity.ok(new DadosDetalhamentoRemedio(remedio));
    }

    @DeleteMapping(value = "/{id}")
    @Transactional
    public ResponseEntity<Void> excluir(@PathVariable Long id){
        remedioRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("inativar/{id}")
    @Transactional
    public ResponseEntity<Void> inativar (@PathVariable Long id){
        var remedio = remedioRepository.getReferenceById(id);
        remedio.inativar();
        return ResponseEntity.noContent().build();
    }


    @PutMapping("ativar/{id}")
    @Transactional
    public ResponseEntity<Void> ativar(@PathVariable Long id){
        var remedio = remedioRepository.getReferenceById(id);
        remedio.ativar();
        return  ResponseEntity.noContent().build();
    }
}
