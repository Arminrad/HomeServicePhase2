package com.phase2.homeService.repository;

import com.phase2.homeService.entities.Professional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessionalRepository extends JpaRepository<Professional, Integer> {

    Professional findByEmail(String email);
}
