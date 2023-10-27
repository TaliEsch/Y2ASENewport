package com.example.wastemanagement.service;

import com.example.wastemanagement.domain.Blog;
import com.example.wastemanagement.service.Dto.BlogDto;
import org.springframework.stereotype.Service;
import com.example.wastemanagement.web.Form.BlogForm;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public interface BlogService {
    List<BlogDto> getAllBlogs() throws IOException;

    void addBlog(BlogForm blogFormToAdd) throws IOException;
    // Exception neccessary due to the conversions (MultipartFile -> byte array)

    BlogDto getBlogByID(int id);

    void deleteBlog(int id);
}
