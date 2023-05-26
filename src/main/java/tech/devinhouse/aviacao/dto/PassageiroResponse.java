package tech.devinhouse.aviacao.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import tech.devinhouse.aviacao.model.ClassificacaoPassageiro;

import java.time.LocalDate;

@Data
public class PassageiroResponse {

    private Long cpf;

    private String nome;

    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd/MM/yyyy")
    private LocalDate dataNascimento;

    private ClassificacaoPassageiro classificacao;

    private Integer milhas;
}
