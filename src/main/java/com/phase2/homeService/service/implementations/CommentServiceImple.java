package com.phase2.homeService.service.implementations;

import com.phase2.homeService.entities.Comment;
import com.phase2.homeService.repository.CommentRepository;
import com.phase2.homeService.service.interfaces.CommentService;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImple implements CommentService {
    private final CommentRepository commentRepository;

    public CommentServiceImple(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public Comment save(Comment comment) {
        return commentRepository.save(comment);
    }
}
