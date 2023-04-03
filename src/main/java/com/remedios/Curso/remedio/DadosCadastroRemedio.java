package com.remedios.Curso.remedio;

import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record DadosCadastroRemedio(


        @NotBlank
        String nome,

        @Enumerated
        Via via,
        @NotBlank
        String lote,

        int quantidade,

        @Future
        LocalDate validade,

        @Enumerated
        Laboratorio laboratorio
) {

}
