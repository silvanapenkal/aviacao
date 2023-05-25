package tech.devinhouse.aviacao;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import tech.devinhouse.aviacao.dto.CheckInRequest;
import tech.devinhouse.aviacao.model.CheckIn;
import tech.devinhouse.aviacao.repository.CheckInRepository;
import tech.devinhouse.aviacao.repository.PassageiroRepository;

@SpringBootApplication
public class AviacaoApplication {

    public static void main(String[] args) {
        SpringApplication.run(AviacaoApplication.class, args);
    }

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.typeMap(CheckInRequest.class, CheckIn.class).addMapping(src -> src.getCpf(), (dest, v) -> dest.getPassageiro().setCpf((Long) v));
        return modelMapper;
    }

    @Autowired
    private PassageiroRepository passageiroRepository;

    @Autowired
    private CheckInRepository checkInRepository;



}
