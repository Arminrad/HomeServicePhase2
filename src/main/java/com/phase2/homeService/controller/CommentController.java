package com.phase2.homeService.controller;

import com.phase2.homeService.entities.Comment;
import com.phase2.homeService.service.implementations.CommentServiceImple;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comment")
public class CommentController {
    private CommentServiceImple commentService;

    public CommentController(CommentServiceImple commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/save")
    public ResponseEntity<Comment> save(@RequestBody Comment comment) {
        return ResponseEntity.ok(commentService.save(comment));
    }
}
