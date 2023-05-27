package tech.devinhouse.aviacao.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import tech.devinhouse.aviacao.exception.RegistroNaoEncontradoException;
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
    @DisplayName("Deve retornar true quando passageiro existe")
    void existPassageiroByCpf() {
        Long cpf = 12345678910L;
        Mockito.when(passageiroRepository.existsById(Mockito.anyLong())).thenReturn(true);
        Boolean existe = passageiroService.existPassageiroByCpf(cpf);
        assertEquals(true, existe);
    }

    @Test
    @DisplayName("Deve retornar false quando passageiro não existe")
    void inexistPassageiroByCpf() {
        Long cpf = 12345678910L;
        Mockito.when(passageiroRepository.existsById(Mockito.anyLong())).thenReturn(false);
        Boolean existe = passageiroService.existPassageiroByCpf(cpf);
        assertEquals(false, existe);
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
    @DisplayName("Quando há um passageiro cadastrado com o cpf, retorna um passageiro")
    void consultarPorCpf() {
        Passageiro passageiro = new Passageiro(12345678910L, "Silvana",  LocalDate.of(1977, Month.JANUARY, 14), ClassificacaoPassageiro.VIP,100);
        Mockito.when(passageiroRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(passageiro));
        Passageiro resultado = passageiroService.consultarPorCpf(passageiro.getCpf());
        assertNotNull(resultado);
        assertEquals(12345678910L,resultado.getCpf());
        assertEquals("Silvana",resultado.getNome());
    }

    @Test
    @DisplayName("Quando não há um passageiro cadastrado com o cpf, retorna um erro")
    void consultarPorCpfInexistente() {
        Long cpf = 12345678910L;
        assertThrows(RegistroNaoEncontradoException.class, () -> passageiroService.consultarPorCpf(cpf));
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
    @DisplayName("Retorna true quando passageiro é maior de idade")
    void verificarMaioridade() {
        Passageiro passageiro = new Passageiro(12345678910L, "Silvana",  LocalDate.of(1977, Month.JANUARY, 14), ClassificacaoPassageiro.ASSOCIADO,100);
        Mockito.when(passageiroRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(passageiro));
        Boolean resultado = passageiroService.verificarMaioridade(passageiro.getCpf());
        assertEquals(true,resultado);
    }

    @Test
    @DisplayName("Retorna false quando passageiro é menor de idade")
    void verificarMenoridade() {
        Passageiro passageiro = new Passageiro(12345678910L, "Silvana",  LocalDate.of(2010, Month.JANUARY, 14), ClassificacaoPassageiro.ASSOCIADO,100);
        Mockito.when(passageiroRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(passageiro));
        Boolean resultado = passageiroService.verificarMaioridade(passageiro.getCpf());
        assertEquals(false,resultado);
    }
}