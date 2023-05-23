package tech.devinhouse.aviacao.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tech.devinhouse.aviacao.exception.RegistroNaoEncontradoException;
import tech.devinhouse.aviacao.model.Passageiro;
import tech.devinhouse.aviacao.repository.PassageiroRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PassageiroService {

    private PassageiroRepository repository;
    public List<Passageiro> consultar() {
        List<Passageiro> passageiros = repository.findAll();
        return  passageiros;
    }

    public Passageiro consultarPorCpf(Long cpf) {
        Optional<Passageiro> passageiroOptional = repository.findById(cpf);
        if(passageiroOptional.isEmpty())
            throw new RegistroNaoEncontradoException("Passageiro",cpf);
        Passageiro passageiro = passageiroOptional.get();
        return passageiro;

    }
}
