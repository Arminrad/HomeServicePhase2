package com.phase2.homeService.controller;

import com.phase2.homeService.dto.ProfessionalDto;
import com.phase2.homeService.dto.ServicesDto;
import com.phase2.homeService.entities.Professional;
import com.phase2.homeService.entities.Services;
import com.phase2.homeService.service.implementations.ServicesServiceImple;
import org.dozer.DozerBeanMapper;
import org.json.JSONObject;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Component
@RestController
@RequestMapping("/service")
public class ServicesController {
    private final ServicesServiceImple serviceService;
    private final DozerBeanMapper mapper;
    private final ModelMapper modelMapper;

    public ServicesController(ServicesServiceImple serviceService) {
        this.serviceService = serviceService;
        this.mapper = new DozerBeanMapper();
        this.modelMapper = new ModelMapper();
    }

    @PostMapping("/save")
    public ResponseEntity<ServicesDto> save(@RequestBody ServicesDto servicesDto) {
        Services service = mapper.map(servicesDto, Services.class);
        Services savedService = serviceService.save(service);
        ServicesDto savedServiceDto = modelMapper.map(savedService, ServicesDto.class);
        return ResponseEntity.ok(savedServiceDto);
    }
}
