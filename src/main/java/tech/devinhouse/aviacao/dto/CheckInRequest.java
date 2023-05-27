package tech.devinhouse.aviacao.dto;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class CheckInRequest {

    private Long cpf;

    private String assento;

    private Boolean malasDespachadas;

}
