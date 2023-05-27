package tech.devinhouse.aviacao.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import tech.devinhouse.aviacao.repository.CheckInRepository;
import tech.devinhouse.aviacao.repository.PassageiroRepository;
import tech.devinhouse.aviacao.service.CheckInService;
import tech.devinhouse.aviacao.service.PassageiroService;


import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest
public class AssentoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CheckInService checkInService;

    @MockBean
    private PassageiroService passageiroService;

    @MockBean
    private PassageiroRepository passageiroRepository;

    @MockBean
    private CheckInRepository checkInRepository;

    @Test
    @DisplayName("Deve retornar lista de assentos da aeronave")
    void ListarAssentos() throws Exception{
        List<String> assentos = List.of("2A", "2B");
        Mockito.when(checkInService.consultarAssentos()).thenReturn(assentos);
        mockMvc.perform(get("/api/assentos")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",hasSize(2)));
    }

}
