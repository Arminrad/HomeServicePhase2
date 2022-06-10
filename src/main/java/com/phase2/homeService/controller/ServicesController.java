package com.phase2.homeService.controller;

import com.phase2.homeService.dto.ServicesDto;
import com.phase2.homeService.entities.Services;
import com.phase2.homeService.service.implementations.ServicesServiceImple;
import org.dozer.DozerBeanMapper;
import org.modelmapper.ModelMapper;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

/*    @PostMapping("/save")
    public ResponseEntity<ServicesDto> save(@RequestBody ServicesDto servicesDto) {
        Services service = mapper.map(servicesDto, Services.class);
        Services savedService = serviceService.save(service);
        ServicesDto savedServiceDto = modelMapper.map(savedService, ServicesDto.class);
        return ResponseEntity.ok(savedServiceDto);
    }*/

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(value = "/save", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public String save(@ModelAttribute @RequestBody ServicesDto servicesDto, Model model) {
        Services parent = serviceService.getById(servicesDto.getParent_id());
        Services services = mapper.map(servicesDto, Services.class);
        services.setParent(parent);
        Services savedService = serviceService.save(services);
        model.addAttribute("name", savedService.getServiceName());
        return savedService.getServiceName();
    }
}
