package tech.devinhouse.aviacao.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import tech.devinhouse.aviacao.dto.CheckInRequest;
import tech.devinhouse.aviacao.model.CheckIn;
import tech.devinhouse.aviacao.repository.CheckInRepository;
import tech.devinhouse.aviacao.repository.PassageiroRepository;
import tech.devinhouse.aviacao.service.CheckInService;
import tech.devinhouse.aviacao.service.PassageiroService;

import java.time.LocalDateTime;

import static org.hamcrest.Matchers.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest
public class CheckInControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper; // classe que serializa objetos para JSON

    @Autowired
    private ModelMapper modelMapper;

    @MockBean  // mock para dependencias da classe de controller
    private CheckInService checkInService;

    @MockBean  // mock para dependencias da classe de controller
    private PassageiroService passageiroService;

    @MockBean
    private PassageiroRepository passageiroRepository;

    @MockBean
    private CheckInRepository checkInRepository;

    @Test
    @DisplayName("Retorna sucesso quando inclusão de novo checkin")
    void inserir() throws Exception {
        CheckInRequest checkInRequest = new CheckInRequest(22222222222L,"2A",true);
        CheckIn checkIn = modelMapper.map(checkInRequest, CheckIn.class);
        String requestJson = objectMapper.writeValueAsString(checkInRequest);
        String eticket = "gatoGarfieldDeSapato";
        LocalDateTime agora = LocalDateTime.now();
        checkIn.setEticket(eticket);
        checkIn.setDataHoraConfirmacao(agora);
        Mockito.when(checkInService.criar(Mockito.any(CheckIn.class))).thenReturn(checkIn);
        mockMvc.perform(post("/api/passageiros/confirmacao")
                        .content(requestJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.eticket",is(eticket)))
                .andExpect(jsonPath("$.dataHoraConfirmacao",is(not(empty())))); // 200;
    }


}
