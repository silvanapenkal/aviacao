package tech.devinhouse.aviacao.controller;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.devinhouse.aviacao.dto.CheckInRequest;
import tech.devinhouse.aviacao.dto.CheckInResponse;
import tech.devinhouse.aviacao.model.CheckIn;
import tech.devinhouse.aviacao.service.CheckInService;

import java.net.URI;

@RestController
@RequestMapping("api/passageiros/confirmacao")
@AllArgsConstructor
public class CheckInController {

    private CheckInService checkInService;

    private ModelMapper mapper;

    @PostMapping
    public ResponseEntity<CheckInResponse> inserir(@RequestBody CheckInRequest checkInRequest) {
        CheckIn checkIn = mapper.map(checkInRequest, CheckIn.class);
        checkIn = checkInService.criar(checkIn);
        CheckInResponse checkInResponse = mapper.map(checkIn,CheckInResponse.class);
        return ResponseEntity.ok(checkInResponse);
    }

}
