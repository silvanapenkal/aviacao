package tech.devinhouse.aviacao;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import tech.devinhouse.aviacao.repository.PassageiroRepository;

@SpringBootApplication
public class AviacaoApplication {

    public static void main(String[] args) {
        SpringApplication.run(AviacaoApplication.class, args);
    }

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper;
    }

    @Autowired
    private PassageiroRepository passageiroRepository;



}
