package com.phase2.homeService.service.interfaces;

import com.phase2.homeService.entities.Professional;

public interface ProfessionalService {

    Professional save(Professional professional);

    Professional findByEmail(String email);
}
