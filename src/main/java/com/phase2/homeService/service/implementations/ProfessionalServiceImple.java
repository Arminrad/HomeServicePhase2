package com.phase2.homeService.service.implementations;

import com.phase2.homeService.entities.Professional;
import com.phase2.homeService.repository.ProfessionalRepository;
import com.phase2.homeService.service.interfaces.ProfessionalService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


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

    @Override
    public List<Professional> waitingForConfirmationProfessionals() {
        return professionalRepository.waitingForConfirmationProfessionals();
    }

    @Override
    public Professional getById(Integer id) {
        return professionalRepository.getById(id);
    }

    @Override
    @Transactional
    public void updateProfessionalStatus(Integer id) {
        professionalRepository.updateProfessionalStatus(id);
    }
}
