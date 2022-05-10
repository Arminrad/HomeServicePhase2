package com.phase2.homeService.service.implementations;

import com.phase2.homeService.entities.Services;
import com.phase2.homeService.repository.ServicesRepository;
import com.phase2.homeService.service.interfaces.ServicesService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ServicesServiceImple implements ServicesService {
    private final ServicesRepository serviceRepository;

    public ServicesServiceImple(ServicesRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    @Override
    public Services save(Services service) {
        return serviceRepository.save(service);
    }

    @Override
    public Services getById(Integer id) {
        return serviceRepository.getById(id);
    }

    @Override
    public List<Services> findAll() {
        return serviceRepository.findAll();
    }
}
