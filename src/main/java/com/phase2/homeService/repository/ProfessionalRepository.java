package com.phase2.homeService.repository;

import com.phase2.homeService.entities.Professional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProfessionalRepository extends JpaRepository<Professional, Integer> {

    Professional findByEmail(String email);

    @Query("SELECT p FROM Professional p WHERE p.status = 'WAITING_FOR_CONFIRMATION'")
    List<Professional> waitingForConfirmationProfessionals();
}
