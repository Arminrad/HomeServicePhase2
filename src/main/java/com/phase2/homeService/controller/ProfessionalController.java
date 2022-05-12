package com.phase2.homeService.controller;

import com.phase2.homeService.dto.ProfessionalDto;
import com.phase2.homeService.entities.Professional;
import com.phase2.homeService.entities.Services;
import com.phase2.homeService.service.implementations.ProfessionalServiceImple;
import com.phase2.homeService.service.implementations.ServicesServiceImple;
import com.phase2.homeService.util.Utility;
import org.dozer.DozerBeanMapper;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/professional")
public class ProfessionalController {
    private final ProfessionalServiceImple professionalService;
    private final DozerBeanMapper mapper;
    private final ModelMapper modelMapper;
    private final ServicesServiceImple servicesService;

    public ProfessionalController(ProfessionalServiceImple professionalService,
                                  ServicesServiceImple servicesService) {
        this.professionalService = professionalService;
        this.servicesService = servicesService;
        this.mapper = new DozerBeanMapper();
        this.modelMapper = new ModelMapper();
    }

    @PostMapping("/save")
    public ResponseEntity<ProfessionalDto> save(@RequestBody ProfessionalDto professionalDto) {
        Set<Services> servicesSet = new HashSet<>();
        for (Integer sId : professionalDto.getServices_id()) {
            servicesSet.add(servicesService.getById(sId));
        }
        Professional professional = mapper.map(professionalDto, Professional.class);
        professional.setServices(servicesSet);
        Professional savedProfessional = professionalService.save(professional);
        ProfessionalDto savedProfessionalDto = modelMapper.map(savedProfessional, ProfessionalDto.class);
        return ResponseEntity.ok(savedProfessionalDto);
    }

    @GetMapping("/getProfessionalsWithNewStatus")
    public ResponseEntity<List<ProfessionalDto>> getProfessionalsWithNewStatus() {
        List<Professional> professionals = professionalService.waitingForConfirmationProfessionals();
        List<ProfessionalDto> professionalDtoList = new ArrayList<>();
        for (Professional p: professionals) {
            ProfessionalDto savedProfessionalDto = modelMapper.map(p, ProfessionalDto.class);
            professionalDtoList.add(savedProfessionalDto);
        }
        return ResponseEntity.ok(professionalDtoList);
    }

    @PostMapping("/professionalConfirmation")
    public void professionalConfirmation(@RequestBody ProfessionalDto professionalDto){
        professionalService.updateProfessionalStatus(professionalDto.getId());
    }
}
