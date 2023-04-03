package com.remedios.Curso.controllers;

import com.remedios.Curso.remedio.DadosCadastroRemedio;
import com.remedios.Curso.remedio.Remedio;
import com.remedios.Curso.remedio.RemedioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/remedios")
public class RemedioController {

    @Autowired
    private RemedioRepository remedioRepository;

    @PostMapping
    public void cadastrar( @RequestBody @Valid DadosCadastroRemedio dados){
        remedioRepository.save( new Remedio(dados));
    }
}
