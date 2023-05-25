package tech.devinhouse.aviacao.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CheckInResponse {

    private String eticket;

    private LocalDateTime dataHoraConfirmacao;

}
