package tech.devinhouse.aviacao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tech.devinhouse.aviacao.repository.PassageiroRepository;

@SpringBootApplication
public class AviacaoApplication {

    public static void main(String[] args) {
        SpringApplication.run(AviacaoApplication.class, args);
    }

    @Autowired
    private PassageiroRepository passageiroRepository;

}
