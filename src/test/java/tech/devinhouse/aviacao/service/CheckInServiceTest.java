package tech.devinhouse.aviacao.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import tech.devinhouse.aviacao.exception.MalaNaoDespachadaException;
import tech.devinhouse.aviacao.exception.PassageiroMenorDeIdadeException;
import tech.devinhouse.aviacao.exception.RegistroEncontradoException;
import tech.devinhouse.aviacao.exception.RegistroNaoEncontradoException;
import tech.devinhouse.aviacao.model.CheckIn;
import tech.devinhouse.aviacao.model.Passageiro;
import tech.devinhouse.aviacao.repository.CheckInRepository;
import tech.devinhouse.aviacao.repository.PassageiroRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class CheckInServiceTest {

    @Mock
    private CheckInRepository checkInRepository;

    @InjectMocks
    private CheckInService checkInService;

    @Mock
    private PassageiroService passageiroService;

    @Test
    @DisplayName("Retorna null quando o cpf não é localizado ")
    void consultarPorCpfInxistente() {
        Long cpf = 22222222222L;
        Mockito.when(checkInRepository.findByPassageiro_Cpf(Mockito.anyLong())).thenReturn(Optional.empty());
        assertNull(checkInService.consultarPorCpf(cpf));
    }

    @Test
    @DisplayName("Retorna os dados de checkin de um CPF específico ")
    void consultarPorCpf() {
        Long cpf = 22222222222L;
        Passageiro passageiro = Passageiro.builder().cpf(cpf).build();
        CheckIn checkIn = new CheckIn(null, "4B",null,true,passageiro);
        Mockito.when(checkInRepository.findByPassageiro_Cpf(cpf)).thenReturn(Optional.of(checkIn));
        CheckIn checkInRealizado = checkInService.consultarPorCpf(cpf);
        assertEquals(22222222222L,checkInRealizado.getPassageiro().getCpf());
    }

    @Test
    @DisplayName("Retorna a lista dos assentos da aeronave ")
    void consultarAssentos() {
        List<String> assentos = checkInService.consultarAssentos();
        assertEquals(54,assentos.size());
    }


    @Test
    @DisplayName("Retorna um erro quando o checkin do passageiro já foi realizado ")
    void criar_CheckInRepetido() {
        Passageiro passageiro = Passageiro.builder().cpf(22222222222L).build();
        CheckIn checkIn = new CheckIn(null, "4B",null,true,passageiro);
        Mockito.when(checkInRepository.existsByPassageiro_Cpf(Mockito.anyLong())).thenReturn(true);
        assertThrows(RegistroEncontradoException.class, () -> checkInService.criar(checkIn));
    }

    @Test
    @DisplayName("Retorna um erro quando o assento está ocupado ")
    void criar_AssentoOcupado() {
        Passageiro passageiro = Passageiro.builder().cpf(22222222222L).build();
        CheckIn checkIn = new CheckIn(null, "4B",null,true,passageiro);
        Mockito.when(checkInRepository.existsByPassageiro_Cpf(Mockito.anyLong())).thenReturn(false);
        Mockito.when(checkInRepository.existsByAssento(Mockito.anyString())).thenReturn(true);
        assertThrows(RegistroEncontradoException.class, () -> checkInService.criar(checkIn));
    }

    @Test
    @DisplayName("Retorna um erro quando o passageiro não existe ")
    void criar_PassageiroInexistente() {
        Passageiro passageiro = Passageiro.builder().cpf(2222222222L).build();
        CheckIn checkIn = new CheckIn(null, "4B",null,true,passageiro);
        Mockito.when(checkInRepository.existsByPassageiro_Cpf(Mockito.anyLong())).thenReturn(false);
        Mockito.when(checkInRepository.existsByAssento(Mockito.anyString())).thenReturn(false);
        Mockito.when(passageiroService.existPassageiroByCpf(Mockito.any())).thenReturn(false);
        assertThrows(RegistroNaoEncontradoException.class, () -> checkInService.criar(checkIn));
    }

    @Test
    @DisplayName("Retorna um erro quando o assento não existe ")
    void criar_AssentoInexistente() {
        Passageiro passageiro = Passageiro.builder().cpf(22222222222L).build();
        CheckIn checkIn = new CheckIn(null, "4Z",null,true,passageiro);
        Mockito.when(checkInRepository.existsByPassageiro_Cpf(Mockito.anyLong())).thenReturn(false);
        Mockito.when(checkInRepository.existsByAssento(Mockito.anyString())).thenReturn(false);
        Mockito.when(passageiroService.existPassageiroByCpf(Mockito.any())).thenReturn(true);
        assertThrows(RegistroNaoEncontradoException.class, () -> checkInService.criar(checkIn));
    }

    @Test
    @DisplayName("Retorna um erro quando o passageiro faz checkin na fila da saída de emergência e a mala não foi despachado  ")
    void criar_MalaNaoDespachada() {
        Passageiro passageiro = Passageiro.builder().cpf(22222222222L).build();
        CheckIn checkIn = new CheckIn(null, "4D",null,false,passageiro);
        Mockito.when(checkInRepository.existsByPassageiro_Cpf(Mockito.anyLong())).thenReturn(false);
        Mockito.when(checkInRepository.existsByAssento(Mockito.anyString())).thenReturn(false);
        Mockito.when(passageiroService.existPassageiroByCpf(Mockito.any())).thenReturn(true);
        assertThrows(MalaNaoDespachadaException.class, () -> checkInService.criar(checkIn));
    }

    @Test
    @DisplayName("Retorna um erro quando o passageiro menor de idade faz checkin na fila da saída de emergência ")
    void criar_PassageiroMenorDeIdade() {
        Passageiro passageiro = Passageiro.builder().cpf(22222222222L).build();
        CheckIn checkIn = new CheckIn(null, "4D",null,true,passageiro);
        Mockito.when(checkInRepository.existsByPassageiro_Cpf(Mockito.anyLong())).thenReturn(false);
        Mockito.when(checkInRepository.existsByAssento(Mockito.anyString())).thenReturn(false);
        Mockito.when(passageiroService.existPassageiroByCpf(Mockito.any())).thenReturn(true);
        Mockito.when(passageiroService.verificarMaioridade(Mockito.anyLong())).thenReturn(false);
        assertThrows(PassageiroMenorDeIdadeException.class, () -> checkInService.criar(checkIn));
    }

    @Test
    @DisplayName("Realiza o checkin quando os dados de checkin forem válidos ")
    void criar() {
        Passageiro passageiro = Passageiro.builder().cpf(2222222222L).build();
        CheckIn checkIn = new CheckIn(null, "4D",null,true,passageiro);
        Mockito.when(checkInRepository.existsByPassageiro_Cpf(Mockito.anyLong())).thenReturn(false);
        Mockito.when(checkInRepository.existsByAssento(Mockito.anyString())).thenReturn(false);
        Mockito.when(passageiroService.existPassageiroByCpf(Mockito.any())).thenReturn(true);
        Mockito.when(passageiroService.verificarMaioridade(Mockito.anyLong())).thenReturn(true);
        checkInService.criar(checkIn);
        assertNotNull(checkIn.getEticket());
        assertNotNull(checkIn.getDataHoraConfirmacao());
    }
}