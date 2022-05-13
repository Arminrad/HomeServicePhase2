package com.phase2.homeService.controller;

import com.phase2.homeService.dto.ProfessionalDto;
import com.phase2.homeService.entities.Professional;
import com.phase2.homeService.entities.Services;
import com.phase2.homeService.entities.enumeration.UserType;
import com.phase2.homeService.service.implementations.ProfessionalServiceImple;
import com.phase2.homeService.service.implementations.ServicesServiceImple;
import com.phase2.homeService.util.Utility;
import org.dozer.DozerBeanMapper;
import org.modelmapper.ModelMapper;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
    public void save(@ModelAttribute ProfessionalDto professionalDto) throws IOException {
        Set<Services> servicesSet = new HashSet<>();
        for (Integer sId : professionalDto.getServices_id()) {
            servicesSet.add(servicesService.getById(sId));
        }
        Professional professional = createProfessional(professionalDto);//mapper.map(professionalDto, Professional.class);
        professional.setServices(servicesSet);
        Professional savedProfessional = professionalService.save(professional);
/*        ProfessionalDto savedProfessionalDto = modelMapper.map(savedProfessional, ProfessionalDto.class);
        return ResponseEntity.ok(savedProfessionalDto);*/
    }

    private Professional createProfessional(ProfessionalDto professionalDto) throws IOException {
        Professional professional = new Professional(
                 "ali",  "reza",  "as@mail.com",
                 "password",  null,  100.0,
                 UserType.Professional,  "city",
        professionalDto.getImage().getBytes(),  "nationalCode",  null);
        return professional;
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

    @PutMapping("/updatePassword")
    public ResponseEntity<ProfessionalDto> changePassword(@RequestBody ProfessionalDto professionalDto){
        Professional professional = mapper.map(professionalDto, Professional.class);
        Professional savedProfessional = professionalService.changePassword(professional);
        ProfessionalDto savedProfessionalDto = modelMapper.map(savedProfessional, ProfessionalDto.class);
        if (savedProfessionalDto != null){
            return ResponseEntity.ok(savedProfessionalDto);
        }else return ResponseEntity.notFound().build();
    }

}
