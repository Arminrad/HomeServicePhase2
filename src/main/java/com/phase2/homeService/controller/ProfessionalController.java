package com.phase2.homeService.controller;

import com.phase2.homeService.entities.Professional;
import com.phase2.homeService.service.implementations.ProfessionalServiceImple;
import com.phase2.homeService.util.Utility;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
@RequestMapping("/professional")
public class ProfessionalController {
    private ProfessionalServiceImple professionalService;
    private Utility utility;

    public ProfessionalController(ProfessionalServiceImple professionalService, Utility utility) {
        this.professionalService = professionalService;
        this.utility = utility;
    }

    @PostMapping("/save")
    public ResponseEntity<Professional> save(@RequestBody Professional professional) {
        try {
            System.out.println(professional.getLastName());
            System.out.println(professional.getFirstName());
            System.out.println(professional.getEmail());
            System.out.println(professional.getCity());
            try {
                System.out.println(Arrays.toString(professional.getImage()));
            } catch (Exception e) {
                System.out.println("farzad");
            }
/*            byte[] image = utility.setImage(professional.getImage());
            professional.setImage(image);*/
            return ResponseEntity.ok(professionalService.save(professional));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }


}
