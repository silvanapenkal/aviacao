package tech.devinhouse.aviacao.controller;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.devinhouse.aviacao.service.CheckInService;

@RestController
@RequestMapping("api/checkin")
@AllArgsConstructor
public class CheckInController {

    private CheckInService checkInService;

    private ModelMapper mapper;

    
}
