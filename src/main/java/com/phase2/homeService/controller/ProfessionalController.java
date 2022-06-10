package com.phase2.homeService.controller;

import com.phase2.homeService.dto.DynamicSearchDto;
import com.phase2.homeService.dto.ProfessionalDto;
import com.phase2.homeService.entities.Professional;
import com.phase2.homeService.entities.Services;
import com.phase2.homeService.entities.enumeration.Role;
import com.phase2.homeService.service.implementations.ProfessionalServiceImple;
import com.phase2.homeService.service.implementations.ServicesServiceImple;
import org.dozer.DozerBeanMapper;
import org.modelmapper.ModelMapper;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.*;

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

    @PostMapping(path = "/save",consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public String save(@ModelAttribute @RequestBody ProfessionalDto professionalDto) throws IOException {
        Set<Services> servicesSet = new HashSet<>();
        for (Integer sId : professionalDto.getServices_id()) {
            servicesSet.add(servicesService.getById(sId));
        }
        Professional professional = createProfessional(professionalDto);
        professional.setServices(servicesSet);
        Professional savedProfessional = professionalService.save(professional);
        ProfessionalDto savedProfessionalDto = modelMapper.map(savedProfessional, ProfessionalDto.class);
        return "professionalProfile";
    }

    private Professional createProfessional(ProfessionalDto professionalDto) throws IOException {
        Professional professional = new Professional(
                 professionalDto.getFirstName(),  professionalDto.getLastName(),  professionalDto.getEmail(),
                 professionalDto.getPassword(),  null,  100.0,
                 Role.ROLE_PROFESSIONAL,  professionalDto.getCity(),
        professionalDto.getImage().getBytes(),  professionalDto.getNationalCode(),  null);
        return professional;
    }

    @PreAuthorize("hasRole('ADMIN')")
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

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/professionalConfirmation")
    public void professionalConfirmation(@RequestBody ProfessionalDto professionalDto){
        professionalService.updateProfessionalStatus(professionalDto.getId());
    }

    @PreAuthorize("hasRole('PROFESSIONAL')")
    @PutMapping("/updatePassword")
    public ResponseEntity<ProfessionalDto> changePassword(@RequestBody ProfessionalDto professionalDto){
        Professional professional = mapper.map(professionalDto, Professional.class);
        Professional savedProfessional = professionalService.changePassword(professional);
        ProfessionalDto savedProfessionalDto = modelMapper.map(savedProfessional, ProfessionalDto.class);
        if (savedProfessionalDto != null){
            return ResponseEntity.ok(savedProfessionalDto);
        }else return ResponseEntity.notFound().build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(value = "/gridSearch")
    public ResponseEntity<List<ProfessionalDto>> gridSearch(@RequestBody DynamicSearchDto dynamicSearch) {
        List<Professional> professionalList = professionalService.filterProfessional(dynamicSearch);
        List<ProfessionalDto> dtoList = new ArrayList<>();
        for (Professional e:professionalList
        ) {
            ProfessionalDto professionalDto = modelMapper.map(e, ProfessionalDto.class);
            dtoList.add(professionalDto);
        }
        return ResponseEntity.ok(dtoList);
    }

}
