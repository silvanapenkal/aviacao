package tech.devinhouse.aviacao.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "PASSAGEIROS")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Passageiro {

    @Id
    private Long cpf;

    private String nome;

    private LocalDate dataNascimento;

    @Enumerated(EnumType.STRING)
    private ClassificacaoPassageiro classificacao;

    private Integer milhas;



}
