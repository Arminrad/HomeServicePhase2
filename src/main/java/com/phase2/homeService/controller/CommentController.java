package com.phase2.homeService.controller;

import com.phase2.homeService.dto.CommentDto;
import com.phase2.homeService.entities.Comment;
import com.phase2.homeService.entities.Customer;
import com.phase2.homeService.entities.Professional;
import com.phase2.homeService.service.implementations.CommentServiceImple;
import com.phase2.homeService.service.implementations.CustomerServiceImple;
import com.phase2.homeService.service.implementations.ProfessionalServiceImple;
import org.dozer.DozerBeanMapper;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment")
public class CommentController {
    private final CommentServiceImple commentService;
    private final DozerBeanMapper mapper;
    private final CustomerServiceImple customerService;
    private final ProfessionalServiceImple professionalService;

    public CommentController(CommentServiceImple commentService, CustomerServiceImple customerService, ProfessionalServiceImple professionalService) {
        this.commentService = commentService;
        this.customerService = customerService;
        this.professionalService = professionalService;
        this.mapper = new DozerBeanMapper();
    }

    @PreAuthorize("hasRole('CUSTOMER')")
    @PostMapping("/save")
    public String save(@ModelAttribute @RequestBody CommentDto commentDto) {
        Customer customer = customerService.getById(commentDto.getCustomer_id());
        Professional professional = professionalService.getById(commentDto.getProfessional_id());
        Comment comment = mapper.map(commentDto, Comment.class);
        comment.setCustomer(customer);
        comment.setProfessional(professional);
        Comment savedComment = commentService.save(comment);
        return "comment";
    }
}
