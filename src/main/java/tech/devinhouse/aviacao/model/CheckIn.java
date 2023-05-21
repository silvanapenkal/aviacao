package tech.devinhouse.aviacao.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "CHECKINS")
@NoArgsConstructor
@AllArgsConstructor
public class CheckIn {

    @Id
    private String eticket;

    private String assento;

    private LocalDateTime dataHoraConfirmacao;

    private Boolean malasDespachadas;

}
