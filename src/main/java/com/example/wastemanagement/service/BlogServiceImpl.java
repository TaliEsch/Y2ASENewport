package com.example.wastemanagement.service;

import com.example.wastemanagement.data.BlogRepositoryJpa;
import com.example.wastemanagement.domain.Blog;
import com.example.wastemanagement.web.Form.BlogForm;
import com.example.wastemanagement.service.Dto.BlogDto;
import com.sun.mail.iap.ByteArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;


@Service
public class BlogServiceImpl implements BlogService{

    private final BlogRepositoryJpa blogRepository;

    @Autowired
    public BlogServiceImpl(BlogRepositoryJpa blogRepository) {
        this.blogRepository = blogRepository;
    }

    @Override
    public List<BlogDto> getAllBlogs() throws IOException {
        var listOfBlogs= blogRepository.findAll();
        List<BlogDto> listOfBlogsDto = new ArrayList<>();

        // Convert byte array to BufferedImage
        for (Blog blog : listOfBlogs) {
            if (blog.getItemImage() != null) {
                String imageString = Base64.getEncoder().encodeToString(blog.getItemImage());
                BlogDto blogDto = new BlogDto(blog.getBlogId(), blog.getBlogTitle(), blog.getUsername(), blog.getItemName(),
                        blog.getItemCategory(), imageString, blog.getBlogContact(), blog.getItemDescription());
                listOfBlogsDto.add(blogDto);
            } else {
                BlogDto blogDto = new BlogDto(blog.getBlogId(), blog.getBlogTitle(), blog.getUsername(), blog.getItemName(),
                        blog.getItemCategory(), null, blog.getBlogContact(), blog.getItemDescription());
                listOfBlogsDto.add(blogDto);
            }

        }
        return listOfBlogsDto;
    }

    public byte[] convertMultipartToByteArray(MultipartFile file) throws IOException {
        return(file.getBytes());
    }

    public void addBlog(BlogForm blogFormToAdd) throws IOException {
        // Email intentionally not stored as this would violate GDPR unless a deletion method was added

        byte[] imageByteArray = convertMultipartToByteArray(blogFormToAdd.getItemImage());

        // if the user does not upload an image, the imageByteArray will be null for the database
        if (blogFormToAdd.getItemImage().isEmpty()){
            imageByteArray = null;
        }

        Blog blogToAdd = new Blog(null,blogFormToAdd.getBlogTitle(), blogFormToAdd.getUsername(),
                blogFormToAdd.getItemName(), blogFormToAdd.getItemCategory(),
                imageByteArray,blogFormToAdd.getBlogContact(), blogFormToAdd.getItemDescription());
        blogRepository.save(blogToAdd);
    }

    public BlogDto getBlogByID(int id) {
        var blog = blogRepository.findById(id);
        // checks if blog exists
        if (blog.isPresent()) {
            // checks if blog has an image
            if (blog.get().getItemImage() != null) {
                // if so convert byte array to string
                String imageString = Base64.getEncoder().encodeToString(blog.get().getItemImage());
                BlogDto blogDtoImg = new BlogDto(blog.get().getBlogId(), blog.get().getBlogTitle(), blog.get().getUsername(), blog.get().getItemName(),
                        blog.get().getItemCategory(), imageString, blog.get().getBlogContact(), blog.get().getItemDescription());
                return blogDtoImg;
            }
            // if not, return blog without image
            BlogDto blogDto = new BlogDto(blog.get().getBlogId(), blog.get().getBlogTitle(), blog.get().getUsername(), blog.get().getItemName(),
                    blog.get().getItemCategory(), null, blog.get().getBlogContact(), blog.get().getItemDescription());
            return blogDto;
        }
        // if blog does not exist, return null
        return null;
    }

    public void deleteBlog(int id) {
        blogRepository.deleteById(id);
    }

}
