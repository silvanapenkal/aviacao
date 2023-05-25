package tech.devinhouse.aviacao.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tech.devinhouse.aviacao.exception.RegistroNaoEncontradoException;
import tech.devinhouse.aviacao.model.Passageiro;
import tech.devinhouse.aviacao.repository.PassageiroRepository;

import java.time.LocalDate;

import java.time.Period;
import java.util.*;

@Service
@AllArgsConstructor
public class PassageiroService {

    private PassageiroRepository repository;

    public boolean existPassageiroByCpf (Long cpf){
        return repository.existsById(cpf);
    }
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
    public Passageiro acrescentarPontos(Long cpf) {
        Passageiro passageiro = consultarPorCpf(cpf);
        Integer milhas = passageiro.getMilhas();
        Integer milhasAdicionais = 0;
        switch(passageiro.getClassificacao()) {
            case VIP:
                milhasAdicionais = 100;
                break;
            case OURO:
                milhasAdicionais = 80;
                break;
            case PRATA:
                milhasAdicionais = 50;
                break;
            case BRONZE:
                milhasAdicionais = 30;
                break;
            case ASSOCIADO:
                milhasAdicionais = 10;
                break;
        }
        passageiro.setMilhas(milhas+milhasAdicionais);
        return passageiro;
    }

    public Boolean verificarMaioridade (Long cpf){
        Passageiro passageiro = consultarPorCpf(cpf);
        LocalDate hoje = LocalDate.now();
        LocalDate nascimento = passageiro.getDataNascimento();
        Period periodo = Period.between(nascimento, hoje);
        return periodo.getYears()>18;
    }


}
