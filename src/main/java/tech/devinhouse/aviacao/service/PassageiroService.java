package tech.devinhouse.aviacao.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tech.devinhouse.aviacao.model.Passageiro;
import tech.devinhouse.aviacao.repository.PassageiroRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class PassageiroService {

    private PassageiroRepository repository;
    public List<Passageiro> consultar() {
        List<Passageiro> passageiros = repository.findAll();
        return  passageiros;
    }
}
