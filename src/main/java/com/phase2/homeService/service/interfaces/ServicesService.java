package com.phase2.homeService.service.interfaces;

import com.phase2.homeService.entities.Services;

import java.security.Provider;
import java.util.List;
import java.util.Optional;

public interface ServicesService {

    Services save(Services service);

    Services getById(Integer id);

    List<Services> findAll();

    Services getServicesByServiceName(String serviceName);
}
