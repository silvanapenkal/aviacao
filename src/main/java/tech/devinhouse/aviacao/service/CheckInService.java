package tech.devinhouse.aviacao.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tech.devinhouse.aviacao.exception.MalaNaoDespachadaException;
import tech.devinhouse.aviacao.exception.PassageiroMenorDeIdadeException;
import tech.devinhouse.aviacao.exception.RegistroEncontradoException;
import tech.devinhouse.aviacao.exception.RegistroNaoEncontradoException;
import tech.devinhouse.aviacao.model.CheckIn;
import tech.devinhouse.aviacao.repository.CheckInRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
@Slf4j
public class CheckInService {

    private CheckInRepository checkInRepository;

    private  PassageiroService passageiroService;

    public CheckIn consultarPorCpf(Long cpf) {
        Optional<CheckIn> checkInOptional = checkInRepository.findByPassageiro_Cpf(cpf);
        if(checkInOptional.isEmpty())
            return null;
        CheckIn checkIn = checkInOptional.get();
        return checkIn;
    }

    public List<String> consultarAssentos(){
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

    public CheckIn criar(CheckIn checkIn) {
        Long cpf = checkIn.getPassageiro().getCpf();
        String assento = checkIn.getAssento();
        Boolean isAssento = consultarAssentos().contains(assento);
        if (checkInRepository.existsByPassageiro_Cpf(cpf)) {
            throw new RegistroEncontradoException(cpf);
        }
        if (checkInRepository.existsByAssento(assento)) {
            throw new RegistroEncontradoException(assento);
        }
        if (!passageiroService.existPassageiroByCpf(cpf)) {
            throw new RegistroNaoEncontradoException("Passageiro",cpf);
        }
        if (!isAssento) {
            throw new RegistroNaoEncontradoException("Assento",assento);
        }
        if ((assento.contains("4")||assento.contains("5")) && checkIn.getMalasDespachadas()==false) {
            throw new MalaNaoDespachadaException(assento);
        }
        if ((assento.contains("4")||assento.contains("5")) && !passageiroService.verificarMaioridade(cpf)) {
            throw new PassageiroMenorDeIdadeException(cpf);
        }
        String eticket = UUID.randomUUID().toString();
        checkIn.setEticket(eticket);
        checkIn.setDataHoraConfirmacao(LocalDateTime.now());
        passageiroService.acrescentarPontos(cpf);
        checkInRepository.save(checkIn);
        log.info("Confirmação feita pelo passageiro de CPF {} com e-ticket {}", cpf, eticket);
        return checkIn;
    }
}
