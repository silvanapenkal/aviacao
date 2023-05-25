package tech.devinhouse.aviacao.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "CHECKINS")
@AllArgsConstructor
@NoArgsConstructor
public class CheckIn {

    @Id
    private String eticket;

    private String assento;

    private LocalDateTime dataHoraConfirmacao;

    private Boolean malasDespachadas;

    @OneToOne
    @JoinColumn(name= "cpf", referencedColumnName = "cpf")
    private Passageiro passageiro;

}
