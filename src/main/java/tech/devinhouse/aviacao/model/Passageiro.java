package tech.devinhouse.aviacao.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Passageiro {

    @Id
    private Long cpf;

    private String nome;

    private LocalDate dataNascimento;

    @Enumerated(EnumType.STRING)
    private ClassificacaoPassageiro classificacao;

    private Integer milhas;

}
