package tech.devinhouse.aviacao.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/assentos")
public class AssentoController {

    @GetMapping
    public List<String> ListarAssentos (){
        List<String> assentos = new ArrayList<>();
        int totalNumeros = 9;
        for (int i = 1; i <= totalNumeros; i++) {
            for (char j = 'A'; j <= 'F'; j++) {
                String assento = i + String.valueOf(j);
                assentos.add(assento);
            }
        }
        return assentos;
    }

}

