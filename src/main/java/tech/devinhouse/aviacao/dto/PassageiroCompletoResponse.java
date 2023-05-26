package tech.devinhouse.aviacao.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import tech.devinhouse.aviacao.model.ClassificacaoPassageiro;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class PassageiroCompletoResponse {

    private Long cpf;

    private String nome;

    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd/MM/yyyy")
    private LocalDate dataNascimento;

    private ClassificacaoPassageiro classificacao;

    private Integer milhas;

    private String eticket;

    private String assento;

    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime dataHoraConfirmacao;

}
