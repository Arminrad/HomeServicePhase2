package com.phase2.homeService.service.interfaces;

import com.phase2.homeService.entities.Professional;

import java.util.List;

public interface ProfessionalService {

    Professional save(Professional professional);

    Professional findByEmail(String email);

    List<Professional> waitingForConfirmationProfessionals();

    Professional getById(Integer id);
}
