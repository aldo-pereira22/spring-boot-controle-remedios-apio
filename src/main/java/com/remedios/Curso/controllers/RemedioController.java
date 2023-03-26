package com.remedios.Curso.controllers;

import com.remedios.Curso.remedio.DadosCadastroRemedio;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/remedios")
public class RemedioController {

    @PostMapping
    public void cadastrar( @RequestBody DadosCadastroRemedio dados){
        System.out.println("Json: "+ );
    }
}
