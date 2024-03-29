package com.phase2.homeService.service.implementations;

import com.phase2.homeService.entities.Services;
import com.phase2.homeService.entities.enumeration.Role;
import com.phase2.homeService.repository.ServicesRepository;
import com.phase2.homeService.service.interfaces.ServicesService;
import org.dozer.DozerBeanMapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;


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

    @Override
    public Services getServicesByServiceName(String serviceName) {
        return serviceRepository.getServicesByServiceName(serviceName);
    }



}
