package com.phase2.homeService.repository;

import com.phase2.homeService.entities.Services;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ServicesRepository extends JpaRepository<Services, Integer> {

    Services getServicesByServiceName(String name);

}
