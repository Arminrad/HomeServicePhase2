package com.phase2.homeService.controller;

import com.phase2.homeService.dto.CommentDto;
import com.phase2.homeService.dto.CustomerDto;
import com.phase2.homeService.entities.Comment;
import com.phase2.homeService.entities.Customer;
import com.phase2.homeService.service.implementations.CommentServiceImple;
import org.dozer.DozerBeanMapper;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comment")
public class CommentController {
    private final CommentServiceImple commentService;
    private final DozerBeanMapper mapper;
    private final ModelMapper modelMapper;

    public CommentController(CommentServiceImple commentService) {
        this.commentService = commentService;
        this.modelMapper = new ModelMapper();
        this.mapper = new DozerBeanMapper();
    }

    @PostMapping("/save")
    public ResponseEntity<CommentDto> save(@RequestBody CommentDto commentDto) {
        Comment comment = mapper.map(commentDto, Comment.class);
        Comment savedComment = commentService.save(comment);
        CommentDto savedCommentDto = modelMapper.map(savedComment, CommentDto.class);
        return ResponseEntity.ok(savedCommentDto);
    }
}
