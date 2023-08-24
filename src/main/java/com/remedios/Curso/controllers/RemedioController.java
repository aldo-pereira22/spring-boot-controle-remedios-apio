package com.remedios.Curso.controllers;

import com.remedios.Curso.remedio.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/remedios")
public class RemedioController {

    @Autowired
    private RemedioRepository remedioRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoRemedio> cadastrar(@RequestBody @Valid DadosCadastroRemedio dados, UriComponentsBuilder uriBuilder){
        var remedio = new Remedio(dados);
        remedioRepository.save( remedio);

        var uri = uriBuilder.path("/remedios/{id}").buildAndExpand(remedio.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoRemedio(remedio));
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

    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoRemedio> buscarPorId(@PathVariable Long id){
        var remedio = remedioRepository.getReferenceById(id);

        return ResponseEntity.ok(new DadosDetalhamentoRemedio(remedio));
    }
}
