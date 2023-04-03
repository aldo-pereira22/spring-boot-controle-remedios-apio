package com.remedios.Curso.controllers;

import com.remedios.Curso.remedio.DadosCadastroRemedio;
import com.remedios.Curso.remedio.DadosListagemRemedio;
import com.remedios.Curso.remedio.Remedio;
import com.remedios.Curso.remedio.RemedioRepository;
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
}
