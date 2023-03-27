package com.remedios.Curso.remedio;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.boot.autoconfigure.domain.EntityScan;


@Entity(name = "remedios")
@Table(name = "Remedios")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Remedio {

    public Remedio(DadosCadastroRemedio dados) {
        this.nome = dados.nome();
        this.via = dados.via();
        this.lote = dados.lote();
        this.quantidade = dados.quantidade();
        this.validade= dados.validade();
        this.laboratorio = dados.laboratorio();
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String nome;

    @Enumerated(EnumType.STRING)
    private Via via;
    private  String lote;
    private String quantidade;
    private  String validade;

    @Enumerated(EnumType.STRING)
    private  Laboratorio laboratorio;
}
