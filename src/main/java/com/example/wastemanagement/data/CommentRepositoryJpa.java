package com.example.wastemanagement.data;

import com.example.wastemanagement.domain.Blog;
import com.example.wastemanagement.domain.Comment;
import com.example.wastemanagement.domain.Hub;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepositoryJpa extends JpaRepository<Comment, Integer> {
    @Query("SELECT c FROM Comment c WHERE c.blogId = ?1")
    List<Comment> findAllByBlogId(int blogId);
}
