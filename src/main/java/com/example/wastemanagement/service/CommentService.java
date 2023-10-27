package com.example.wastemanagement.service;

import com.example.wastemanagement.domain.Blog;
import com.example.wastemanagement.domain.Comment;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface CommentService {
    List<Comment> getCommentsByBlogId(int blogId);
    void addComment(Comment comment, int blogId);

    void deleteComment(int id);
}
