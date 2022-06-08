package com.phase2.homeService.service.implementations;

import com.phase2.homeService.dto.DynamicSearchDto;
import com.phase2.homeService.entities.Professional;
import com.phase2.homeService.entities.Services;
import com.phase2.homeService.entities.enumeration.Role;
import com.phase2.homeService.repository.ProfessionalRepository;
import com.phase2.homeService.service.interfaces.ProfessionalService;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class ProfessionalServiceImple implements ProfessionalService {

    private final ProfessionalRepository professionalRepository;
    private final ServicesServiceImple servicesService;

    public ProfessionalServiceImple(ProfessionalRepository professionalRepository, ServicesServiceImple servicesService) {
        this.professionalRepository = professionalRepository;
        this.servicesService = servicesService;
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

    @Override
    public Professional changePassword(Professional professional) {
        Professional savedProfessional = professionalRepository.getById(professional.getId());
        savedProfessional.setPassword(professional.getPassword());
        return professionalRepository.save(savedProfessional);
    }

    public List<Professional> filterProfessional(DynamicSearchDto dynamicSearch){
        Professional professional = new Professional(dynamicSearch.getFirstName(),
                dynamicSearch.getLastName(),
                dynamicSearch.getEmail(),null,null, null,
                Role.PROFESSIONAL,null,null, null, null);

        List<Professional> professionals = professionalRepository.findAll(userSpecification(professional));
        Services services;
        if(dynamicSearch.getService() == null || dynamicSearch.getService().isEmpty()) {
            return professionals;
        }
        else{
            services = servicesService.getServicesByServiceName(dynamicSearch.getService());
            if(services == null) {
                professionals.clear();
                return professionals;
            }
            else
                return services.getProfessionals().stream().filter(professionals::contains).collect(Collectors.toList());
        }
    }

    private Specification<Professional> userSpecification(Professional professional){
        return (userRoot, query, criteriaBuilder)
                -> {
            CriteriaQuery<Professional> criteriaQuery = criteriaBuilder.createQuery(Professional.class);
            criteriaQuery.select(userRoot);

            List<Predicate> predicates = new ArrayList<>();
/*            if(professional.getRole() != null )
                predicates.add(criteriaBuilder.equal(userRoot.get("role"),professional.getRole()));*/
            if(professional.getFirstName() != null && !professional.getFirstName().isEmpty())
                predicates.add(criteriaBuilder.equal(userRoot.get("firstName"),professional.getFirstName()));
            if(professional.getLastName() != null && !professional.getLastName().isEmpty())
                predicates.add(criteriaBuilder.equal(userRoot.get("lastName"),professional.getLastName()));
            if(professional.getEmail() != null && !professional.getEmail().isEmpty())
                predicates.add(criteriaBuilder.equal(userRoot.get("email"),professional.getEmail()));

            criteriaQuery.where(predicates.toArray(new Predicate[0]));
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
