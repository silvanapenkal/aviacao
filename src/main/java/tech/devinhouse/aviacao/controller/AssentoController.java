package tech.devinhouse.aviacao.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.devinhouse.aviacao.service.CheckInService;

import java.util.List;

@RestController
@RequestMapping("api/assentos")
@AllArgsConstructor
public class AssentoController {

    private CheckInService checkInService;

    @GetMapping
    public ResponseEntity<List<String>> ListarAssentos (){
        List<String> assentos =  checkInService.consultarAssentos();
        return ResponseEntity.ok(assentos);
    }

}

