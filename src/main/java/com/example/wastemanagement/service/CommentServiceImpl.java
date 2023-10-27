package com.example.wastemanagement.service;

import com.example.wastemanagement.data.CommentRepositoryJpa;
import com.example.wastemanagement.domain.Blog;
import com.example.wastemanagement.domain.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentRepositoryJpa commentRepository;

    @Override
    public List<Comment> getCommentsByBlogId(int blogId) {
        return commentRepository.findAllByBlogId(blogId);
    }

    @Override
    public void addComment(Comment comment, int blogId) {
        Comment comment1 = new Comment(null, blogId, comment.getUsername(), comment.getCommentText());
        commentRepository.save(comment1);
    }

    @Override
    public void deleteComment(int id) {
        commentRepository.deleteById(id);
    }
}
