package tech.devinhouse.aviacao.dto;

import lombok.Data;
import tech.devinhouse.aviacao.model.ClassificacaoPassageiro;

import java.time.LocalDate;

@Data
public class PassageiroResponse {

    private Long cpf;

    private String nome;

    private LocalDate dataNascimento;

    private ClassificacaoPassageiro classificacao;

    private Integer milhas;
}
