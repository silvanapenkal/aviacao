package tech.devinhouse.aviacao.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import tech.devinhouse.aviacao.model.ClassificacaoPassageiro;
import tech.devinhouse.aviacao.model.Passageiro;
import tech.devinhouse.aviacao.repository.PassageiroRepository;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PassageiroServiceTest {

    @Mock
    private PassageiroRepository passageiroRepository;

    @InjectMocks
    private PassageiroService passageiroService;

    @Test
    void existPassageiroByCpf() {
    }

    @Test
    @DisplayName("Deve retornar a lista de passageiros")
    void consultar() {
        var passageiros = List.of(
                new Passageiro(12345678910L, "Silvana",  LocalDate.of(1977, Month.JANUARY, 14), ClassificacaoPassageiro.VIP,100),
                new Passageiro(12345678911L, "Ivan", LocalDate.of(1977, Month.SEPTEMBER, 27), ClassificacaoPassageiro.VIP,100)
        );
        Mockito.when(passageiroRepository.findAll()).thenReturn(passageiros);
        List<Passageiro> resultado = passageiroService.consultar();
        assertNotNull(resultado);
        assertFalse(resultado.isEmpty());
        assertEquals(2, resultado.size());
        assertEquals(12345678910L,resultado.get(0).getCpf());
        assertEquals("Ivan",resultado.get(1).getNome());
    }

    @Test
    void consultarPorCpf() {
    }

    @Test
    @DisplayName("Deve acrescentar 100 milhas ao passageiro se sua classificação for VIP")
    void acrescentarPontosVip() {
        Passageiro passageiroVip = new Passageiro(12345678910L, "Silvana",  LocalDate.of(1977, Month.JANUARY, 14), ClassificacaoPassageiro.VIP,100);
        Mockito.when(passageiroRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(passageiroVip));
        passageiroService.acrescentarPontos(passageiroVip.getCpf());
        assertEquals(200,passageiroVip.getMilhas());
    }

    @Test
    @DisplayName("Deve acrescentar 80 milhas ao passageiro se sua classificação for OURO")
    void acrescentarPontosOuro() {
        Passageiro passageiroVip = new Passageiro(12345678910L, "Silvana",  LocalDate.of(1977, Month.JANUARY, 14), ClassificacaoPassageiro.OURO,100);
        Mockito.when(passageiroRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(passageiroVip));
        passageiroService.acrescentarPontos(passageiroVip.getCpf());
        assertEquals(180,passageiroVip.getMilhas());
    }

    @Test
    @DisplayName("Deve acrescentar 50 milhas ao passageiro se sua classificação for PRATA")
    void acrescentarPontosPrata() {
        Passageiro passageiroVip = new Passageiro(12345678910L, "Silvana",  LocalDate.of(1977, Month.JANUARY, 14), ClassificacaoPassageiro.PRATA,100);
        Mockito.when(passageiroRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(passageiroVip));
        passageiroService.acrescentarPontos(passageiroVip.getCpf());
        assertEquals(150,passageiroVip.getMilhas());
    }

    @Test
    @DisplayName("Deve acrescentar 30 milhas ao passageiro se sua classificação for BRONZE")
    void acrescentarPontosBronze() {
        Passageiro passageiroVip = new Passageiro(12345678910L, "Silvana",  LocalDate.of(1977, Month.JANUARY, 14), ClassificacaoPassageiro.BRONZE,100);
        Mockito.when(passageiroRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(passageiroVip));
        passageiroService.acrescentarPontos(passageiroVip.getCpf());
        assertEquals(130,passageiroVip.getMilhas());
    }

    @Test
    @DisplayName("Deve acrescentar 10 milhas ao passageiro se sua classificação for ASSOCIADO")
    void acrescentarPontosAssociado() {
        Passageiro passageiroVip = new Passageiro(12345678910L, "Silvana",  LocalDate.of(1977, Month.JANUARY, 14), ClassificacaoPassageiro.ASSOCIADO,100);
        Mockito.when(passageiroRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(passageiroVip));
        passageiroService.acrescentarPontos(passageiroVip.getCpf());
        assertEquals(110,passageiroVip.getMilhas());
    }
    @Test
    void verificarMaioridade() {
    }
}