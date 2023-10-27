package com.example.wastemanagement.unit.form;

import com.example.wastemanagement.domain.Blog;
import com.example.wastemanagement.domain.Comment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.event.annotation.AfterTestClass;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class CheckCommentForm {
    private Validator validator;

    // this is re-instantiate before each method
    // used to signal that the annotated method should be executed before each
    // @Test method in the current test class.
    @BeforeEach
    public void setUp() {
        new Blog(21, "Electric mop", "username", "Mop", "Cleaning", null,
                "3124256374", "description");
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @AfterTestClass
    public void tearDown() {
        // close the validator factory
        validator = null;
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    public void shouldNotAllowEmptyComment() {
         //GIVEN
         Comment comment = new Comment(1,21,"james", "");

         //WHEN
         var violations = validator.validate(comment);

         //THEN
         assertEquals(1, violations.size());
         assertEquals("commentText", violations.iterator().next().getPropertyPath().toString());
     }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    public void shouldNotAllowSpacesAsAWholeComment() {
        //GIVEN
        Comment comment = new Comment(1,21,"james", " ");

        //WHEN
        var violations = validator.validate(comment);

        //THEN
        assertEquals(1, violations.size());
        assertEquals("commentText", violations.iterator().next().getPropertyPath().toString());
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    public void shouldAllowValidComment() {
        //GIVEN
        Comment comment = new Comment(1,21,"james", "This is a valid comment");

        //WHEN
        var violations = validator.validate(comment);

        //THEN
        assertEquals(0, violations.size());
    }

    //Username
    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    public void shouldNotAllowEmptyUsername() {
        //GIVEN
        Comment comment = new Comment(1,21,"", "This is a valid comment");

        //WHEN
        var violations = validator.validate(comment);

        //THEN
        assertEquals("username", violations.iterator().next().getPropertyPath().toString());
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    public void shouldNotAllowSpacesAsAWholeUsername() {
        //GIVEN
        Comment comment = new Comment(1,21," ", "This is a valid comment");

        //WHEN
        var violations = validator.validate(comment);

        //THEN
        assertEquals("username", violations.iterator().next().getPropertyPath().toString());
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    public void shouldAllowValidUsername() {
        //GIVEN
        Comment comment = new Comment(1,21,"james", "This is a valid comment");

        //WHEN
        var violations = validator.validate(comment);

        //THEN
        assertEquals(0, violations.size());
    }

}
