package com.phase2.homeService.controller;

import com.phase2.homeService.entities.Services;
import com.phase2.homeService.service.implementations.ServicesServiceImple;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Component
@RestController
@RequestMapping("service")
public class ServicesController {
    private ServicesServiceImple serviceService;

    public ServicesController(ServicesServiceImple serviceService) {
        this.serviceService = serviceService;
    }

    @PostMapping("/save")
    public ResponseEntity<Services> save(@RequestBody Services service) {
        if (service.getId() != 0) {
            Services parentService = serviceService.getById(service.getId());
            service.setParent(parentService);
        }
        return ResponseEntity.ok(serviceService.save(service));
    }
}
