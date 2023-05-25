package tech.devinhouse.aviacao.dto;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
public class CheckInRequest {

    private Long cpf;

    private String assento;

    private Boolean malasDespachadas;

}
