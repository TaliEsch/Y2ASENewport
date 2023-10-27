package com.example.wastemanagement.unit.repositories;

import com.example.wastemanagement.data.BlogRepositoryJpa;
import com.example.wastemanagement.data.CommentRepositoryJpa;
import com.example.wastemanagement.domain.Blog;
import com.example.wastemanagement.domain.Comment;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class SaveAndGetComments {

    @Autowired
    private CommentRepositoryJpa commentRepositoryJpa;
    @Autowired
    private BlogRepositoryJpa blogRepositoryJpa;

    @Test
    public void saveAndGetAllTheCommentsByBlogId() {
        //Given

        Blog blog = new Blog(null, "Blog 2", "Josh Demarche", "Microwave Oven",
                "Electronics",null, "3124256374", "TESTING TESTING 123");

        // mack saving of the blog
        blogRepositoryJpa.saveAndFlush(blog);
        Assertions.assertThat(blogRepositoryJpa.findAll().contains(blog));

        Comment comment = Comment.builder().commentId(null).blogId(blog.getBlogId())
                .username("user1").commentText("comment1").build();
        Comment comment1 = Comment.builder().commentId(null).blogId(blog.getBlogId())
                .username("user1").commentText("comment1").build();
        Comment comment2 = Comment.builder().commentId(null).blogId(blog.getBlogId())
                .username("user1").commentText("comment1").build();

        commentRepositoryJpa.saveAll(List.of(comment, comment1, comment2));
        //When
        List<Comment> comments = commentRepositoryJpa.findAllByBlogId(blog.getBlogId());
        //Then
        assertEquals(3, comments.size());
        Assertions.assertThat(comments.get(0)).usingRecursiveComparison().isEqualTo(comment);
        Assertions.assertThat(comments.get(1)).usingRecursiveComparison().isEqualTo(comment1);
        Assertions.assertThat(comments.get(2)).usingRecursiveComparison().isEqualTo(comment2);
    }
}
