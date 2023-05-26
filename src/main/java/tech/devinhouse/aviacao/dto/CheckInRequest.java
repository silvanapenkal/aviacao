package tech.devinhouse.aviacao.dto;

import lombok.Data;


@Data
public class CheckInRequest {

    private Long cpf;

    private String assento;

    private Boolean malasDespachadas;

}
