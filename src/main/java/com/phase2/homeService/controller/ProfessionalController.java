package com.phase2.homeService.controller;

import com.phase2.homeService.dto.OfferDto;
import com.phase2.homeService.dto.ProfessionalDto;
import com.phase2.homeService.entities.Offer;
import com.phase2.homeService.entities.Professional;
import com.phase2.homeService.service.implementations.ProfessionalServiceImple;
import com.phase2.homeService.util.Utility;
import org.dozer.DozerBeanMapper;
import org.modelmapper.ModelMapper;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
@RequestMapping("/professional")
public class ProfessionalController {
    private ProfessionalServiceImple professionalService;
    private final DozerBeanMapper mapper;
    private final ModelMapper modelMapper;
    private Utility utility;

    public ProfessionalController(ProfessionalServiceImple professionalService, Utility utility) {
        this.professionalService = professionalService;
        this.mapper = new DozerBeanMapper();
        this.modelMapper = new ModelMapper();
        this.utility = utility;
    }

    @PostMapping("/save")
    public ResponseEntity<ProfessionalDto> save(@RequestBody ProfessionalDto professionalDto) {
        Professional professional = mapper.map(professionalDto, Professional.class);
        Professional savedProfessional = professionalService.save(professional);
        ProfessionalDto savedProfessionalDto = modelMapper.map(savedProfessional, ProfessionalDto.class);
        return ResponseEntity.ok(savedProfessionalDto);
    }
}
