package com.example.wastemanagement.data;

import com.example.wastemanagement.domain.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogRepositoryJpa extends JpaRepository<Blog, Integer> {
    List<Blog> findAll();
}
