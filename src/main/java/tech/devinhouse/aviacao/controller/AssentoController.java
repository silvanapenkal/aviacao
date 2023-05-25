package tech.devinhouse.aviacao.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.devinhouse.aviacao.service.CheckInService;

import java.util.List;

@RestController
@RequestMapping("api/assentos")
public class AssentoController {

    private CheckInService checkInService;

    @GetMapping
    public List<String> ListarAssentos (){
        return checkInService.consultarAssentos();
    }

}

