package com.phase2.homeService.repository;

import com.phase2.homeService.entities.Professional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProfessionalRepository extends JpaRepository<Professional, Integer> {

    Professional findByEmail(String email);

    @Query("SELECT p FROM Professional p WHERE p.status = 'WAITING_FOR_CONFIRMATION'")
    List<Professional> waitingForConfirmationProfessionals();

    @Modifying
    @Query("UPDATE Professional p SET p.status = 'CONFIRMED' WHERE p.id = :id")
    void updateProfessionalStatus(@Param(value = "id") Integer id);



}


