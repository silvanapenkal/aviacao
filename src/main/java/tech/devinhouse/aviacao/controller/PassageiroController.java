package tech.devinhouse.aviacao.controller;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.devinhouse.aviacao.dto.PassageiroCompletoResponse;
import tech.devinhouse.aviacao.dto.PassageiroResponse;
import tech.devinhouse.aviacao.model.Passageiro;
import tech.devinhouse.aviacao.service.PassageiroService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/passageiros")
@AllArgsConstructor
public class PassageiroController {

    private PassageiroService passageiroService;

    private ModelMapper mapper;

    @GetMapping
    public ResponseEntity<List<PassageiroCompletoResponse>> consultar(){
        List<Passageiro> listaPassageiros = passageiroService.consultar();
        List<PassageiroCompletoResponse> resp = listaPassageiros.stream().map(s -> mapper.map(s, PassageiroCompletoResponse.class)).collect(Collectors.toList());
        return ResponseEntity.ok(resp);
    }

    @GetMapping("{cpf}")
    public ResponseEntity<PassageiroResponse> consultarPorCpf(@PathVariable("cpf") Long cpf){
        Passageiro passageiro = passageiroService.consultarPorCpf(cpf);
        PassageiroResponse passageiroResponse = mapper.map(passageiro, PassageiroResponse.class);
        return ResponseEntity.ok(passageiroResponse);
    }



}
