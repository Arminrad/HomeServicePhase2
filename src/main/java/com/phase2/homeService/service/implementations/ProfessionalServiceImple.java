package com.phase2.homeService.service.implementations;

import com.phase2.homeService.entities.Professional;
import com.phase2.homeService.repository.ProfessionalRepository;
import com.phase2.homeService.service.interfaces.ProfessionalService;
import org.springframework.stereotype.Service;


@Service
public class ProfessionalServiceImple implements ProfessionalService {

    private final ProfessionalRepository professionalRepository;

    public ProfessionalServiceImple(ProfessionalRepository professionalRepository) {
        this.professionalRepository = professionalRepository;
    }

    @Override
    public Professional save(Professional professional) {
        return professionalRepository.save(professional );
    }

    @Override
    public Professional findByEmail(String email) {
        return professionalRepository.findByEmail(email);
    }
}
