package com.example.wastemanagement.unit.repositories;

import com.example.wastemanagement.data.BlogRepositoryJpa;
import com.example.wastemanagement.domain.Blog;
import com.example.wastemanagement.web.Form.BlogForm;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.event.annotation.AfterTestClass;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;

@DataJpaTest
public class inputNewBlogsTest {
    private BlogRepositoryJpa blogRepositoryJpa;

    @Autowired
    public inputNewBlogsTest(BlogRepositoryJpa blogRepositoryJpa) {
        this.blogRepositoryJpa = blogRepositoryJpa;
    }

    @Test
    public void inputNewBlogTest() throws IOException {
        // Clears the repository to prep for testing
//        blogRepositoryJpa.deleteAll();
        int originalSize = blogRepositoryJpa.findAll().size();

        /* Creates a new image file and converts it to a byte[] to simulate a user input, then builds a new blog object
        to simulate that created when the form is submitted. It then saves this all to the repository */
        File file1 = new File("src/main/resources/static/images/index1.jpeg");
        byte[] testImage1 = Files.readAllBytes(file1.toPath());
        Blog blog1 = Blog.builder().blogId(null).blogTitle("Blog 2").username("Josh Demarche").itemName("Microwave Oven")
                .itemCategory("Electronics").itemImage(testImage1).blogContact("3124256374").itemDescription("Pressing 1 and holding 3 will turn the beep off.")
                .build();
        blogRepositoryJpa.save(blog1);

        // Test 2 same concept, different image extension
        File file2 = new File("src/main/resources/static/images/index2.png");
        byte[] testImage2 = Files.readAllBytes(file2.toPath());
        Blog blog2 = Blog.builder().blogTitle("BlogTwo").username("JohnDoe").itemName("Sofa")
                .itemCategory("Electronics").itemImage(testImage2).blogContact("3124256374").itemDescription("Great Sofa Overhaul")
                .build();
        blogRepositoryJpa.save(blog2);

        // Test 3 same concept, different image extension
        File file3 = new File("src/main/resources/static/images/index4.jpg");
        byte[] testImage3 = Files.readAllBytes(file3.toPath());
        Blog blog3 = Blog.builder().blogTitle("BlogThree").username("JohnDoe").itemName("Sofa")
                .itemCategory("Electronics").itemImage(testImage3).blogContact("3124256374").itemDescription("Great Sofa Overhaul")
                .build();
        blogRepositoryJpa.save(blog3);

        // This creates the same blog object byt without an image to simulate a user not adding an image
        Blog blog4 = Blog.builder().blogTitle("BlogFour").username("JohnDoe").itemName("Sofa")
                .itemCategory("Electronics").blogContact("3124256374").itemDescription("Great Sofa Overhaul")
                .build();
        blogRepositoryJpa.save(blog4);

        // Converts all the stored data in the blog repository to a list to be searched
        List<Blog> blogToTest = blogRepositoryJpa.findAll();

        /* Finds the corresponding blog from the list just created, then recursively checks every value in the outputted
        object against those in the inputted one. */
        List <Blog> blogToTest1 = new ArrayList<>();
        blogToTest1.addAll(List.of(blog1, blog2, blog3, blog4));

        for (int i = originalSize; i < blogToTest.size(); i++) {
            Assertions.assertThat(blogToTest.get(i)).usingRecursiveComparison().isEqualTo(blogToTest1.get(i - originalSize));
        }
//        Assertions.assertThat(blogToTest.get(0)).usingRecursiveComparison().isEqualTo(blog1);
//        Assertions.assertThat(blogToTest.get(1)).usingRecursiveComparison().isEqualTo(blog2);
//        Assertions.assertThat(blogToTest.get(2)).usingRecursiveComparison().isEqualTo(blog3);
//        Assertions.assertThat(blogToTest.get(3)).usingRecursiveComparison().isEqualTo(blog4);

    }
}
