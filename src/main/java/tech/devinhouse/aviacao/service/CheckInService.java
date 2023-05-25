package tech.devinhouse.aviacao.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tech.devinhouse.aviacao.model.CheckIn;
import tech.devinhouse.aviacao.repository.CheckInRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class CheckInService {

    private CheckInRepository repository;
    public List<CheckIn> consultar() {
        List<CheckIn> checkIns = repository.findAll();
        return  checkIns;
    }
}
