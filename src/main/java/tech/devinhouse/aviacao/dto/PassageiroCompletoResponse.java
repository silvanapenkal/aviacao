package tech.devinhouse.aviacao.dto;

import lombok.Data;
import tech.devinhouse.aviacao.model.ClassificacaoPassageiro;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class PassageiroCompletoResponse {

    private Long cpf;

    private String nome;

    private LocalDate dataNascimento;

    private ClassificacaoPassageiro classificacao;

    private Integer milhas;

    private Long eticket;

    private String assento;

    private LocalDateTime dataHoraConfirmacao;

}
